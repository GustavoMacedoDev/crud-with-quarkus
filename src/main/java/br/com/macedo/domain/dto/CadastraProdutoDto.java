package br.com.macedo.domain.dto;

import br.com.macedo.domain.aggregate.StatusEntity;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CadastraProdutoDto implements Serializable {

    @NotNull(message = "O Campo nome do Produto não pode ser nulo")
    @Size(message = "O campo nomeProduto deve ter no mínimo [min] e maximo [max]", min = 3, max = 50)
    private String nomeProduto;

    @NotNull(message = "O campo preco não pode ser nulo")
    @Digits(message = "Campo inválido", integer = 12, fraction = 2)
    private BigDecimal preco;

    private String status;

}
