package br.com.macedo.repository;

import br.com.macedo.domain.aggregate.StatusEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class StatusRepository implements PanacheRepository<StatusEntity> {

    public Optional<StatusEntity> findByNomeStatus(String nomeStatus) {
        return find("nomeStatus", nomeStatus).firstResultOptional();
    }
}
