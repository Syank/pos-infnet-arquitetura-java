package rafael.furtado.concessionaria.feignClients;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import rafael.furtado.concessionaria.entities.client.Address;

@Service
public class ViaCEPClient {

    public static final String API_BASE_URL = "https://viacep.com.br/ws";

    public Address findAddressByCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();

        Address addressFound = restTemplate.getForEntity(API_BASE_URL + "/" + cep + "/json/", Address.class).getBody();

        return addressFound;
    }

}
