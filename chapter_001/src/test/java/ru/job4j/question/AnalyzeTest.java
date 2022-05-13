package ru.job4j.question;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.05.2022
 */
public class AnalyzeTest {
    @Test
    public void whenNotChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        assertEquals(Analyze.diff(previous, current), (new Info(0, 0, 0)));
    }

    @Test
    public void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), u3);
        assertEquals(Analyze.diff(previous, current), (new Info(0, 1, 0)));
    }

    @Test
    public void whenTwoChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), new User(3, "CC"));
        assertEquals(Analyze.diff(previous, current), (new Info(0, 2, 0)));
    }

    @Test
    public void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u3);
        assertEquals(Analyze.diff(previous, current), (new Info(0, 0, 1)));
    }

    @Test
    public void whenTwoDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1);
        assertEquals(Analyze.diff(previous, current), (new Info(0, 0, 2)));
    }

    @Test
    public void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        assertEquals(Analyze.diff(previous, current), (new Info(1, 0, 0)));
    }

    @Test
    public void whenTwoAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"), new User(5, "E"));
        assertEquals(Analyze.diff(previous, current), (new Info(2, 0, 0)));
    }

    @Test
    public void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertEquals(Analyze.diff(previous, current), (new Info(1, 1, 1)));
    }

    @Test
    public void whenTwoDeletedAndTowChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        Set<User> previous = Set.of(u1, u2, u3, u4);
        Set<User> current = Set.of(new User(1, "AA"), new User(2, "BB"));
        assertEquals(Analyze.diff(previous, current), (new Info(0, 2, 2)));
    }

    @Test
    public void whenTwoAddedAndTowChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        Set<User> previous = Set.of(u1, u2);
        Set<User> current = Set.of(
                new User(1, "AA"),
                new User(2, "BB"),
                new User(3, "C"),
                new User(4, "D")
        );
        assertEquals(Analyze.diff(previous, current), (new Info(2, 2, 0)));
    }

    @Test
    public void whenTwoAddedAndThreeDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u5 = new User(5, "E");
        Set<User> previous = Set.of(u1, u2, u5);
        Set<User> current = Set.of(new User(3, "C"), new User(4, "D"));
        assertEquals(Analyze.diff(previous, current), (new Info(2, 0, 3)));
    }

    @Test
    public void whenAllChangedTwoTimes() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        Set<User> previous = Set.of(u1, u2, u3, u4);
        Set<User> current = Set.of(
                new User(1, "AA"),
                new User(2, "BB"),
                new User(5, "E"),
                new User(6, "F")
        );
        assertEquals(Analyze.diff(previous, current), (new Info(2, 2, 2)));
    }
}