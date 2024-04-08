package com.example.textanalyzerbe;

import com.example.textanalyzerbe.Service.TextAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

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
        Map<Character, Integer> result = instance.analyze("vowels", "");

        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeEmptyStringForConsonantsShouldBeAllZeros() {
        Map<Character, Integer> result = instance.analyze("consonants", "");

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
        Map<Character, Integer> result = instance.analyze("vowels", "bcdFg1H4JßklMnP;Üü+Qrs#?TvWXyz");
        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeTextForConsonantsWithoutConsonantsShouldReturnZeros() {
        Map<Character, Integer> result = instance.analyze("consonants", "aEioU");
        for (Integer number : result.values()) {
            assertThat(number).isEqualTo(0);
        }
    }

    @Test
    public void analyzeTextForConsonantsWithConsonantsShouldReturnNumbers() {
        Map<Character, Integer> result = instance.analyze("consonants", "bcdF?egHJak&lM*OnPäQorÖsTvÜWXi#yzBßUCD§fGhe!jKuL.mNi-pqRSetVEwxYZvuvsnwqrSs");
        Map<Character, Integer> expectedResult = Map.ofEntries(
                Map.entry('B', 2),
                Map.entry('C', 2),
                Map.entry('D', 2),
                Map.entry('F', 2),
                Map.entry('G', 2),
                Map.entry('H', 2),
                Map.entry('J', 2),
                Map.entry('K', 2),
                Map.entry('L', 2),
                Map.entry('M', 2),
                Map.entry('N', 3),
                Map.entry('P', 2),
                Map.entry('Q', 3),
                Map.entry('R', 3),
                Map.entry('S', 7),
                Map.entry('T', 2),
                Map.entry('V', 4),
                Map.entry('W', 3),
                Map.entry('X', 2),
                Map.entry('Y', 2),
                Map.entry('Z', 2)
        );
        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    public void analyzeTextForVowelsWithVowelsShouldReturnNumbers() {
        Map<Character, Integer> result = instance.analyze("vowels", "bcdF?egHJak&lM*OnPäQorÖsTvÜWXi#yzBßUCD§fGhe!jKuL.mNi-pqRSetVEwxYZvuvsnwqrSs");
        Map<Character, Integer> expectedResult = Map.of(
                'A', 1,
                'E', 4,
                'I', 2,
                'O', 2,
                'U', 3
        );
        assertThat(result).isEqualTo(expectedResult);
    }

}
