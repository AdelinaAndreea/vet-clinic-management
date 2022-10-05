package ro.sda.java37.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.java37.finalProject.dto.ClientDto;
import ro.sda.java37.finalProject.dto.PetDto;
import ro.sda.java37.finalProject.entities.Client;
import ro.sda.java37.finalProject.entities.Pet;
import ro.sda.java37.finalProject.exceptions.EntityNotFoundError;
import ro.sda.java37.finalProject.repository.ClientRepository;
import ro.sda.java37.finalProject.repository.PetRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private ClientMapper clientMapper;
  @Autowired
  private PetRepository petRepository;
  @Autowired
  private PetMapper petMapper;


  public ClientDto getClientById(Long id) {
    Optional<Client> clientOptional = clientRepository.findById(id);
    if (clientOptional.isPresent()) {
      Client client = clientOptional.get();
      return clientMapper.convertToDto(client);
    } else {
      throw new EntityNotFoundError("Client with id " + id + " not found");
    }
  }

  public List<ClientDto> getAllClients() {
    return clientRepository.findAll().stream()
      .map(e -> clientMapper.convertToDto(e))
      .collect(Collectors.toList());
  }

  public ClientDto createClient(ClientDto clientDto) {
    Client client = clientMapper.convertToEntity(clientDto);
    client = clientRepository.save(client);
    ClientDto result = clientMapper.convertToDto(client);
    return result;
  }

  public ClientDto updateClient(ClientDto clientDto) {
    Client clientEntity = clientRepository.findById(clientDto.getId()).orElseThrow(() -> new EntityNotFoundError("Entity not found"));
    clientEntity.setFirstName(clientDto.getFirstName());
    clientEntity.setLastName(clientDto.getLastName());
    clientEntity.setEmail(clientEntity.getEmail());
    clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
    clientRepository.saveAndFlush(clientEntity);
    
    return clientMapper.convertToDto(clientEntity);
  }

  public void deleteClient(Long id) {
    clientRepository.deleteById(id);
  }

  public List<PetDto> getPetsByClientId(Long id) {
    List<Pet> myPets = petRepository.getAllByOwnerId(id);
    return myPets.stream()
      .map(pet -> petMapper.convertToDto(pet))
      .collect(Collectors.toList());
  }
}


