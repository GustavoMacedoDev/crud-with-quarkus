package br.com.macedo.utils.mensagens;

public abstract class Mensagem {

    private String mensagem;

    public Mensagem() {
    }

    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
