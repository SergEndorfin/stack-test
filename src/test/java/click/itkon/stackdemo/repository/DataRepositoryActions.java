package click.itkon.stackdemo.repository;

import click.itkon.stackdemo.entity.Data;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public final class DataRepositoryActions {

    public static Consumer<DataRepository> pushAndCheckIfAdded(Data data) {
        return repository -> assertNotNull(repository.save(data));
    }

    public static Consumer<DataRepository> getLastAddedItemAndCheckValue(String data) {
        return repository -> {
            Optional<Data> last = repository.findLast();
            assertTrue(last.isPresent());
            assertEquals(data, last.get().getValue());
        };
    }

    public static Consumer<DataRepository> checkIfTotalNumberOfItemsIs(long count) {
        return CrudRepository::count;
    }

    public static Consumer<DataRepository> removeLastAddedItem() {
        return repository -> repository.delete(repository.findLast().orElse(new Data()));
    }

    public static Consumer<DataRepository> checkIfItemNotExists() {
        return repository -> assertFalse(repository.findLast().isPresent());
    }
}
