package br.com.macedo.repository;

import br.com.macedo.domain.aggregate.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoEntity> {

    public Optional<ProdutoEntity> findByNomeProduto(String nomeProduto) {
        return find("nomeProduto", nomeProduto).firstResultOptional();
    }

    public List<ProdutoEntity> findProdutosByStatus(String status) {
        return list("statusProduto.nomeStatus", status );
    }
}
