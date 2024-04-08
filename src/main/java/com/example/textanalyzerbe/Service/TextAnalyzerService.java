package com.example.textanalyzerbe.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TextAnalyzerService {

  private static final char[] vowelsArray = "AEIOU".toCharArray();
  private static final char[] consonantArray = "BCDFGHJKLMNPQRSTVWXYZ".toCharArray();

  public TextAnalyzerService() {
  }

  /**
   * calculates how many times either all vowels or consonants appear in the text. All letters are counted as uppercase.
   * So "ß" counts as two S
   *
   * @param type can be 'vowels' or 'consonants' otherwise an IllegalArgumentException is thrown
   * @param text the text to analyze
   * @return a Map that has all the vowels or consonants as keys and their respective counts as values
   */
  public Map<Character, Integer> analyze(String type, String text) {
    if (type.equals("vowels")) {
      return analyzeTextForVowels(text);
    } else if (type.equals("consonants")) {
      return analyzeTextForConsonants(text);
    } else {
      throw new IllegalArgumentException("Invalid type: " + type);
    }
  }

  /**
   * calculates how many times all vowels appear in the text All letters are counted as uppercase.
   *
   * @param text the text to analyze
   * @return a Map that has all the vowels or consonants as keys and their respective counts as values
   */
  public Map<Character, Integer> analyzeTextForVowels(String text) {
    Map<Character, Integer> vowels = initializeVowelsOrConsonantsMap(true);

    return addCharactersToMapValues(text, vowels);
  }

  /**
   * calculates how many times all consonants appear in the text. All letters are counted as uppercase.
   * so "ß" counts as two S
   *
   * @param text the text to analyze
   * @return a Map that has all the consonants or consonants as keys and their respective counts as values
   */
  public Map<Character, Integer> analyzeTextForConsonants(String text) {
    Map<Character, Integer> consonants = initializeVowelsOrConsonantsMap(false);

    return addCharactersToMapValues(text, consonants);
  }

  /**
   * Counts the number the charcters given in the characterFrequency Map appear in the text and adds the count to the map.
   * All characters are counted as upperCase
   *
   * @param text               the text to analyze
   * @param characterFrequency a Map that determines the characters of interest, by having them as keys. The counts are added to the existing values of this map
   * @return The original map with the added counts for each character
   */
  public Map<Character, Integer> addCharactersToMapValues(String text, Map<Character, Integer> characterFrequency) {
    char[] chars = text.toUpperCase().toCharArray();
    for (char aChar : chars) {
      if (characterFrequency.containsKey(aChar)) {
        Integer num = characterFrequency.get(aChar);
        characterFrequency.put(aChar, ++num);
      }
    }
    return characterFrequency;
  }

  /**
   * Creates a Map that based on the isVowels parameter either has all vowels or all consonants as keys
   * and zeros as values
   *
   * @param isVowels determines if the keys of the map are all consonants or all vowels
   * @return the created map
   */
  private Map<Character, Integer> initializeVowelsOrConsonantsMap(boolean isVowels) {
    Map<Character, Integer> initializedMap = new HashMap<>();

    char[] characterArray = consonantArray;

    if (isVowels) {
      characterArray = vowelsArray;
    }

    for (char c : characterArray) {
      initializedMap.put(c, 0);
    }
    return initializedMap;
  }


}
