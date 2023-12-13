package com.example.textanalyzerbe;

import com.example.textanalyzerbe.Service.TextAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class TextAnalyzerServiceUnitTest {

    private final TextAnalyzerService instance;

    public TextAnalyzerServiceUnitTest() {
        instance = new TextAnalyzerService();
    }

    @Test
    public void analyzeEmptyStringForVowelsShouldBeAllZeros() {
        HashMap<Character, Integer> result = instance.analyze("vowels", "");

        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeEmptyStringForConsonantsShouldBeAllZeros() {
        HashMap<Character, Integer> result = instance.analyze("consonants", "");

        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void throwInvalidInputExceptionForWrongType() {
        assertThatThrownBy(() -> instance.analyze("test", "test")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void analyzeTextForVowelsWithoutVowelsShouldReturnZeros() {
        HashMap<Character, Integer> result = instance.analyze("vowels", "bcdFg1H4JßklMnP;Üü+Qrs#?TvWXyz");
        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeTextForConsonantsWithoutConsonantsShouldReturnZeros() {
        HashMap<Character, Integer> result = instance.analyze("consonants", "aEioU");
        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeTextForConsonantsWithConsonantsShouldReturnNumbers() {
        HashMap<Character, Integer> result = instance.analyze("consonants", "bcdF?egHJak&lM*OnPäQorÖsTvÜWXi#yzBßUCD§fGhe!jKuL.mNi-pqRSetVEwxYZvuvsnwqrSs");
        HashMap<Character, Integer> expectedResult = new HashMap<>() {{
            put('B', 2);
            put('C', 2);
            put('D', 2);
            put('F', 2);
            put('G', 2);
            put('H', 2);
            put('J', 2);
            put('K', 2);
            put('L', 2);
            put('M', 2);
            put('N', 3);
            put('P', 2);
            put('Q', 3);
            put('R', 3);
            put('S', 7);
            put('T', 2);
            put('V', 4);
            put('W', 3);
            put('X', 2);
            put('Y', 2);
            put('Z', 2);
        }};
        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    public void analyzeTextForVowelsWithVowelsShouldReturnNumbers() {
        HashMap<Character, Integer> result = instance.analyze("vowels", "bcdF?egHJak&lM*OnPäQorÖsTvÜWXi#yzBßUCD§fGhe!jKuL.mNi-pqRSetVEwxYZvuvsnwqrSs");
        HashMap<Character, Integer> expectedResult = new HashMap<>() {{
            put('A', 1);
            put('E', 4);
            put('I', 2);
            put('O', 2);
            put('U', 3);
        }};
        assertThat(result).isEqualTo(expectedResult);
    }

}
