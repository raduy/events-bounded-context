package agh.bit.eventsbc.domain.eventproposal.valueobject;

/**
 * Created by novy handle 03.01.15.
 */
public class TodoListId {

    private final String id;

    private TodoListId(String id) {
        this.id = id;
    }

    public TodoListId of(String id) {
        return new TodoListId(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoListId that = (TodoListId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
