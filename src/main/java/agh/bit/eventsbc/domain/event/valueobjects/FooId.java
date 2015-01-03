package agh.bit.eventsbc.domain.event.valueobjects;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class FooId {
    private final String id;

    public FooId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FooId fooId = (FooId) o;

        if (!id.equals(fooId.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "FooId{" +
                "id='" + id + '\'' +
                '}';
    }
}
