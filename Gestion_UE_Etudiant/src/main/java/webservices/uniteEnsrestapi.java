package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class uniteEnsrestapi {
    UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getListUEs() {
        return Response.status(200).entity(helper.getListeUE()).build();

    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUE(UniteEnseignement unite) {
        if (helper.addUniteEnseignement(unite)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("Erreur").build();
        }

    }

    @Path("/{intCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getIntCodeUEs(@PathParam("intCode") int code) {
        return Response.ok(helper.getUEByCode(code)).build();
    }

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getUEsBySemestre(@QueryParam("semestre") int semestre) {
        return Response.ok(helper.getUEBySemestre(semestre)).build();
    }

    @Path("/")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUEs(@QueryParam("semestre") int semestre) {
        if (helper.deleteUniteEnseignement(semestre)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(400).entity("Erreur").build();
        }
    }
}
