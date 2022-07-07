package br.com.macedo.services;

import br.com.macedo.entities.ProdutoEntity;
import br.com.macedo.entities.dto.CadastraProdutoDto;
import br.com.macedo.entities.dto.DetalhaProdutoDto;
import br.com.macedo.entities.dto.ListagemProdutoDto;
import br.com.macedo.entities.enums.StatusEnum;
import br.com.macedo.utils.exceptions.NegocioException;
import br.com.macedo.utils.exceptions.ObjectNotFoundException;
import br.com.macedo.utils.mensagens.MensagemRetorno;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoService {

    private final static Logger LOGGER = Logger.getLogger(ProdutoService.class);

    public List<ListagemProdutoDto> listaProdutos() {
        List<ListagemProdutoDto> listaProdutosResponse = new ArrayList<>();

        List<ProdutoEntity> listaProdutosEntity = ProdutoEntity.listAll();

        for (ProdutoEntity produto : listaProdutosEntity) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto(produto);

            listaProdutosResponse.add(listagemProdutoDto);
        }

        return listaProdutosResponse;
    }

    @Transactional
    public MensagemRetorno cadastrarProduto(CadastraProdutoDto cadastraProdutoDto)  {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNomeProduto(cadastraProdutoDto.getNomeProduto());
        produtoEntity.setPreco(cadastraProdutoDto.getPreco());
        produtoEntity.setStatusEnum(StatusEnum.ATIVO);

        try {
            produtoEntity.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new NegocioException("Erro ao inserir produto");
        }

        return new MensagemRetorno(produtoEntity.getIdProduto(),
                "O Produto de código " + produtoEntity.getIdProduto()
                            + ", foi incluído com sucesso");

    }

    public DetalhaProdutoDto detalhaProduto(Long idProduto) {
        ProdutoEntity produtoEntity = pesquisaProduto(idProduto);

        return new DetalhaProdutoDto(produtoEntity);
    }

    private ProdutoEntity pesquisaProduto(Long idProduto) {
        Optional<ProdutoEntity> produtoEntity = ProdutoEntity.findByIdOptional(idProduto);

        return produtoEntity.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public MensagemRetorno inativaProduto(Long idProduto) {
        ProdutoEntity produto = pesquisaProduto(idProduto);

        produto.setStatusEnum(StatusEnum.INATIVO);

        try {
            produto.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }

        MensagemRetorno mensagemRetorno = new MensagemRetorno();
        mensagemRetorno.setCodigo(produto.getIdProduto());
        mensagemRetorno.setMensagem("Produto Inativado com sucesso");

        return mensagemRetorno;
    }

    @Transactional
    public MensagemRetorno bloqueiaProduto(Long idProduto) {
        ProdutoEntity produto = pesquisaProduto(idProduto);

        produto.setStatusEnum(StatusEnum.BLOQUEADO);

        try {
            produto.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }

        MensagemRetorno mensagemRetorno = new MensagemRetorno();
        mensagemRetorno.setCodigo(produto.getIdProduto());
        mensagemRetorno.setMensagem("Produto Bloqueado com sucesso");

        return mensagemRetorno;
    }

    @Transactional
    public MensagemRetorno ativaProduto(Long idProduto) {
        ProdutoEntity produto = pesquisaProduto(idProduto);

        produto.setStatusEnum(StatusEnum.ATIVO);

        try {
            produto.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }

        MensagemRetorno mensagemRetorno = new MensagemRetorno();
        mensagemRetorno.setCodigo(produto.getIdProduto());
        mensagemRetorno.setMensagem("Produto Ativado com sucesso");

        return mensagemRetorno;
    }
}
