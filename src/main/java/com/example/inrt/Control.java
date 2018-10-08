package com.example.inrt;

import com.example.inrt.exceptions.BadRequest;
import com.example.inrt.sampleName.Card;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;


import java.util.*;

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
    public Card add (@RequestBody Card card)
    {

        Optional<String> validate = validate(card);
        if(validate.isPresent()) {
            throw new BadRequest("Invalid card " + validate.get());
        }
        card.setId(id++);
        cards.add(card);
        return card;
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity handleBadReq(BadRequest e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    private Optional<String> validate(Card card) {
        if (card.getDescription() == null) {
            return Optional.of("Invalid description");
        }

        return Optional.empty();
    }


}
