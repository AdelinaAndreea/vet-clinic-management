package ro.sda.java37.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.java37.finalProject.dto.ConsultDto;
import ro.sda.java37.finalProject.services.ConsultService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/consult", produces = "application/json")
public class ConsultRestController {
  @Autowired
  private ConsultService consultService;

  @GetMapping()
  public List<ConsultDto> getAllConsults() {
    return consultService.getAllConsults();
  }

  @GetMapping("/pet/{petId}")
  public List<ConsultDto> getAllConsultsByPetId(@PathVariable Long petId) {
    return consultService.getAllConsultsByPetId(petId);
  }

  @GetMapping("/veterinarian/{vetId}")
  public List<ConsultDto> getAllConsultsByVeterinarianId(@PathVariable Long vetId) {
    return consultService.getAllConsultsByVeterinarianId(vetId);
  }


  @PostMapping
  public ConsultDto createConsult(@RequestBody ConsultDto consultDto) {
    return consultService.createConsult(consultDto);
  }

  @PutMapping()
  public ConsultDto updateConsult(@RequestBody ConsultDto consultDto) {
    return consultService.updateConsult(consultDto);
  }
}
