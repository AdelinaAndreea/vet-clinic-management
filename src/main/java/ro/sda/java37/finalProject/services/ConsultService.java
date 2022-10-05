package ro.sda.java37.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.ConsultDto;
import ro.sda.java37.finalProject.entities.Consult;
import ro.sda.java37.finalProject.entities.Pet;
import ro.sda.java37.finalProject.entities.Veterinarian;
import ro.sda.java37.finalProject.exceptions.EntityNotFoundError;
import ro.sda.java37.finalProject.repository.ConsultRepository;
import ro.sda.java37.finalProject.repository.PetRepository;
import ro.sda.java37.finalProject.repository.VeterinarianRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultService {


    private ConsultRepository consultRepository;
    private VeterinarianRepository veterinarianRepository;
    private PetRepository petRepository;
    private ConsultMapper consultMapper;

    public List<ConsultDto> getAllConsults() {
        return consultRepository.findAll().stream().map(e -> consultMapper.convertToDto(e)).collect(Collectors.toList());
    }

    public ConsultDto createConsult(ConsultDto consultDto) {
        Consult consult = consultMapper.convertToEntity(consultDto);
        consult = consultRepository.save(consult);
        ConsultDto result = consultMapper.convertToDto(consult);
        return result;

    }

    public List<ConsultDto> getAllConsultsByPetId(Long petId) {
        List<Consult> consults = consultRepository.findAllByPetId(petId);
        return consults.stream().map(consult -> consultMapper.convertToDto(consult)).collect(Collectors.toList());
    }

    public List<ConsultDto> getAllConsultsByVeterinarianId(Long vetId) {
        List<Consult> consults = consultRepository.findAllByVeterinarianId(vetId);
        return consults.stream().map(consult -> consultMapper.convertToDto(consult)).collect(Collectors.toList());
    }

    public ConsultDto updateConsult(ConsultDto consultDto) {
      Consult consultEntity = consultRepository.findById(consultDto.getId()).orElseThrow(()-> new EntityNotFoundError("Entity not found"));
      if (consultDto.getVeterinarianId() != null) {
        Veterinarian veterinarian = veterinarianRepository.getById(consultDto.getVeterinarianId());
        consultEntity.setVeterinarian(veterinarian);
      }
      if (consultDto.getPetId() != null) {
        Pet pet = petRepository.getById(consultDto.getPetId());
        pet.getConsults().add(consultEntity);
        consultEntity.setPet(pet);
      }
      consultEntity.setDate(consultDto.getDate());
      consultEntity.setDescription(consultDto.getDescription());
      consultEntity.setPrice(consultDto.getPrice());
      consultRepository.save(consultEntity);
    return consultMapper.convertToDto(consultEntity);
    }
}
