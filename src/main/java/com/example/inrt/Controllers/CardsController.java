package com.example.inrt.Controllers;
import com.example.inrt.exceptions.BadRequest;
import com.example.inrt.entities.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class CardsController {

    private int id = 0;
    private Map<Integer,Card> cardMap = new HashMap<>();

    @PostMapping(value = "cards/update",consumes = {"application/json"})
    public Card update(@RequestBody Card card)
    {
       Optional<String> validate = validateEditCard(card);
        if (validate.isPresent())
        {
            throw new BadRequest("Invalid card " + validate.get());
        }
        Card cardExist = cardMap.get(card.getId());
        return updateCard(cardExist,card);
    }


    private Card updateCard(Card cardExist, Card given)
    {
        if(cardExist==null)
        {
            throw new BadRequest("Id is not exist");
        }

        if (given.getDescription() != null)
        {
            cardExist.setDescription(given.getDescription());
        }
        if(given.getTitle() != null)
        {
            cardExist.setTitle(given.getTitle());
        }
        if(given.getUrl() != null)
        {
            cardExist.setUrl(given.getUrl());
        }

        return cardExist;

    }


    @GetMapping(value = "cards/all")
    public Collection<Card> allCards()
    {
        return cardMap.values();
    }

    @PostMapping(value = "/cards/add",consumes ={"application/json"})
    public Card add (@RequestBody Card card)
    {
        Optional<String> validate = validateAddCard(card);
        if(validate.isPresent()) {
            throw new BadRequest("Invalid card " + validate.get());
        }
        id++;
        card.setId(id);

        cardMap.put(id, card);
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
