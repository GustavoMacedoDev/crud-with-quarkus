package br.com.macedo.domain.dto;

import br.com.macedo.domain.aggregate.ProdutoEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DetalhaProdutoDto implements Serializable {
    private final String nomeProduto;
    private final BigDecimal preco;

    public DetalhaProdutoDto(ProdutoEntity entity) {
        this.nomeProduto = entity.getNomeProduto();
        this.preco = entity.getPreco();
    }




}
