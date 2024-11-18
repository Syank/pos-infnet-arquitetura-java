package rafael.furtado.concessionaria.entities.vehicles.marineVehicle;

import jakarta.persistence.*;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;

@Entity
@Table(name = "marine_vehicles")
public class MarineVehicle extends Vehicle {

    @Column
    private MarineVehicleCategory category;

    @Column(nullable = false)
    private int maxKilogramsWeight;

    @Column(nullable = false)
    private int maxMetersDepth;

    public MarineVehicleCategory getCategory() {
        return category;
    }

    public void setCategory(MarineVehicleCategory category) {
        this.category = category;
    }

    public int getMaxKilogramsWeight() {
        return maxKilogramsWeight;
    }

    public void setMaxKilogramsWeight(int maxKilogramsWeight) {
        this.maxKilogramsWeight = maxKilogramsWeight;
    }

    public int getMaxMetersDepth() {
        return maxMetersDepth;
    }

    public void setMaxMetersDepth(int maxMetersDepth) {
        this.maxMetersDepth = maxMetersDepth;
    }

}
