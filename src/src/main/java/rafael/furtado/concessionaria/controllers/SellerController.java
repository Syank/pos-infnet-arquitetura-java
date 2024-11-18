package rafael.furtado.concessionaria.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.services.SellerService;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<Seller> getSellers() {
        List<Seller> sellers = sellerService.getSellers();

        return sellers;
    }

    @GetMapping("/getSellerById")
    public ResponseEntity<Seller> getSellerById(@RequestParam Integer id) {
        Optional<Seller> seller = sellerService.getSellerById(id);

        if (seller.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(seller.get());
    }

    @GetMapping("/getVehicleSellers")
    public List<Seller> getVehicleSellers(@RequestParam VehicleType vehicleType) {
        List<Seller> sellers = sellerService.getVehicleSellers(vehicleType);

        return sellers;
    }

    @PostMapping("/registerSeller")
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
        Seller registeredSeller = sellerService.registerSeller(seller);

        return new ResponseEntity<>(registeredSeller, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSeller")
    public ResponseEntity<Seller> deleteSeller(@RequestParam Integer sellerId) {
        Seller deletedSeller = sellerService.deleteSeller(sellerId);

        return new ResponseEntity<>(deletedSeller, HttpStatus.OK);
    }

    @PutMapping("/updateSeller")
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(seller);

        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

}
