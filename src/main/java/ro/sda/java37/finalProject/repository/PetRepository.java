package ro.sda.java37.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.java37.finalProject.entities.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

  public List<Pet> getAllByOwnerId(Long id);

}
