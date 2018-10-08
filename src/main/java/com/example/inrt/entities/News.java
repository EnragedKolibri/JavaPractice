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
public class News {

    private Integer id;

    private String title;
    private String image_url;
    private String description;
    private String source_url;

    public News(){}
    public News(Integer id,String title,String image_url,String description,String source_url)
    {
        setId(id);
        setTitle(title);
        setDescription(description);
        setImage_url(image_url);
        setSource_url(source_url);
    }

}
