package ro.sda.java37.finalProject.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    //List<Pet> petList;
}
