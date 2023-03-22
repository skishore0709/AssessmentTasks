package com.kishore.readingDataJSONFormat.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchemeDTO {

    @JsonProperty("scheme_Id")
    private String scheme_Id;

    @JsonProperty("scheme_Code")
    private String scheme_Code;

    @JsonProperty("scheme_Name")
    private String scheme_Name;
}
