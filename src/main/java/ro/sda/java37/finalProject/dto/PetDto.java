package ro.sda.java37.finalProject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetDto {
    private Long id;
    private String name;
    private String race;
    private LocalDate dateOfBirth;
    private Float kilos;
    private Boolean isVaccinated;
    private ClientDto owner;
    private Long ownerId;

}
