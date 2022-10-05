package ro.sda.java37.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.java37.finalProject.dto.PetDto;
import ro.sda.java37.finalProject.services.PetService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pet", produces = "application/json")
public class PetRestController {
  @Autowired
  private PetService petService;

  @GetMapping()
  public List<PetDto> getAllPets() {
    return petService.getAllPets();
  }

  @PostMapping
  public PetDto createPet(@RequestBody PetDto petDto) {
    return petService.createPet(petDto);
  }

  @PutMapping()
  public PetDto updatePet(@RequestBody PetDto petDto) {
    System.out.println("updatePet" + petDto);
    return petService.updatePet(petDto);
  }

  @DeleteMapping("/{id}")
  public void deletePet(@PathVariable Long id) {
    petService.deletePet(id);
  }

}
