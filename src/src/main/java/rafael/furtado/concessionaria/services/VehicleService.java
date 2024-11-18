package rafael.furtado.concessionaria.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rafael.furtado.concessionaria.entities.client.Client;
import rafael.furtado.concessionaria.repositories.VehicleRepository;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private ClientService clientService;
    private SellerService sellerService;


    public VehicleService(VehicleRepository vehicleRepository, SellerService sellerService) {
        this.vehicleRepository = vehicleRepository;
        this.sellerService = sellerService;

    }

    @Autowired
    public void setClientService(@Lazy ClientService clientService) {
        this.clientService = clientService;

    }


    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(VehicleType type) {
        List<Vehicle> vehicles = vehicleRepository.findByType(type, Sort.by(Sort.Order.asc("price")));

        return vehicles;
    }

    public List<Vehicle> filterByPriceAndVehicleType(double maximumPrice, Optional<VehicleType> type) {
        List<Vehicle> vehicles;

        if (type.isEmpty()) {
            vehicles = vehicleRepository.findByPriceLessThanEqual(maximumPrice, Sort.by(Sort.Order.asc("price")));

        } else {
            vehicles = vehicleRepository.findByPriceLessThanEqualAndType(maximumPrice, type.get(), Sort.by(Sort.Order.asc("price")));

        }

        return vehicles;
    }

    public List<Vehicle> getUnsoldVehicles(Optional<VehicleType> type) {
        List<Vehicle> vehicles;

        if (type.isEmpty()) {
            vehicles = vehicleRepository.findBySoldFalse(Sort.by(Sort.Order.asc("price")));

        } else {
            vehicles = vehicleRepository.findBySoldFalseAndType(type.get(), Sort.by(Sort.Order.asc("price")));

        }

        return vehicles;
    }

    public Vehicle registerNewVehicle(Vehicle vehicle) {
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        return newVehicle;
    }

    public void sellVehicle(Integer vehicleId, Integer clientId, Integer sellerId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        Optional<Client> optionalClient = clientService.findById(clientId);
        Optional<Seller> optionalVehicleSeller = sellerService.findById(sellerId);

        validateSellEntities(optionalVehicle, optionalClient, optionalVehicleSeller);

        Vehicle vehicle = optionalVehicle.get();
        Client client = optionalClient.get();
        Seller seller = optionalVehicleSeller.get();

        vehicle.setSold(true);
        vehicle.setOwner(client);

        seller.setAccumulatedSoldValue(seller.getAccumulatedSoldValue() + vehicle.getPrice());

        vehicleRepository.save(vehicle);
        sellerService.save(seller);

    }

    private void validateSellEntities(Optional<Vehicle> vehicle, Optional<Client> client, Optional<Seller> seller) {
        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found");

        } else if (client.isEmpty()) {
            throw new IllegalArgumentException("Client not found");

        } else if (seller.isEmpty()) {
            throw new IllegalArgumentException("Seller not found");

        }

    }

    public Vehicle deleteVehicle(Integer vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            vehicleRepository.delete(vehicle);

            return vehicle;
        } else {
            throw new IllegalArgumentException("Vehicle not found with the ID: " + vehicleId);
        }

    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        Integer vehicleId = vehicle.getId();

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);

            return updatedVehicle;
        } else {
            throw new IllegalArgumentException("Vehicle not found with the ID: " + vehicleId);
        }

    }

    public Optional<Vehicle> getVehicleById(Integer vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        return vehicle;
    }

    public void saveVehicles(List<Vehicle> vehicles) {
        vehicleRepository.saveAll(vehicles);

    }

}
