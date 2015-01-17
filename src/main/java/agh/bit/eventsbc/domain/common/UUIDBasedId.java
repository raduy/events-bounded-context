package agh.bit.eventsbc.domain.common;

import java.util.UUID;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class UUIDBasedId implements DomainIdentifier {
    private final String id;

    public UUIDBasedId() {
        this.id = randomUUIDString();
    }

    public UUIDBasedId(String id) {
        this.id = id;
    }

    private String randomUUIDString() {
        return UUID.randomUUID().toString();
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UUIDBasedId that = (UUIDBasedId) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "UUIDBasedId{" +
                "id='" + id + '\'' +
                '}';
    }
}
