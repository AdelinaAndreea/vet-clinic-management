package ro.sda.java37.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.VeterinarianDto;
import ro.sda.java37.finalProject.entities.Veterinarian;

@Service
@AllArgsConstructor
public class VeterinarianMapper implements Mapper<Veterinarian, VeterinarianDto> {

    @Override
    public VeterinarianDto convertToDto(Veterinarian veterinarian) {
        VeterinarianDto veterinarianDto = new VeterinarianDto();
        veterinarianDto.setId(veterinarian.getId());
        veterinarianDto.setFirstName(veterinarian.getFirstName());
        veterinarianDto.setLastName(veterinarian.getLastName());
        veterinarianDto.setSpeciality(veterinarian.getSpeciality());
        return veterinarianDto;

    }

    @Override
    public Veterinarian convertToEntity(VeterinarianDto veterinarianDto) {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setId(veterinarianDto.getId());
        veterinarian.setFirstName(veterinarianDto.getFirstName());
        veterinarian.setLastName(veterinarianDto.getLastName());
        veterinarian.setSpeciality(veterinarianDto.getSpeciality());
        return veterinarian;
    }
}
