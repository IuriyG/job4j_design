package ru.job4j.generics.store;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Iuriy Gaydarzhi.
 * @created 17.12.2020
 */
public class UserStoreTest {
    protected User user, user2, user3, user4, user5;
    private UserStore memStore;

    /**
     * Метод выполняется перед тестами.
     */
    @Before
    public void setUp() {
        memStore = new UserStore();
        user = new User("Penelopa");
        user2 = new User("Jon");
        user3 = new User("Michele");
        user4 = new User("Danny");
        user5 = new User("James");
        memStore.add(user);
        memStore.add(user2);
        memStore.add(user3);
    }

    /**
     * Тест метода add.
     */
    @Test
    public void testAdd() {
        assertEquals(memStore.findById("Jon"), user2);
    }

    /**
     * Тест метода findById.
     */
    @Test
    public void testFindById() {
        assertEquals(memStore.findById("Michele"), user3);
        assertNull(memStore.findById("none"));
    }

    /**
     * Тест метода replace.
     */
    @Test
    public void testReplace() {
        memStore.replace("Jon", user5);
        assertEquals(memStore.findById("James"), user5);
    }

    /**
     * Тест метода delete.
     */
    @Test
    public void testDelete() {
        memStore.delete("Jon");
        assertNull(memStore.findById("Jon"));
    }
}
