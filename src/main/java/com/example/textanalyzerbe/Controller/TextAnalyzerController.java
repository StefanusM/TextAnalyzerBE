package com.example.textanalyzerbe.Controller;

import com.example.textanalyzerbe.Service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(
        origins = {
                "http://localhost:4200"
        },
        methods = {
                RequestMethod.POST
        }
)
@RestController
public class TextAnalyzerController {

    @Autowired
    private TextAnalyzerService service;

    /**
     * Counts the number of each vowel in the given text and returns it as a Map
     *
     * @param text the text to analyze
     * @return a Map that has all the vowels as keys and their respective counts as values
     */
    @PostMapping(path = "/analyze-vowels")
    public Map<Character, Integer> analyzeTextForVowels(@RequestBody String text) {

        return service.analyzeTextForVowels(text);
    }

    /**
     * Counts the number of each consonant in the given text and returns it as a Map
     *
     * @param text the text to analyze
     * @return a Map that has all the consonants as keys and their respective counts as values
     */
    @PostMapping(path = "/analyze-consonants")
    public Map<Character, Integer> analyzeTextForConsonants(@RequestBody String text) {

        return service.analyzeTextForConsonants(text);
    }

}
