package agh.bit.eventsbc.infrastructure;

import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemIdProvider;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoItemIdProviderImplTest {

    private TodoItemIdProvider objectUnderTest = new TodoItemIdProviderImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionGivenNegativeValue() throws Exception {
        objectUnderTest.nextIdBatch(-5);
    }

    @Test
    public void shouldReturnUniqueIdBatchOfRequestedSize() throws Exception {

        final int expectedSize = 3;

        final List<TodoItemId> providedIds = objectUnderTest.nextIdBatch(expectedSize);
        final Set<TodoItemId> withoutAnyDuplicates = Sets.newHashSet(providedIds);

        assertThat(withoutAnyDuplicates).hasSize(expectedSize);
    }
}