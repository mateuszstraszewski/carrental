package pl.straszewski.carrental.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private String regNumber;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date creationDate;

    @Column(columnDefinition = "boolean default true")
    private Boolean availStatus = true;

}
