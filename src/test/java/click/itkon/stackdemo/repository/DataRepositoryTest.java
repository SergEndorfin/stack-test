package click.itkon.stackdemo.repository;

import click.itkon.stackdemo.entity.Data;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataRepositoryTest {

    @Autowired
    private DataRepository dataRepository;

    private final String firstInStack = "111";
    private final String secondInStack = "222";

    @Test
    @Order(1)
    @Rollback(false)
    void save_2_items() {
        assertNotNull(dataRepository.save(Data.builder().value(firstInStack).build()));
        assertNotNull(dataRepository.save(Data.builder().value(secondInStack).build()));
    }

    @Test
    @Order(2)
    void findLast_secondItem() {
        Optional<Data> last = dataRepository.findLast();
        assertTrue(last.isPresent());
        assertEquals(secondInStack, last.get().getValue());
        assertEquals(2, dataRepository.count());
    }

    @Test
    @Order(3)
    @Rollback(false)
    void findLast_firstItem() {
        dataRepository.delete(dataRepository.findLast().get());
        Data last = dataRepository.findLast().get();
        assertTrue(dataRepository.findLast().isPresent());
        assertEquals(firstInStack, last.getValue());
    }

    @Test
    @Order(4)
    void findLast_whenItemNotExists() {
        dataRepository.delete(dataRepository.findLast().get());
        assertFalse(dataRepository.findLast().isPresent());
    }
}
