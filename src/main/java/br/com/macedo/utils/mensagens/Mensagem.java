package br.com.macedo.utils.mensagens;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mensagem;

}
