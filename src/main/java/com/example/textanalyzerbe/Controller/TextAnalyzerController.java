package com.example.textanalyzerbe.Controller;

import com.example.textanalyzerbe.Service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(
        origins = {
                "http://localhost:4200"
        },
        methods = {
                RequestMethod.GET
        }
)
@RestController
public class TextAnalyzerController {

    @Autowired
    private TextAnalyzerService service;

    /**
     * Counts the number of each vowel in the given text and returns it as a HashMap
     *
     * @param text the text to analyze
     * @return a HashMap that has all the vowels as keys and their respective counts as values
     */
    @GetMapping(path = "/analyzeVowels")
    public HashMap<Character, Integer> analyzeTextForVowels(@RequestParam String text) {

        return service.analyzeTextForVowels(text);
    }

    /**
     * Counts the number of each consonant in the given text and returns it as a HashMap
     *
     * @param text the text to analyze
     * @return a HashMap that has all the consonants as keys and their respective counts as values
     */
    @GetMapping(path = "/analyzeConsonants")
    public HashMap<Character, Integer> analyzeTextForConsonants(@RequestParam String text) {

        return service.analyzeTextForConsonants(text);
    }

}
