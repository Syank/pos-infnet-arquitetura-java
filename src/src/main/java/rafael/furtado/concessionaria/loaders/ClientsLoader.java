package rafael.furtado.concessionaria.loaders;

import org.springframework.stereotype.Component;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.entities.client.Address;
import rafael.furtado.concessionaria.entities.client.Client;
import rafael.furtado.concessionaria.entities.client.Contact;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;
import rafael.furtado.concessionaria.services.ClientService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ClientsLoader implements DataLoader {

    private ClientService clientService;


    public ClientsLoader(ClientService clientService) {
        this.clientService = clientService;

    }


    @Override
    public void loadData() throws IOException {
        FileReader file = new FileReader("data/clients.txt");

        BufferedReader reader = new BufferedReader(file);

        String line = reader.readLine();

        List<Client> clients = new ArrayList<>();

        while (line != null) {
            String[] data = line.split(";");

            String name = data[0];
            String cpf = data[1];

            String contactEmail = data[2];
            String contactPhone = data[3];

            String addressStreet = data[4];
            String addressNumber = data[5];
            String addressNeighborhood = data[6];
            String addressCity = data[7];
            String addressState = data[8];
            String addressZipCode = data[9];
            String addressCountry = data[10];

            Contact contact = new Contact();

            contact.setEmail(contactEmail);
            contact.setPhone(contactPhone);

            Address address = new Address();

            address.setStreet(addressStreet);
            address.setNumber(addressNumber);
            address.setNeighborhood(addressNeighborhood);
            address.setCity(addressCity);
            address.setState(addressState);
            address.setZipCode(addressZipCode);
            address.setCountry(addressCountry);

            Client client = new Client();

            client.setName(name);
            client.setCpf(cpf);
            client.setContact(contact);
            client.setAddress(address);

            clients.add(client);

            line = reader.readLine();

        }

        try {
            clientService.saveClients(clients);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
