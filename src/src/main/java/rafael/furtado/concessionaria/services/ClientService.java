package rafael.furtado.concessionaria.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rafael.furtado.concessionaria.entities.client.Address;
import rafael.furtado.concessionaria.entities.client.Client;
import rafael.furtado.concessionaria.exceptions.InvalidAddressException;
import rafael.furtado.concessionaria.clients.ViaCEPClient;
import rafael.furtado.concessionaria.repositories.ClientRepository;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private VehicleService vehicleService;
    private ViaCEPClient viaCEPClient;


    public ClientService(ClientRepository clientRepository, VehicleService vehicleService, ViaCEPClient viaCEPClient) {
        this.clientRepository = clientRepository;
        this.vehicleService = vehicleService;
        this.viaCEPClient = viaCEPClient;

    }


    public List<Client> getClients() {
        List<Client> clients = clientRepository.findAll(Sort.by(Sort.Order.asc("name")));

        return clients;
    }

    public Optional<Client> getClientByCpf(String cpf) {
        Optional<Client> optionalClient = clientRepository.findByCpf(cpf);

        return optionalClient;
    }

    public List<Client> getInterestedInVehicle(Integer vehicleId) throws Exception {
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            List<Client> clients = vehicle.getInterestedClients();

            return clients;
        } else {
            throw new Exception("Vehicle not found with the ID: " + vehicleId);
        }

    }

    public Optional<Client> getVehicleOwner(Integer vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            Client owner = vehicle.getOwner();

            return Optional.of(owner);
        }

        return Optional.empty();
    }

    public Optional<Client> findById(Integer clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        return optionalClient;
    }

    public Client registerNewClient(Client client) throws InvalidAddressException {
        boolean isAddressValid = validateClientAddress(client.getAddress());

        if (!isAddressValid) {
            throw new InvalidAddressException("Endereço inválido para novo usuário.\nO CEP informado foi validado e não corresponde aos outros campos informados.");
        }

        Client newClient = clientRepository.save(client);

        return newClient;
    }

    public void deleteClient(Integer clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            clientRepository.delete(client);

        } else {
            throw new IllegalArgumentException("Client not found with the ID: " + clientId);
        }

    }

    public Client updateClient(Client client) {
        Integer clientId = client.getId();

        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isPresent()) {
            Client updatedClient = clientRepository.save(client);

            return updatedClient;
        } else {
            throw new IllegalArgumentException("Client not found with the ID: " + clientId);
        }

    }

    private boolean validateClientAddress(Address clientAddress) {
        String zipCode = clientAddress.getZipCode();

        Address foundAddress = viaCEPClient.findAddressByCep(zipCode);

        if (foundAddress == null) {
            return false;
        }

        if (!foundAddress.getCity().equals(clientAddress.getCity())) {
            return false;
        }

        if (!foundAddress.getState().equals(clientAddress.getState())) {
            return false;
        }

        if (!foundAddress.getStreet().equals(clientAddress.getStreet())) {
            return false;
        }

        if (!foundAddress.getNeighborhood().equals(clientAddress.getNeighborhood())) {
            return false;
        }

        return true;
    }

    public void saveClients(List<Client> clients) throws InvalidAddressException {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);

            registerNewClient(client);

        }

    }

}
