package br.com.macedo.services;

import br.com.macedo.domain.aggregate.StatusEntity;
import br.com.macedo.domain.dto.CadastraStatusDto;
import br.com.macedo.domain.dto.DeletaStatusDto;
import br.com.macedo.domain.dto.ListaStatusDto;
import br.com.macedo.domain.dto.ListagemProdutoDto;
import br.com.macedo.repository.StatusRepository;
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
public class StatusService {

    private final static Logger LOGGER = Logger.getLogger(StatusService.class);

    @Inject
    StatusRepository statusRepository;

    @Inject
    ProdutoService produtoService;


    public List<ListaStatusDto> listaStatus() {
        List<ListaStatusDto> listaStatusResponse  = new ArrayList<>();
        List<StatusEntity> listaStatus = StatusEntity.listAll();

        for(StatusEntity status : listaStatus) {
            ListaStatusDto listaStatusDto = new ListaStatusDto(status);

            listaStatusResponse.add(listaStatusDto);
        }

        return listaStatusResponse;
    }

    public StatusEntity buscaStatusPorNomeStatus(String nomeStatus) {
        Optional<StatusEntity> statusEntity = pesquisaStatusPorNome(nomeStatus);
        return statusEntity.orElseThrow(
                () -> new ObjectNotFoundException("Status não encontrado"));
    }

    private Optional<StatusEntity> pesquisaStatusPorNome(String nomeStatus) {

        return statusRepository.findByNomeStatus(nomeStatus);
    }

    @Transactional
    public MensagemRetorno cadastraStatus(CadastraStatusDto cadastraStatusDto) {
        Optional<StatusEntity> statusEntityResponse = pesquisaStatusPorNome(cadastraStatusDto.getNomeStatus());

        if(statusEntityResponse.isPresent()) {
            throw new ObjectNotFoundException("Status já cadastrado!");
        }

        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setNomeStatus(cadastraStatusDto.getNomeStatus());
        statusEntity.setDescricaoStatus(cadastraStatusDto.getDescricaoStatus());

        try {
            statusEntity.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e.getMessage());
            throw new ObjectNotFoundException("Erro ao inserir status");
        }

        return new MensagemRetorno(statusEntity.getIdStatus(),
                "Status do código " + statusEntity.getIdStatus() + ", foi incluído com sucesso");

    }

    @Transactional
    public MensagemRetorno deletaStatus(DeletaStatusDto deletaStatusDto) {

        Optional<StatusEntity> statusEntityResponse =
                statusRepository.findByNomeStatus(deletaStatusDto.getNomeStatus());

        if (statusEntityResponse.isEmpty()) {
            throw new ObjectNotFoundException("Status não encontrado");
        }

        List<ListagemProdutoDto> listagemProduto = produtoService.listaProdutosPorStatus(deletaStatusDto.getNomeStatus());

        if(!listagemProduto.isEmpty()) {
            throw new ObjectNotFoundException("O status está vinculado a algum produto");
        }
        else {
            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setIdStatus(statusEntityResponse.get().getIdStatus());

            try {
                statusEntity.delete();
            } catch (PersistenceException e) {
                LOGGER.error(e.getMessage());
                throw new ObjectNotFoundException("Erro ao tentar deletar o status");

            }
        }


        return new MensagemRetorno(1L, "Status deletado com sucesso");
    }
}
