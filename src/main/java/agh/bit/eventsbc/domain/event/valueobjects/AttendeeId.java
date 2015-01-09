package agh.bit.eventsbc.domain.event.valueobjects;

public class AttendeeId {
    public final Long id;

    public AttendeeId( Long id ) {
        this.id = id;
    }

    public static AttendeeId valueOf( Long id ) {
        return new AttendeeId( id );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendeeId that = (AttendeeId) o;

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
