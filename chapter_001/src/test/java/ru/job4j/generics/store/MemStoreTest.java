package ru.job4j.generics.store;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public class MemStoreTest {
    protected User user, user2, user3, user4, user5, user6;
    private MemStore<Base> memStore;

    /**
     * Метод выполняется перед тестами.
     */
    @Before
    public void setUp() {
        memStore = new MemStore<>();
        user = new User("one");
        user2 = new User("two");
        user3 = new User("three");
        user4 = new User("four");
        user5 = new User("five");
        user6 = new User("six");
        memStore.add(user);
        memStore.add(user2);
        memStore.add(user3);
    }

    /**
     * Тест метода add.
     */
    @Test
    public void whenUseAdd() {
        assertEquals(memStore.findById("one"), user);
    }

    /**
     * Тест метода replace.
     */
    @Test
    public void whenUseReplace() {
        memStore.replace("two", user4);
        memStore.replace("one", user5);
        memStore.replace("six", user);
        assertEquals(memStore.findById("four"), user4);
        assertEquals(memStore.findById("five"), user5);
        assertNull(memStore.findById("six"));
    }

    /**
     * Тест метода delete.
     */
    @Test
    public void whenUseDelete() {
        memStore.delete("two");
        memStore.delete("six");
        assertNull(memStore.findById("two"));
        assertNull(memStore.findById("six"));
    }

    /**
     * Тест метода findById.
     */
    @Test
    public void whenUseFindById() {
        assertEquals(memStore.findById("three"), user3);
        assertNull(memStore.findById("six"));
    }

    /**
     * Тест метода searchIdByIndex
     */
    @Test
    public void whenUseSearchById() {
        assertEquals(memStore.searchIdByIndex("three"), 2);
        assertEquals(memStore.searchIdByIndex("six"), -1);
    }
}
