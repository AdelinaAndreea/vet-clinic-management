package ro.sda.java37.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.java37.finalProject.dto.VeterinarianDto;
import ro.sda.java37.finalProject.services.VeterinarianService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/veterinarian", produces = "application/json")
public class VeterinarianRestController {
    @Autowired
    private VeterinarianService veterinarianService;

    @GetMapping()
    public List<VeterinarianDto> getAllVeterinarians() {
        return veterinarianService.getAll();
    }

    @PostMapping()
    public VeterinarianDto createVeterinarian(@RequestBody VeterinarianDto veterinarianDto) {
        return veterinarianService.createVeterirarian(veterinarianDto);
    }

    @PutMapping()
    public VeterinarianDto updateVeterinarian(@RequestBody VeterinarianDto veterinarianDto) {
        return veterinarianService.updateVeterirarian(veterinarianDto);
    }

    @DeleteMapping("{id}")
    public void deleteVeterinarian(@PathVariable Long id) {
        veterinarianService.deleteVeterinarian(id);
    }


}
