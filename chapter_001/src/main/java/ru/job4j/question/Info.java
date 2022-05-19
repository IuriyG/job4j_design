package ru.job4j.question;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.05.2022
 */
public class Info {
    private final int changed;
    private final int deleted;
    private final int added;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Info info = (Info) o;

        if (added != info.added) {
            return false;
        }
        if (changed != info.changed) {
            return false;
        }
        return deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        int result = added;
        result = 31 * result + changed;
        result = 31 * result + deleted;
        return result;
    }

    @Override
    public String toString() {
        return "Info{"
                + "added="
                + added
                + ", changed="
                + changed
                + ", deleted="
                + deleted
                + '}';
    }
}
