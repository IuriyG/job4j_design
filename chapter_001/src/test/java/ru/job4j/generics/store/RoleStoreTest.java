package ru.job4j.generics.store;

import junit.framework.TestCase;

/**
 * @author Iuriy Gaydarzhi.
 * @created 18.12.2020
 */
public class RoleStoreTest extends TestCase {
    protected Role role, role2, role3, role4, role5;
    private RoleStore memStore;

    /**
     * Метод выполняется перед тестами.
     */
    public void setUp() {
        memStore = new RoleStore();
        role = new Role("finisher");
        role2 = new Role("coordinator");
        role3 = new Role("insider");
        role4 = new Role("expert");
        role5 = new Role("leader");
        memStore.add(role);
        memStore.add(role2);
        memStore.add(role3);
    }

    /**
     * Тест метода add.
     */
    public void testAdd() {
        assertEquals(memStore.findById("insider"), role3);
    }

    /**
     * Тест метода replace.
     */
    public void testReplace() {
        memStore.replace("coordinator", role5);
        assertEquals(memStore.findById("leader"), role5);
    }

    /**
     * Тест метода delete.
     */
    public void testDelete() {
        memStore.delete("insider");
        assertNull(memStore.findById("insider"));
    }

    /**
     * Тест метода findById.
     */
    public void testFindById() {
        assertEquals(memStore.findById("finisher"), role);
        assertNull(memStore.findById("none"));
    }
}
