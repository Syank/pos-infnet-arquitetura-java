package rafael.furtado.concessionaria.entities.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias("logradouro")
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @JsonAlias("bairro")
    @Column(nullable = false)
    private String neighborhood;

    @JsonAlias("localidade")
    @Column(nullable = false)
    private String city;

    @JsonAlias("uf")
    @Column(nullable = false)
    private String state;

    @JsonAlias("cep")
    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String country;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Client client;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
