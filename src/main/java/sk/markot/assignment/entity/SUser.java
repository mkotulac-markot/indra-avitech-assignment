package sk.markot.assignment.entity;

import java.util.Objects;

public record SUser(Long id, String guid, String name) implements Entity {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SUser sUser = (SUser) o;
        return id.equals(sUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
