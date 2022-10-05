package ro.sda.java37.finalProject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Consult {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "vet_id")
    private Veterinarian veterinarian;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    private Double price;


}
