package rafael.furtado.concessionaria.loaders;

import org.springframework.stereotype.Component;
import rafael.furtado.concessionaria.entities.Seller;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;
import rafael.furtado.concessionaria.services.SellerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SellersLoader implements DataLoader {

    private SellerService sellerService;


    public SellersLoader(SellerService sellerService) {
        this.sellerService = sellerService;

    }


    @Override
    public void loadData() throws IOException {
        FileReader file = new FileReader("data/sellers.txt");

        BufferedReader reader = new BufferedReader(file);

        String line = reader.readLine();

        List<Seller> sellers = new ArrayList<>();

        while (line != null) {
            String[] data = line.split(";");

            String name = data[0];
            VehicleType vehicleType = VehicleType.valueOf(data[1]);
            double accumulatedSoldValue = Double.parseDouble(data[2]);

            Seller seller = new Seller(null, name, vehicleType, accumulatedSoldValue);

            sellers.add(seller);

            line = reader.readLine();

        }

        sellerService.saveSellers(sellers);

    }

}
