package com.example.inrt;

import com.example.inrt.sampleName.Card;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.*;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Control {

    private int id = 1;
    public List<Card> cards = new ArrayList<>();

    @GetMapping("/cards")
    String defResp()
    {
        return "it's works ";
    }

    @GetMapping("/cards/all")
    public String greeting(Map<String,String> model)
    {
        model.put("title", "Test");
        model.put("picture_url", "Test");
        model.put("description", "Test");
        model.put("button_url", "Test");
    return "view"; }



    @PostMapping(value = "/cards/add",consumes ={"application/json"})
    public String add (Card card)
    {

//        card.add("id",JsonElement );
//        cards.add(card.);
        Gson gson = new Gson();
        return gson.toJson(card);
    }


}
