package br.com.macedo.domain.dto;

import br.com.macedo.domain.aggregate.StatusEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListaStatusDto implements Serializable {
    private final String nomeStatus;
    private final String descricaoStatus;

    public ListaStatusDto(StatusEntity statusEntity) {
        this.nomeStatus = statusEntity.getNomeStatus();
        this.descricaoStatus = statusEntity.getDescricaoStatus();
    }
}
