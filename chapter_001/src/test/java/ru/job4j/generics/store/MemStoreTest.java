package ru.job4j.generics.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * @author Iuriy Gaydarzhi.
 * @created 16.12.2020
 */
public class MemStoreTest {
    protected User user, user2, user3, user4, user5;
    private MemStore<Base> memStore;

    @Before
    public void setUp() {
        memStore = new MemStore<>();
        user = new User("one");
        user2 = new User("two");
        user3 = new User("three");
        user4 = new User("four");
        user5 = new User("five");
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
        assertThat(memStore.findById("four"), is(user4));
        assertThat(memStore.findById("five"), is(user5));
    }

    @Test
    public void whenUseDelete() {
        memStore.delete("four");
        assertNull(memStore.findById("four"));
    }

    @Test
    public void whenUseFindById() {
        assertThat(memStore.findById("three"), is(user3));
        assertNull(memStore.findById("none"));
    }
}
