package ro.sda.java37.finalProject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @NotNull
    private String phoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> petList = new ArrayList<>();

    public Client addPet(Pet pet) {
        petList.add(pet);
        pet.setOwner(this);
        return this;
    }

    public Client removePet(Pet pet) {
        petList.remove(pet);
        pet.setOwner(null);
        return this;
    }
}
