package rafael.furtado.concessionaria.entities.vehicles.aeronauticVehicle;

import jakarta.persistence.*;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;

@Entity
@Table(name = "aeronautic_vehicles")
public class AeronauticVehicle extends Vehicle {

    @Column
    private AeronauticVehicleCategory category;

    @Column
    private int maxMetersAltitude;

    @Column(nullable = false)
    private int maxKilogramsWeight;


    public AeronauticVehicleCategory getCategory() {
        return category;
    }

    public void setCategory(AeronauticVehicleCategory category) {
        this.category = category;
    }

    public int getMaxMetersAltitude() {
        return maxMetersAltitude;
    }

    public void setMaxMetersAltitude(int maxMetersAltitude) {
        this.maxMetersAltitude = maxMetersAltitude;
    }

    public int getMaxKilogramsWeight() {
        return maxKilogramsWeight;
    }

    public void setMaxKilogramsWeight(int maxKilogramsWeight) {
        this.maxKilogramsWeight = maxKilogramsWeight;
    }

}
