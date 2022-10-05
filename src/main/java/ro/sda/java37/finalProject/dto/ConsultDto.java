package ro.sda.java37.finalProject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultDto {

    private Long id;
    private LocalDate date;
    private String description;
    private VeterinarianDto veterinarian;
    private Long veterinarianId;
    private PetDto pet;
    private Long petId;

    private Double price;

}
