package ro.sda.java37.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.ConsultDto;
import ro.sda.java37.finalProject.entities.Consult;
import ro.sda.java37.finalProject.entities.Veterinarian;
import ro.sda.java37.finalProject.repository.ConsultRepository;
import ro.sda.java37.finalProject.repository.PetRepository;
import ro.sda.java37.finalProject.repository.VeterinarianRepository;

@Service

public class ConsultMapper implements Mapper<Consult, ConsultDto> {

  @Autowired
  private ConsultRepository consultRepository;


  @Autowired
  private PetMapper petMapper;

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private VeterinarianMapper veterinarianMapper;

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @Override
  public ConsultDto convertToDto(Consult consult) {
    ConsultDto consultDto = new ConsultDto();
    consultDto.setId(consult.getId());
    consultDto.setDate(consult.getDate());
    consultDto.setDescription(consult.getDescription());
    consultDto.setPrice(consult.getPrice());
    if (consult.getPet() != null) {
      consultDto.setPet(petMapper.convertToDto(consult.getPet()));
      consultDto.setPetId(consult.getPet().getId());
    }
    if (consult.getVeterinarian() != null) {
      consultDto.setVeterinarian(veterinarianMapper.convertToDto(consult.getVeterinarian()));
      consultDto.setVeterinarianId(consult.getVeterinarian().getId());
    }


    return consultDto;

  }

  @Override
  public Consult convertToEntity(ConsultDto consultDto) {
    Consult consult = new Consult();
    consult.setId(consultDto.getId());
    consult.setDate(consultDto.getDate());
    consult.setDescription(consultDto.getDescription());
    consult.setPrice(consultDto.getPrice());

    if (consultDto.getVeterinarianId() != null) {
      Veterinarian veterinarian = veterinarianRepository.getById(consultDto.getVeterinarianId());
      consult.setVeterinarian(veterinarian);
    }

    if (consultDto.getPetId() != null) {
      consult.setPet(petRepository.getById(consultDto.getPetId()));
    }
    return consult;

  }
}
