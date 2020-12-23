package ru.job4j.generics.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * @author Iuriy Gaydarzhi.
 * @since 16.12.2020
 */
public class MemStoreTest {
    protected User user, user2, user3, user4, user5, user6;
    private MemStore<Base> memStore;

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

    @Test
    public void whenUseAdd() {
        assertThat(memStore.findById("one"), is(user));
    }

    @Test
    public void whenUseReplace() {
        memStore.replace("two", user4);
        memStore.replace("one", user5);
        memStore.replace("six", user);
        assertThat(memStore.findById("four"), is(user4));
        assertThat(memStore.findById("five"), is(user5));
        assertNull(memStore.findById("six"));
    }

    @Test
    public void whenUseDelete() {
        memStore.delete("two");
        memStore.delete("six");
        assertNull(memStore.findById("two"));
        assertNull(memStore.findById("six"));
    }

    @Test
    public void whenUseFindById() {
        assertThat(memStore.findById("three"), is(user3));
        assertNull(memStore.findById("six"));
    }

    @Test
    public void whenUseSearchById() {
        assertThat(memStore.searchIdByIndex("three"), is(2));
        assertThat(memStore.searchIdByIndex("six"), is(-1));
    }
}
