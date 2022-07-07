package br.com.macedo.utils.exceptions;

import br.com.macedo.utils.mensagens.Mensagem;
import br.com.macedo.utils.mensagens.MensagemErro;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ObjectNotFoundException extends NotFoundException {

    private final transient List<Mensagem> mensagens = new ArrayList<>();

    public ObjectNotFoundException(String... messages) {
        List<MensagemErro> list = Arrays.stream(messages).
                map(MensagemErro::new).collect(Collectors.toList());
        this.mensagens.addAll(list);
    }


    public ObjectNotFoundException(Mensagem... messages) {
        this.mensagens.addAll(Arrays.asList(messages));
    }

    public ObjectNotFoundException() {
    }


    public void add(Mensagem message) {
        this.mensagens.add(message);
    }


    public List<Mensagem> getMessages() {
        return mensagens;
    }

}
