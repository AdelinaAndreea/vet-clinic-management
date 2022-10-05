package ro.sda.java37.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.PetDto;
import ro.sda.java37.finalProject.entities.Client;
import ro.sda.java37.finalProject.entities.Pet;
import ro.sda.java37.finalProject.exceptions.EntityNotFoundError;
import ro.sda.java37.finalProject.repository.ClientRepository;
import ro.sda.java37.finalProject.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService {
  @Autowired
  private PetRepository petRepository;
  @Autowired
  private PetMapper petMapper;

  @Autowired
  private ClientRepository clientRepository;

  public List<PetDto> getAllPets() {
    return petRepository.findAll().stream().map(e -> petMapper.convertToDto(e)).collect(Collectors.toList());

  }

  public PetDto createPet(PetDto petDto) {
    Pet pet = petMapper.convertToEntity(petDto);
    pet = petRepository.save(pet);
    PetDto result = petMapper.convertToDto(pet);
    return result;
  }

  public void deletePet(Long id) {
    Pet pet = petRepository.findById(id).orElseThrow(() -> new EntityNotFoundError("Pet not found"));
    Client client = pet.getOwner();
    client.removePet(pet);
    petRepository.delete(pet);
  }

  public PetDto updatePet(PetDto petDto) {
    Pet petEntity = petRepository.findById(petDto.getId()).orElseThrow(() -> new EntityNotFoundError("Entity not found"));
    petEntity.setName(petDto.getName());
    petEntity.setRace(petDto.getRace());
    petEntity.setDateOfBirth(petDto.getDateOfBirth());
    petEntity.setIsVaccinated(petDto.getIsVaccinated());
    petEntity.setKilos(petDto.getKilos());
    if (petDto.getOwnerId() != null) {
      Client client = clientRepository.getById(petDto.getOwnerId());
      petEntity.setOwner(client);
    }
    petRepository.save(petEntity);
    return petMapper.convertToDto(petEntity);

  }
}
