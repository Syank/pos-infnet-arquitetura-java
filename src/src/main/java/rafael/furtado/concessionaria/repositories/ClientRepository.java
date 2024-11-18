package rafael.furtado.concessionaria.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rafael.furtado.concessionaria.entities.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    public List<Client> findAll(Sort sort);

    public Optional<Client> findByCpf(String cpf);

}
