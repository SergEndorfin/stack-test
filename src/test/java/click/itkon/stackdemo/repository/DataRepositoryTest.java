package click.itkon.stackdemo.repository;

import click.itkon.stackdemo.entity.Data;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static click.itkon.stackdemo.repository.DataRepositoryActions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataRepositoryTest {

    @Autowired
    private DataRepository dataRepository;

    private final String firstInStack = "111";
    private final String secondInStack = "222";

    private static int remainingItemsInStack;

    @BeforeAll
    static void beforeAll() {
        remainingItemsInStack = 2;
    }

    @Test
    @Order(1)
    @Rollback(false)
    void save_2_items() {
        pushAndCheckIfAdded(new Data(firstInStack))
                .andThen(pushAndCheckIfAdded(new Data(secondInStack)))
                .accept(dataRepository);
    }

    @Test
    @Order(2)
    void findLast_secondItem() {
        getLastAddedItemAndCheckValue(secondInStack)
                .andThen(checkIfTotalNumberOfItemsIs(remainingItemsInStack--))
                .accept(dataRepository);
    }

    @Test
    @Order(3)
    @Rollback(false)
    void findLast_firstItem() {
        removeLastAddedItem()
                .andThen(checkIfTotalNumberOfItemsIs(remainingItemsInStack--))
                .andThen(getLastAddedItemAndCheckValue(firstInStack))
                .accept(dataRepository);
    }

    @Test
    @Order(4)
    void findLast_whenItemNotExists() {
        removeLastAddedItem()
                .andThen(checkIfTotalNumberOfItemsIs(remainingItemsInStack))
                .andThen(checkIfItemNotExists())
                .accept(dataRepository);
    }
}
