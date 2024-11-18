package rafael.furtado.concessionaria.entities.vehicles.terrestrialVehicle;

import jakarta.persistence.*;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;

import java.util.List;


@Entity
@Table(name = "terrestrial_vehicles")
public class TerrestrialVehicle extends Vehicle {

    @Column
    private TerrestrialVehicleCategory category;

    @Column
    private List<RoadType> roadTypes;


    public TerrestrialVehicleCategory getCategory() {
        return category;
    }

    public void setCategory(TerrestrialVehicleCategory category) {
        this.category = category;
    }

    public List<RoadType> getRoadTypes() {
        return roadTypes;
    }

    public void setRoadTypes(List<RoadType> roadTypes) {
        this.roadTypes = roadTypes;
    }

}
