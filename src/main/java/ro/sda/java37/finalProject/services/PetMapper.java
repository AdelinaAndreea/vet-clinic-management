package ro.sda.java37.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.PetDto;
import ro.sda.java37.finalProject.entities.Client;
import ro.sda.java37.finalProject.entities.Pet;
import ro.sda.java37.finalProject.repository.ClientRepository;

@Service
public class PetMapper implements Mapper<Pet, PetDto> {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;

    @Override
    public PetDto convertToDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        petDto.setRace(pet.getRace());
        petDto.setDateOfBirth(pet.getDateOfBirth());
        petDto.setKilos(pet.getKilos());
        petDto.setIsVaccinated(pet.getIsVaccinated());
        if (pet.getOwner() != null) {
            petDto.setOwner(clientMapper.convertToDto(pet.getOwner()));
            petDto.setOwnerId(pet.getOwner().getId());
        }


        return petDto;
    }

    @Override
    public Pet convertToEntity(PetDto petDto) {
        Pet pet = new Pet();
        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        pet.setRace(petDto.getRace());
        pet.setDateOfBirth(petDto.getDateOfBirth());
        pet.setKilos(petDto.getKilos());
        pet.setIsVaccinated(petDto.getIsVaccinated());
        if (petDto.getOwnerId() != null) {
            Client client = clientRepository.getById(petDto.getOwnerId());
            pet.setOwner(client);
            
        }


        return pet;
    }
}
