package rafael.furtado.concessionaria.loaders;

import org.springframework.stereotype.Component;
import rafael.furtado.concessionaria.entities.vehicles.FuelType;
import rafael.furtado.concessionaria.entities.vehicles.Vehicle;
import rafael.furtado.concessionaria.entities.vehicles.VehicleType;
import rafael.furtado.concessionaria.entities.vehicles.aeronauticVehicle.AeronauticVehicle;
import rafael.furtado.concessionaria.entities.vehicles.aeronauticVehicle.AeronauticVehicleCategory;
import rafael.furtado.concessionaria.entities.vehicles.marineVehicle.MarineVehicle;
import rafael.furtado.concessionaria.entities.vehicles.marineVehicle.MarineVehicleCategory;
import rafael.furtado.concessionaria.entities.vehicles.terrestrialVehicle.RoadType;
import rafael.furtado.concessionaria.entities.vehicles.terrestrialVehicle.TerrestrialVehicle;
import rafael.furtado.concessionaria.entities.vehicles.terrestrialVehicle.TerrestrialVehicleCategory;
import rafael.furtado.concessionaria.services.VehicleService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehiclesLoader implements DataLoader {

    private VehicleService vehicleService;


    public VehiclesLoader(VehicleService vehicleService) {
        this.vehicleService = vehicleService;

    }


    @Override
    public void loadData() throws IOException {
        FileReader file = new FileReader("data/vehicles.txt");

        BufferedReader reader = new BufferedReader(file);

        String line = reader.readLine();

        List<Vehicle> vehicles = new ArrayList<>();

        while (line != null) {
            String[] data = line.split(";");

            VehicleType type = VehicleType.valueOf(data[0]);

            Vehicle vehicle;

            switch (type) {
                case TERRESTRIAL:
                    vehicle = loadTerrestrialVehicle(data);

                    break;
                case MARINE:
                    vehicle = loadMarineVehicle(data);

                    break;
                case AERONAUTIC:
                    vehicle = loadAeronauticVehicle(data);

                    break;
                default:
                    throw new RuntimeException("Invalid vehicle type: " + type);
            }

            vehicles.add(vehicle);

            line = reader.readLine();

        }

        vehicleService.saveVehicles(vehicles);

    }

    private Vehicle loadTerrestrialVehicle(String[] data) {
        VehicleType type = VehicleType.TERRESTRIAL;

        double price = Double.parseDouble(data[1]);
        boolean sold = Boolean.parseBoolean(data[2]);
        int passengersCapacity = Integer.parseInt(data[3]);
        int kmPerLiter = Integer.parseInt(data[4]);

        List<FuelType> fuelTypes = loadFuelTypes(data[5]);

        TerrestrialVehicleCategory category = TerrestrialVehicleCategory.valueOf(data[6]);

        List<RoadType> roadTypes = new ArrayList<>();

        for (String roadType : data[7].split(",")) {
            roadTypes.add(RoadType.valueOf(roadType));

        }

        TerrestrialVehicle vehicle = new TerrestrialVehicle();

        vehicle.setType(type);
        vehicle.setPrice(price);
        vehicle.setSold(sold);
        vehicle.setPassengersCapacity(passengersCapacity);
        vehicle.setKmPerLiter(kmPerLiter);
        vehicle.setFuelTypes(fuelTypes);
        vehicle.setCategory(category);
        vehicle.setRoadTypes(roadTypes);

        return vehicle;
    }

    private Vehicle loadMarineVehicle(String[] data) {
        VehicleType type = VehicleType.MARINE;

        double price = Double.parseDouble(data[1]);
        boolean sold = Boolean.parseBoolean(data[2]);
        int passengersCapacity = Integer.parseInt(data[3]);
        int kmPerLiter = Integer.parseInt(data[4]);

        List<FuelType> fuelTypes = loadFuelTypes(data[5]);

        MarineVehicleCategory category = MarineVehicleCategory.valueOf(data[6]);
        int maxKilogramsWeight = Integer.parseInt(data[7]);
        int maxMetersDepth = Integer.parseInt(data[8]);

        MarineVehicle vehicle = new MarineVehicle();

        vehicle.setType(type);
        vehicle.setPrice(price);
        vehicle.setSold(sold);
        vehicle.setPassengersCapacity(passengersCapacity);
        vehicle.setKmPerLiter(kmPerLiter);
        vehicle.setFuelTypes(fuelTypes);
        vehicle.setCategory(category);
        vehicle.setMaxKilogramsWeight(maxKilogramsWeight);
        vehicle.setMaxMetersDepth(maxMetersDepth);

        return vehicle;
    }

    private Vehicle loadAeronauticVehicle(String[] data) {
        VehicleType type = VehicleType.AERONAUTIC;

        double price = Double.parseDouble(data[1]);
        boolean sold = Boolean.parseBoolean(data[2]);
        int passengersCapacity = Integer.parseInt(data[3]);
        int kmPerLiter = Integer.parseInt(data[4]);

        List<FuelType> fuelTypes = loadFuelTypes(data[5]);

        AeronauticVehicleCategory category = AeronauticVehicleCategory.valueOf(data[6]);
        int maxMetersAltitude = Integer.parseInt(data[7]);
        int maxKilogramsWeight = Integer.parseInt(data[8]);

        AeronauticVehicle vehicle = new AeronauticVehicle();

        vehicle.setType(type);
        vehicle.setPrice(price);
        vehicle.setSold(sold);
        vehicle.setPassengersCapacity(passengersCapacity);
        vehicle.setKmPerLiter(kmPerLiter);
        vehicle.setFuelTypes(fuelTypes);
        vehicle.setCategory(category);
        vehicle.setMaxMetersAltitude(maxMetersAltitude);
        vehicle.setMaxKilogramsWeight(maxKilogramsWeight);

        return vehicle;
    }

    private List<FuelType> loadFuelTypes(String data) {
        List<FuelType> fuelTypes = new ArrayList<>();

        for (String fuelType : data.split(",")) {
            fuelTypes.add(FuelType.valueOf(fuelType));

        }

        return fuelTypes;
    }

}
