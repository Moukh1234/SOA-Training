package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class modulBesinessApi {

    ModuleBusiness helper = new ModuleBusiness();

    // GET /module/list  → liste de tous les modules
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    // POST /module/new  → créer un nouveau module
    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("Erreur").build();
        }
    }

}

