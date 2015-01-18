package agh.bit.eventsbc.query.eventproposal.repositories;

import agh.bit.eventsbc.query.eventproposal.model.EventProposalBasicView;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface EventProposalJpaRepository extends
        CrudRepository<EventProposalBasicView, Long> {
}
