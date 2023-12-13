package com.example.textanalyzerbe.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TextAnalyzerService {

  private final char[] vowelsArray = "AEIOU".toCharArray();
  private final char[] consonantArray = "BCDFGHJKLMNPQRSTVWXYZ".toCharArray();

  public TextAnalyzerService() {
  }

  /**
   * calculates how many times either all vowels or consonants appear in the text. All letters are counted as uppercase.
   * So "ß" counts as two S
   *
   * @param type can be 'vowels' or 'consonants' otherwise an IllegalArgumentException is thrown
   * @param text the text to analyze
   * @return a HashMap that has all the vowels or consonants as keys and their respective counts as values
   */
  public HashMap<Character, Integer> analyze(String type, String text) {
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
   * @return a HashMap that has all the vowels or consonants as keys and their respective counts as values
   */
  public HashMap<Character, Integer> analyzeTextForVowels(String text) {
    HashMap<Character, Integer> vowels = initializeVowelsHashMap();

    return addCharactersToHashMapValues(text, vowels);
  }

  /**
   * calculates how many times all consonants appear in the text. All letters are counted as uppercase.
   * so "ß" counts as two S
   *
   * @param text the text to analyze
   * @return a HashMap that has all the consonants or consonants as keys and their respective counts as values
   */
  public HashMap<Character, Integer> analyzeTextForConsonants(String text) {
    HashMap<Character, Integer> consonants = initializeConsonantsHashMap();

    return addCharactersToHashMapValues(text, consonants);
  }

  /**
   * Counts the number the charcters given in the characterFrequency Map appear in the text and adds the count to the map.
   * All characters are counted as upperCase
   *
   * @param text               the text to analyze
   * @param characterFrequency a Map that determines the characters of interest, by having them as keys. The counts are added to the existing values of this map
   * @return The original map with the added counts for each character
   */
  public HashMap<Character, Integer> addCharactersToHashMapValues(String text, HashMap<Character, Integer> characterFrequency) {
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
   * Creates a Map that has all vowels as keys and zeros as values
   *
   * @return the created map
   */
  private HashMap<Character, Integer> initializeVowelsHashMap() {
    HashMap<Character, Integer> vowelsHashMap = new HashMap<>();
    for (char c : vowelsArray) {
      vowelsHashMap.put(c, 0);
    }
    return vowelsHashMap;
  }

  /**
   * Creates a Map that has all consonats as keys and zeros as values
   *
   * @return the created map
   */
  private HashMap<Character, Integer> initializeConsonantsHashMap() {
    HashMap<Character, Integer> vowelsHashMap = new HashMap<>();
    for (char c : consonantArray) {
      vowelsHashMap.put(c, 0);
    }
    return vowelsHashMap;
  }

}
