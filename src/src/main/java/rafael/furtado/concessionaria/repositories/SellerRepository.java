package rafael.furtado.concessionaria.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    public List<Seller> findAll(Sort sort);
    public List<Seller> findBySpecializedVehicle(VehicleType vehicleType);

}
