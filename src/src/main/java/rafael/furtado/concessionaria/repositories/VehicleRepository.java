package rafael.furtado.concessionaria.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    public List<Vehicle> findByType(VehicleType type, Sort sort);

    public List<Vehicle> findByPriceLessThanEqual(double maximumPrice, Sort sort);

    public List<Vehicle> findByPriceLessThanEqualAndType(double maximumPrice, VehicleType type, Sort sort);

    public List<Vehicle> findBySoldFalse(Sort sort);

    public List<Vehicle> findBySoldFalseAndType(VehicleType type, Sort sort);

}
