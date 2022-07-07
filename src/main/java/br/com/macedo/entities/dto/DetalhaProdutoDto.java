package br.com.macedo.entities.dto;

import br.com.macedo.entities.ProdutoEntity;
import br.com.macedo.entities.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DetalhaProdutoDto implements Serializable {
    private final String nomeProduto;
    private final BigDecimal preco;
    private final StatusEnum statusEnum;

    public DetalhaProdutoDto(ProdutoEntity entity) {
        this.nomeProduto = entity.getNomeProduto();
        this.preco = entity.getPreco();
        this.statusEnum = entity.getStatusEnum();
    }




}
