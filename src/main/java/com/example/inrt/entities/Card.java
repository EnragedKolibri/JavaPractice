package com.example.inrt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    @Id
    private Integer id;

    private String title;
    private String image_url;
    private String description;

    public Card(){}

    public Card(String title,String url,String description, Integer id)
    {
        setId(id);
        setTitle(title);
        setImage_url(url);
        setDescription(description);
    }


}
