package com.example.inrt.Controllers;
import com.example.inrt.exceptions.BadRequest;
import com.example.inrt.entities.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class CardsController {

    private int id = 1;
    private List<Card> cards = new ArrayList<>();

    @PostMapping(value = "cards/update",consumes = {"application/json"})
    public Card update(@RequestBody Card card)
    {
       Optional<String> validate = validateEditCard(card);
        if (validate.isPresent())
        {
            throw new BadRequest("Invalid card " + validate.get());
        }

        return updateCard(cards.get(findCardByID(cards,card)),card);
    }

    private Integer findCardByID(List<Card> cards,Card toFind)
    {
        boolean found = false;
        int i;
        for (i = 0; !found ;i++)
        {
            if (cards.get(i).getId() == toFind.getId())
            {
                found = true;
            }

        }
        return i-1;
    }

    private Card updateCard(Card taken,Card given)
    {
        if (given.getDescription() != null)
        {
            taken.setDescription(given.getDescription());
        }
        if(given.getTitle() != null)
        {
            taken.setTitle(given.getTitle());
        }
        if(given.getUrl() != null)
        {
            taken.setUrl(given.getUrl());
        }

        return cards.get(findCardByID(cards,given));

    }


    @GetMapping(value = "cards/all")
    public List<Card> allCards()
    {
        return cards;
    }

    @PostMapping(value = "/cards/add",consumes ={"application/json"})
    public Card add (@RequestBody Card card)
    {
        Optional<String> validate = validateAddCard(card);
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

    private Optional<String> validateAddCard(Card card) {
        if (card.getDescription() == null) {
            return Optional.of("Invalid description");
        }
        if (card.getTitle() == null) {
            return Optional.of("Invalid title");
        }
        if (card.getUrl() == null) {
            return Optional.of("Invalid url");
        }
        return Optional.empty();
    }

    private Optional<String> validateEditCard(Card card) {
        if (card.getId() == null) {
            return Optional.of("Invalid description");
        }
        return Optional.empty();
    }


}
