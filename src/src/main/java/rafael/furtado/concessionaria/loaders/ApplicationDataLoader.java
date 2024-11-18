package rafael.furtado.concessionaria.loaders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDataLoader implements CommandLineRunner {

    private SellersLoader sellersLoader;
    private ClientsLoader clientsLoader;
    private VehiclesLoader vehiclesLoader;


    public ApplicationDataLoader(SellersLoader sellersLoader, ClientsLoader clientsLoader, VehiclesLoader vehiclesLoader) {
        this.sellersLoader = sellersLoader;
        this.clientsLoader = clientsLoader;
        this.vehiclesLoader = vehiclesLoader;

    }

    @Override
    public void run(String... args) throws Exception {
        sellersLoader.loadData();
        clientsLoader.loadData();
        vehiclesLoader.loadData();

    }

}
