package rafael.furtado.concessionaria.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;

@Entity
@Table(name = "vehicle_sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private VehicleType specializedVehicle;

    @NotNull
    @Column(nullable = false)
    private double accumulatedSoldValue;

    @JsonCreator
    public Seller(@JsonProperty("id") Integer id,
                  @JsonProperty("name") String name,
                  @JsonProperty("specializedVehicle") VehicleType specializedVehicle,
                  @JsonProperty("accumulatedSoldValue") double accumulatedSoldValue) {
        this.id = id;
        this.name = name;
        this.specializedVehicle = specializedVehicle;
        this.accumulatedSoldValue = accumulatedSoldValue;

    }

    public Seller() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getSpecializedVehicle() {
        return specializedVehicle;
    }

    public void setSpecializedVehicle(VehicleType specializedVehicle) {
        this.specializedVehicle = specializedVehicle;
    }

    public double getAccumulatedSoldValue() {
        return accumulatedSoldValue;
    }

    public void setAccumulatedSoldValue(double accumulatedSoldValue) {
        this.accumulatedSoldValue = accumulatedSoldValue;
    }

}
