package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/modules")
public class ModuleRessources {

    private ModuleBusiness moduleBusiness = new ModuleBusiness();

    // GET /api/modules
    // Retourne la liste de tous les modules
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        List<Module> liste = moduleBusiness.getAllModules();
        return Response.status(200).entity(liste).build();
    }

    // GET /api/modules/{matricule}
    // Retourne un module par son matricule
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module module = moduleBusiness.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(200).entity(module).build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"Module avec le matricule " + matricule + " introuvable\"}")
                    .build();
        }
    }

    // GET /api/modules/type/{type}
    // Retourne les modules par type (TRANSVERSAL, PROFESSIONNEL, RECHERCHE)
    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@PathParam("type") String type) {
        try {
            Module.TypeModule typeModule = Module.TypeModule.valueOf(type.toUpperCase());
            List<Module> liste = moduleBusiness.getModulesByType(typeModule);
            return Response.status(200).entity(liste).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400)
                    .entity("{\"message\": \"Type invalide. Valeurs acceptées: TRANSVERSAL, PROFESSIONNEL, RECHERCHE\"}")
                    .build();
        }
    }

    // POST /api/modules
    // Ajouter un nouveau module
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        boolean added = moduleBusiness.addModule(module);
        if (added) {
            return Response.status(201)
                    .entity("{\"message\": \"Module ajouté avec succès\"}")
                    .build();
        } else {
            return Response.status(400)
                    .entity("{\"message\": \"Erreur lors de l'ajout : UE associée introuvable\"}")
                    .build();
        }
    }

    // PUT /api/modules/{matricule}
    // Mettre à jour un module existant
    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        boolean updated = moduleBusiness.updateModule(matricule, updatedModule);
        if (updated) {
            return Response.status(200)
                    .entity("{\"message\": \"Module mis à jour avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"Module avec le matricule " + matricule + " introuvable\"}")
                    .build();
        }
    }

    // DELETE /api/modules/{matricule}
    // Supprimer un module par son matricule
    @DELETE
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        boolean deleted = moduleBusiness.deleteModule(matricule);
        if (deleted) {
            return Response.status(200)
                    .entity("{\"message\": \"Module supprimé avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"Module avec le matricule " + matricule + " introuvable\"}")
                    .build();
        }
    }
}
