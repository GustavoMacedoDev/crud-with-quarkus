package br.com.macedo.domain.dto;

import br.com.macedo.domain.aggregate.ProdutoEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ListagemProdutoDto implements Serializable {
    private final String nomeProduto;
    private final BigDecimal preco;
    private final String status;

    public ListagemProdutoDto(ProdutoEntity entity) {
        this.nomeProduto = entity.getNomeProduto();
        this.preco = entity.getPreco();
        this.status = entity.getStatusProduto().getNomeStatus();

    }
}
