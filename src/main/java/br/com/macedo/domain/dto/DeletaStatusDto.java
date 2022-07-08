package br.com.macedo.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeletaStatusDto implements Serializable {
    private String nomeStatus;
}
