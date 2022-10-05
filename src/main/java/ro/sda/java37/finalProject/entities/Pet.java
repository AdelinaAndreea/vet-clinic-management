package ro.sda.java37.finalProject.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String race;
    private LocalDate dateOfBirth;
    private Float kilos;
    private Boolean isVaccinated;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Client owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Consult> consults;

}
