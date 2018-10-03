package com.example.inrt;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/cards")
public class Calc {

    private int id = 1;
    public List<Map<String,String>> cardExample = new ArrayList<>()

    {{add(new HashMap<String, String>()
        {{ put("title","some title"); put("picture_url","some picture url"); put("description","some description");put("button_url","some button url");}});
    }};

    public List<Map<String,String>> cards = new ArrayList<>();

    @GetMapping("/cards")
    String defResp()
    {
        String newLine = "\r\n";
        return "it's works but"+newLine+"Use method POST with next body"+newLine+cardExample;
    }

    @GetMapping("/cards/all")
    public String greeting(Map<String,String> model)
    {
        model.put("title", "Test");
        model.put("picture_url", "Test");
        model.put("description", "Test");
        model.put("button_url", "Test");
    return "view"; }



    @PostMapping("/cards/add")
    public Map add (@RequestBody Map<String,String> card)
    {
        card.put("id",String.valueOf(id++));
        cards.add(card);
        return card;
    }


}
