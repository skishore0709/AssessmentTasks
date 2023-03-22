package com.kishore.readingDataJSONFormat.service;

import com.kishore.readingDataJSONFormat.models.Scheme;
import com.kishore.readingDataJSONFormat.models.SchemeDTO;
import com.kishore.readingDataJSONFormat.repository.SchemeRepository;
import com.kishore.readingDataJSONFormat.status.SchemeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Optional;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchemeService {

    @Autowired
    SchemeRepository schemeRepository;

    public  SchemeService(SchemeRepository schemeRepository){
        this.schemeRepository = schemeRepository;
    }

    public List<SchemeDTO> getSchemes(){
        return  schemeRepository.findAll().stream().map(this::responseJson).collect(Collectors.toList());
    }

    public SchemeDTO responseJson(Scheme scheme){
        SchemeDTO schemeDTO = new SchemeDTO();
        schemeDTO.setScheme_Id(String.valueOf(scheme.getScheme_id()));
        schemeDTO.setScheme_Code(scheme.getScheme_Code());
        scheme.setScheme_Name(scheme.getScheme_Name());
        return schemeDTO;
    }

    public Pair<SchemeRepository, Boolean> getAllSchemesById(Long id){
        Slice<Scheme> schemes;
        schemes = schemeRepository.findAllSchemesByCode(SchemeStatus.ACTIVE.value(),id, Scheme.class);
        return Pair.of(schemeRepository,schemes.hasNext());
    }

    public Optional<Scheme> findById(Long id){
        return schemeRepository.findById(id);
    }

    public void schemesSave() throws ParserConfigurationException, IOException, SAXException {
        Scheme scheme = new Scheme();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("https://api.mfapi.in/mf"));
        NodeList elements = document.getDocumentElement().getElementsByTagName("scheme");
        String code,name = null;

        for (int i=0; i < elements.getLength(); i++){
            Node node = elements.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                NodeList childElements = node.getChildNodes();
                for (int j= 0; j < childElements.getLength(); j+=1){
                    Node child = childElements.item(j);
                    Element childElement = (Element) child;

                    switch (childElement.getTagName()){
                        case "schemeCode" -> {
                            code = childElement.getTextContent();
                            scheme.setScheme_Code(code);
                        }
                        case "schemeName" -> {
                            name = childElement.getTextContent();
                            scheme.setScheme_Name(name);
                        }
                        default -> {

                        }
                    }
                    schemeRepository.save(scheme);
                }
            }
        }
    }
}
