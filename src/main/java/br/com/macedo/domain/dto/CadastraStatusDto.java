package br.com.macedo.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CadastraStatusDto implements Serializable {

    private String nomeStatus;

    private String descricaoStatus;


}
