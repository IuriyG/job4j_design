package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


/**
 * @author Iuriy Gaydarzhi.
 * @since 20.08.2022
 */
class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkStartWith() {
        NameLoad nameLoad = new NameLoad();
        String name = "=Bob=Buffalo";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("this name: %s does not contain a key", name);
    }

    @Test
    void checkContainsEquals() {
        NameLoad nameLoad = new NameLoad();
        String name = "BobBuffalo";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("this name: %s does not contain the symbol \"=\"", name);
    }

    @Test
    void check() {
        NameLoad nameLoad = new NameLoad();
        String name = "BobBuffalo=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("this name: %s does not contain a value", name);
    }
}