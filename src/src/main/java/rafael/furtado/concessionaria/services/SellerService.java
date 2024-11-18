package rafael.furtado.concessionaria.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rafael.furtado.concessionaria.repositories.SellerRepository;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;

    }


    public List<Seller> getSellers() {
        List<Seller> sellers = sellerRepository.findAll(Sort.by(Sort.Order.asc("name")));

        return sellers;
    }

    public Optional<Seller> getSellerById(Integer id) {
        Optional<Seller> seller = sellerRepository.findById(id);

        return seller;
    }

    public List<Seller> getVehicleSellers(VehicleType vehicleType) {
        List<Seller> sellers = sellerRepository.findBySpecializedVehicle(vehicleType);

        return sellers;
    }

    public Seller registerSeller(Seller seller) {
        Seller registeredSeller = sellerRepository.save(seller);

        return registeredSeller;
    }

    public Seller save(Seller seller) {
        Seller savedSeller = sellerRepository.save(seller);

        return savedSeller;
    }

    public Optional<Seller> findById(Integer sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);

        return seller;
    }

    public Seller deleteSeller(Integer sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);

        if (seller.isPresent()) {
            sellerRepository.deleteById(sellerId);

            return seller.get();
        } else {
            throw new IllegalArgumentException("Seller not found with the ID: " + sellerId);
        }

    }

    public Seller updateSeller(Seller seller) {
        Integer sellerId = seller.getId();

        Optional<Seller> optionalVehicleSeller = sellerRepository.findById(sellerId);

        if (optionalVehicleSeller.isPresent()) {
            Seller sellerToUpdate = optionalVehicleSeller.get();

            sellerToUpdate.setName(seller.getName() != null ? seller.getName() : sellerToUpdate.getName());
            sellerToUpdate.setAccumulatedSoldValue(seller.getAccumulatedSoldValue() != 0.0 ? seller.getAccumulatedSoldValue() : sellerToUpdate.getAccumulatedSoldValue());
            sellerToUpdate.setSpecializedVehicle(seller.getSpecializedVehicle() != null ? seller.getSpecializedVehicle() : sellerToUpdate.getSpecializedVehicle());

            Seller updatedClient = sellerRepository.save(sellerToUpdate);

            return updatedClient;
        } else {
            throw new IllegalArgumentException("Seller not found with the ID: " + sellerId);
        }

    }

    public void saveSellers(List<Seller> sellers) {
        sellerRepository.saveAll(sellers);

    }

}
