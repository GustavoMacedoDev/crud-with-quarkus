package br.com.macedo.resources;

import br.com.macedo.domain.dto.CadastraStatusDto;
import br.com.macedo.domain.dto.DeletaStatusDto;
import br.com.macedo.domain.dto.ListaStatusDto;
import br.com.macedo.services.StatusService;
import br.com.macedo.utils.mensagens.MensagemRetorno;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/status")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Status")
public class StatusResource {

    @Inject
    StatusService statusService;

    @GET
    @Path("/")
    @Operation(summary = "Lista todos os status", description = "Lista Status")
    public Response listaStatus() {
        List<ListaStatusDto> listaStatusDto = statusService.listaStatus();

        return Response.status(Response.Status.OK).entity(listaStatusDto).build();

    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastro de Status", description = "Cadastro de Status")
    public Response cadastraStatus(@Valid @NotNull(message = "A requisição não pode ser nula")
                                   CadastraStatusDto cadastraStatusDto) {

        MensagemRetorno mensagemRetorno = statusService.cadastraStatus(cadastraStatusDto);

        return Response.status(Response.Status.CREATED).entity(mensagemRetorno).build();
    }

    @DELETE
    @Path("/")
    @Operation(summary = "Deletar um Status", description = "Deletar um Status")
    public Response deletarStatus(@Valid @NotNull(message = "A Requisição não pode ser nula")
                                  DeletaStatusDto deletaStatusDto) {
        MensagemRetorno mensagemRetorno = statusService.deletaStatus(deletaStatusDto);

        return Response.status(Response.Status.OK).entity(mensagemRetorno).build();
    }


}
