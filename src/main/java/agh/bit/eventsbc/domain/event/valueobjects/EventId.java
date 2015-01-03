package agh.bit.eventsbc.domain.event.valueobjects;

public class EventId {
    public final long id;

    public EventId(long id) {
        this.id = id;
    }

    public static EventId valueOf(long id) {
        return new EventId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventId eventId = (EventId) o;

        if (id != eventId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
