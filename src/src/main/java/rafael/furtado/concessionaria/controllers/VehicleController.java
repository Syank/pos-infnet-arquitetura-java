package rafael.furtado.concessionaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rafael.furtado.concessionaria.services.VehicleService;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();

        return vehicles;
    }

    @GetMapping("/findByType")
    public List<Vehicle> getVehiclesByType(@RequestParam VehicleType type) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(type);

        return vehicles;
    }

    @GetMapping("/filterByPrice")
    public List<Vehicle> getVehiclesByPrice(@RequestParam double maximumPrice, @RequestParam Optional<VehicleType> type) {
        List<Vehicle> vehicles = vehicleService.filterByPriceAndVehicleType(maximumPrice, type);

        return vehicles;
    }

    @GetMapping("/getUnsoldVehicles")
    public List<Vehicle> getUnsoldVehicles(@RequestParam Optional<VehicleType> type) {
        List<Vehicle> vehicles = vehicleService.getUnsoldVehicles(type);

        return vehicles;
    }

    @PostMapping("/registerNewVehicle")
    public ResponseEntity<Vehicle> registerNewVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.registerNewVehicle(vehicle);

        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @PostMapping("/sellVehicle")
    public ResponseEntity<Void> sellVehicle(@RequestParam Integer vehicleId, @RequestParam Integer clientId,
                                            @RequestParam Integer sellerId) {
        vehicleService.sellVehicle(vehicleId, clientId, sellerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteVehicle")
    public ResponseEntity<Vehicle> deleteVehicle(@RequestParam Integer vehicleId) {
        Vehicle deletedVehicle = vehicleService.deleteVehicle(vehicleId);

        return new ResponseEntity<>(deletedVehicle, HttpStatus.OK);
    }

    @PutMapping("/updateVehicle")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);

        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

}
