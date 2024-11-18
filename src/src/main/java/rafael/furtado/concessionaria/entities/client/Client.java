package rafael.furtado.concessionaria.entities.client;

import jakarta.persistence.*;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "interestedClients")
    private List<Vehicle> buyPretensions;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> purchasedVehicles;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Vehicle> getBuyPretensions() {
        return buyPretensions;
    }

    public void setBuyPretensions(List<Vehicle> buyPretensions) {
        this.buyPretensions = buyPretensions;
    }

    public List<Vehicle> getPurchasedVehicles() {
        return purchasedVehicles;
    }

    public void setPurchasedVehicles(List<Vehicle> purchasedVehicles) {
        this.purchasedVehicles = purchasedVehicles;
    }

}
