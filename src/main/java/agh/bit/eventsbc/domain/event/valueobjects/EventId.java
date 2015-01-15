package agh.bit.eventsbc.domain.event.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;

public class EventId implements DomainIdentifier {
    public final Long id;

    public EventId(Long id) {
        this.id = id;
    }

    public static EventId valueOf(Long id) {
        return new EventId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventId that = (EventId) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
