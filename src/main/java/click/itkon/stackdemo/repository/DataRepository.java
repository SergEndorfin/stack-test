package click.itkon.stackdemo.repository;

import click.itkon.stackdemo.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    @Query(value = "SELECT * FROM data ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Data> findLast();
}
