package br.com.macedo.services;

import br.com.macedo.domain.aggregate.ProdutoEntity;
import br.com.macedo.domain.dto.CadastraProdutoDto;
import br.com.macedo.domain.dto.DetalhaProdutoDto;
import br.com.macedo.domain.dto.ListagemProdutoDto;
import br.com.macedo.repository.ProdutoRepository;
import br.com.macedo.utils.exceptions.ObjectNotFoundException;
import br.com.macedo.utils.mensagens.MensagemRetorno;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoService {

    @Inject
    StatusService statusService;

    @Inject
    ProdutoRepository produtoRepository;

    private final static Logger LOGGER = Logger.getLogger(ProdutoService.class);

    public List<ListagemProdutoDto> listaProdutos() {
        List<ListagemProdutoDto> listaProdutosResponse = new ArrayList<>();

        List<ProdutoEntity> listaProdutosEntity;
        try {
            listaProdutosEntity = ProdutoEntity.listAll();

        } catch (PersistenceException e) {
            throw new ObjectNotFoundException("Erro ao buscar os produtos");
        }

        for (ProdutoEntity produto : listaProdutosEntity) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto(produto);

            listaProdutosResponse.add(listagemProdutoDto);
        }

        return listaProdutosResponse;
    }

    @Transactional
    public MensagemRetorno cadastrarProduto(CadastraProdutoDto cadastraProdutoDto)  {
        Optional<ProdutoEntity> produtoEntityResponse = produtoRepository.findByNomeProduto(cadastraProdutoDto.getNomeProduto());

        if(produtoEntityResponse.isPresent()) {
            throw new ObjectNotFoundException("Produto já cadastrado");
        }

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNomeProduto(cadastraProdutoDto.getNomeProduto());
        produtoEntity.setPreco(cadastraProdutoDto.getPreco());
        produtoEntity.setStatusProduto(statusService.buscaStatusPorNomeStatus(cadastraProdutoDto.getStatus()));

        try {
            produtoEntity.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ObjectNotFoundException("Erro ao inserir produto");
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


    public List<ListagemProdutoDto> listaProdutosPorStatus(String nomeStatus) {
        List<ProdutoEntity> listaProdutos = produtoRepository.findProdutosByStatus(nomeStatus);

        List<ListagemProdutoDto> listagemProdutoResponse = new ArrayList<>();
        for(ProdutoEntity produto : listaProdutos) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto(produto);
            listagemProdutoResponse.add(listagemProdutoDto);
        }


        return listagemProdutoResponse;
    }
}
