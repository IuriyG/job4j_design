package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Iuriy Gaydarzhi.
 * @since 21.08.2022
 */
class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "four", "five", "fifteen", "fifty");
        assertThat(list)
                .allMatch(s -> s.contains("f"))
                .isExactlyInstanceOf(list.getClass())
                .anyMatch(s -> s.equals("fifteen"))
                .isNotSameAs("ten")
                .doesNotHaveDuplicates()
                .doesNotContainNull();
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "four", "five", "fifteen", "fifty");
        assertThat(set).allSatisfy(s -> {
                    assertThat(s).startsWith("f");
                    assertThat(s).doesNotMatch("fi");
                    assertThat(s).isLowerCase();
                }).containsAnyOf("one", "ten", "fifty")
                .element(0).isNotSameAs("Second");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> str = simpleConvert.toMap("first", "four", "five", "fifteen", "fifty");
        assertThat(str)
                .hasSize(5)
                .containsKeys("first", "fifty")
                .containsValue(2)
                .containsEntry("first", 0)
                .containsEntry("five", 2)
                .doesNotContainKeys("one", "two", "ten");
    }
}