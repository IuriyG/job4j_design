package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 15.06.2022
 */
public class ArgsNameTest {
    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    public void whenGetTwoArgumentsInReversOrder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertEquals(jvm.get("Xmx"), "512");
        assertEquals(jvm.get("encoding"), "UTF-8");
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertEquals(jvm.get("request"), "?msg=Exit=");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArrayIsEmpty() {
        ArgsName.of(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsHyphen() {
        ArgsName.of(new String[]{"-=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAbsentEqualSymbol() {
        ArgsName.of(new String[]{"-encodingUTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAbsentHyphenSymbol() {
        ArgsName.of(new String[]{"encoding=UTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAbsentKeyAndTwoEqualSymbol() {
        ArgsName.of(new String[]{"-==password"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAbsentKeyAndValue() {
        ArgsName.of(new String[]{"-="});
    }
}