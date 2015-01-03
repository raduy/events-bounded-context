package agh.bit.eventsbc.domain.eventproposal.valueobject;

/**
 * Created by novy on 03.01.15.
 */
public class EventDescription {
    private final String description;

    private EventDescription(String description) {
        this.description = description;
    }

    public static EventDescription of(String description) { return new EventDescription(description); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventDescription that = (EventDescription) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }
}