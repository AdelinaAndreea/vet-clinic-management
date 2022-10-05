package ro.sda.java37.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.java37.finalProject.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
