package com.example.inrt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {


    private Integer id;

    private String title;
    private String url;
    private String description;

    public Card(){}

    public Card(String title,String url,String description, Integer id)
    {
        setId(id);
        setTitle(title);
        setUrl(url);
        setDescription(description);
    }


}
