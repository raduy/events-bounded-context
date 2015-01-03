package agh.bit.eventsbc.domain.eventproposal.valueobject;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposalId {

    private final String id;

    private EventProposalId(String id) {
        this.id = id;
    }

    public static EventProposalId of(String id) {
        return new EventProposalId(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventProposalId that = (EventProposalId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventProposalId{" +
                "id='" + id + '\'' +
                '}';
    }
}
