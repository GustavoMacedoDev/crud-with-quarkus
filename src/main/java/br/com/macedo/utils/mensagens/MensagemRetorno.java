package br.com.macedo.utils.mensagens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRetorno implements Serializable {

    private Long codigo;
    private String mensagem;


}
