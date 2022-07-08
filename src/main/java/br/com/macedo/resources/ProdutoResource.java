package br.com.macedo.resources;

import br.com.macedo.entities.dto.CadastraProdutoDto;
import br.com.macedo.entities.dto.DetalhaProdutoDto;
import br.com.macedo.entities.dto.ErroDto;
import br.com.macedo.entities.dto.ListagemProdutoDto;
import br.com.macedo.utils.mensagens.MensagemRetorno;
import br.com.macedo.services.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/produtos")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Produtos")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/")
    @Operation(summary = "Lista Produtos", description = "ListaProdutos")
    @APIResponse(responseCode = "200", description = "Lista de todos os produtos")
    public Response listaProdutos() {
        List<ListagemProdutoDto> listagemProdutos = produtoService.listaProdutos();
        
        return Response.status(Response.Status.OK).entity(listagemProdutos).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Novo Produto", description = "Cadastra Novo Produto")
    public Response cadastraProduto(@Valid
                                    @NotNull(message = "Requisição não pode ser nula")
                                    CadastraProdutoDto cadastraProdutoDto) {
        MensagemRetorno resposta = produtoService.cadastrarProduto(cadastraProdutoDto);

        return Response.status(Response.Status.CREATED).entity(resposta).build();

    }

    @GET
    @Path("/produto/{idProduto}")
    @Operation(summary = "Detalha produto ", description = "Detalha produto")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = DetalhaProdutoDto.class)))
    @APIResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = ErroDto.class)))
    public Response buscaProdutoPorId(@Parameter(description = "código do produto", required = true)
                                      @PathParam("idProduto") Long idProduto) {
        DetalhaProdutoDto detalhaProdutoDto = produtoService.detalhaProduto(idProduto);

        return Response.status(Response.Status.OK).entity(detalhaProdutoDto).build();

    }

    @PUT
    @Path("/produto/inativa/{idProduto}")
    @Operation(summary = "Inativa Produto", description = "Inativa Produto")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = DetalhaProdutoDto.class)))
    public Response inativaProduto(@Parameter(description = "código do produto", required = true)
                                   @PathParam("idProduto") Long idProduto) {

        MensagemRetorno mensagem = produtoService.inativaProduto(idProduto);

        return Response.status(Response.Status.OK).entity(mensagem).build();

    }

    @PUT
    @Path("/produto/bloqueia/{idProduto}")
    @Operation(summary = "Bloqueia Produto", description = "Bloqueia Produto")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = DetalhaProdutoDto.class)))
    public Response bloqueiaProduto(@Parameter(description = "código do produto", required = true)
                                   @PathParam("idProduto") Long idProduto) {

        MensagemRetorno mensagem = produtoService.bloqueiaProduto(idProduto);

        return Response.status(Response.Status.OK).entity(mensagem).build();

    }

    @PUT
    @Path("/produto/ativa/{idProduto}")
    @Operation(summary = "Ativa Produto", description = "Ativa Produto")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = DetalhaProdutoDto.class)))
    public Response ativaProduto(@Parameter(description = "código do produto", required = true)
                                    @PathParam("idProduto") Long idProduto) {

        MensagemRetorno mensagem = produtoService.ativaProduto(idProduto);

        return Response.status(Response.Status.OK).entity(mensagem).build();

    }
}

