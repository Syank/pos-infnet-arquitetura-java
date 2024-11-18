package rafael.furtado.concessionaria.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rafael.furtado.concessionaria.entities.client.Client;
import rafael.furtado.concessionaria.exceptions.InvalidAddressException;
import rafael.furtado.concessionaria.services.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public List<Client> getClients() {
        List<Client> clients = clientService.getClients();

        return clients;
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<Client> getClientByCpf(@RequestParam String cpf) {
        Optional<Client> optionalClient = clientService.getClientByCpf(cpf);

        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findInterestedInVehicle")
    public List<Client> getInterestedInVehicle(@RequestParam Integer vehicleId) throws Exception {
        List<Client> clients = clientService.getInterestedInVehicle(vehicleId);

        return clients;
    }

    @GetMapping("/findVehicleOwner")
    public ResponseEntity<Client> getVehicleOwner(@RequestParam Integer vehicleId) {
        Optional<Client> optionalClient = clientService.getVehicleOwner(vehicleId);

        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/registerNewClient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> registerNewClient(@RequestBody Client client) throws InvalidAddressException {
        Client newClient = clientService.registerNewClient(client);

        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteClient")
    public ResponseEntity<Void> deleteClient(@RequestParam Integer clientId) {
        clientService.deleteClient(clientId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client updatedClient = clientService.updateClient(client);

        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

}
