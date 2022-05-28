package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 25.05.2022
 */
public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "James Gosling");
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "James Gosling");
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueEmptyWithComment() {
        String path = "./data/value_empty_comment.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyIsEmpty() {
        String path = "./data/key_empty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoSplitSymbol() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
    }

   @Test
    public void whenManySplitSymbols() {
        String path = "./data/several_symbols.properties";
        Config config = new Config(path);
        config.load();
       assertEquals(config.value("name"), "author=1=James Gosling");
   }
}