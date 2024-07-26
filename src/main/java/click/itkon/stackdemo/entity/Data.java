package click.itkon.stackdemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "`key`", nullable = false)
    private String key;

    @Column(name = "`value`" ,nullable = false)
    private String value;
}
