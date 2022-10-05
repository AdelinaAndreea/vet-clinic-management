package ro.sda.java37.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.java37.finalProject.entities.Consult;

import java.util.List;

public interface ConsultRepository extends JpaRepository<Consult, Long> {

    List<Consult> findAllByPetId(Long petId);

    List<Consult> findAllByVeterinarianId(Long vetId);


}
