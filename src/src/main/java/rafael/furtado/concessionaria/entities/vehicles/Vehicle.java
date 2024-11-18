package rafael.furtado.concessionaria.entities.vehicles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import rafael.furtado.concessionaria.entities.client.Client;

import java.util.List;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private VehicleType type;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int passengersCapacity;

    @Column(nullable = false)
    private boolean sold;

    @Column(nullable = false)
    private int kmPerLiter;

    @Column(nullable = false)
    private List<FuelType> fuelTypes;

    @ManyToOne
    @JsonIgnore
    private Client owner;

    @ManyToMany
    @JsonIgnore
    private List<Client> interestedClients;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getKmPerLiter() {
        return kmPerLiter;
    }

    public void setKmPerLiter(int kmPerLiter) {
        this.kmPerLiter = kmPerLiter;
    }

    public List<FuelType> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<FuelType> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public List<Client> getInterestedClients() {
        return interestedClients;
    }

    public void setInterestedClients(List<Client> interestedClients) {
        this.interestedClients = interestedClients;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

}
