package br.com.macedo.utils.exceptions;

import br.com.macedo.utils.mensagens.Mensagem;
import br.com.macedo.utils.mensagens.MensagemErro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NegocioException extends RuntimeException {

    private final transient List<Mensagem> mensagens = new ArrayList<>();

    public NegocioException(String... messages) {
        List<MensagemErro> list = Arrays.stream(messages).
                map(MensagemErro::new).collect(Collectors.toList());
        this.mensagens.addAll(list);
    }

    public NegocioException(Mensagem... messages) {
        this.mensagens.addAll(Arrays.asList(messages));
    }

    public NegocioException() {
    }


    public void add(Mensagem message) {
        this.mensagens.add(message);
    }

    public List<Mensagem> getMessages() {
        return mensagens;
    }

    public static void throwExceptionErro(String mensagem) {
        throw new NegocioException(new MensagemErro(mensagem));
    }


    public static <T> void throwExceptionErroIfNull(T objeto, String mensagem) {
        if (objeto == null) {
            throwExceptionErro(mensagem);
        }
    }
}
