package com.example.block18elasticSearch.controller;

import com.example.block18elasticSearch.application.DictionaryService;
import com.example.block18elasticSearch.controller.dto.input.DictionaryInput;
import com.example.block18elasticSearch.controller.dto.output.DictionaryOutputSimple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @PostMapping("/save")
    public DictionaryOutputSimple saveDictionary(@RequestBody DictionaryInput dictionaryInput) {
        return dictionaryService.saveDictionary(dictionaryInput);
    }
    @DeleteMapping("/delete")
    public void deleteDictionary(@RequestParam String id) {
        dictionaryService.deleteDictionary(id);
    }
    @GetMapping("/search")
    public List<DictionaryOutputSimple> searchDictionary(@RequestParam String text) {
        return dictionaryService.searchDictionaryv2(text);
    }

}
