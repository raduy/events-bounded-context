package agh.bit.eventsbc.domain.common;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by novy on 08.01.15.
 */
public abstract class IdentifiedDomainAggregateRoot<ID extends DomainIdentifier>
        extends AbstractAnnotatedAggregateRoot implements IdentifiedObject<ID> {

    @AggregateIdentifier
    protected ID id;

    public ID id() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !this.getClass().isInstance(o)) return false;

        IdentifiedDomainAggregateRoot that = (IdentifiedDomainAggregateRoot) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
