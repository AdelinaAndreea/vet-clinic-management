package ro.sda.java37.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.java37.finalProject.dto.ClientDto;
import ro.sda.java37.finalProject.dto.PetDto;
import ro.sda.java37.finalProject.services.ClientService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/client", produces = "application/json")
public class ClientRestController {
  @Autowired
  private ClientService clientService;

  @GetMapping()
  public List<ClientDto> getAllClients() {
    List<ClientDto> response = clientService.getAllClients();
    System.out.println("aaa" + response);
    return response;
  }

  @GetMapping("/{id}")
  public ClientDto getClientById(@PathVariable Long id) {
    ClientDto response = clientService.getClientById(id);
    System.out.println("aaa" + response);
    return response;
  }

  @GetMapping("/{id}/pets")
  public List<PetDto> getPets(@PathVariable Long id) {
    return clientService.getPetsByClientId(id);

  }

  @PostMapping
  public ClientDto createClient(@RequestBody ClientDto clientDto) {
    return clientService.createClient(clientDto);
  }

  @PutMapping
  public ClientDto updateClient(@RequestBody ClientDto clientDto) {
    return clientService.updateClient(clientDto);

  }

  @DeleteMapping("/{id}")
  public void deleteClient(@PathVariable Long id) {
    clientService.deleteClient(id);
  }

}
