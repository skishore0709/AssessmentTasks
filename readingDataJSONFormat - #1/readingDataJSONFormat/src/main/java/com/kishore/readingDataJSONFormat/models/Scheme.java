package com.kishore.readingDataJSONFormat.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "scheme")
@Accessors( chain = true)
@DynamicUpdate
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_Id")
    int scheme_id;

    //JsonProperty("Scheme_Code")
    @Column(name = "scheme_Code")
    private String scheme_Code;

    //JsonProperty("schemeName")
    @Column(name="scheme_Name")
    private String scheme_Name;

}
