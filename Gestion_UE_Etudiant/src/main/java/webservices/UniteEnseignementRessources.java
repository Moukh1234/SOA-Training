package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/unites-enseignement")
public class UniteEnseignementRessources {

    private UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    // GET /api/unites-enseignement
    // Retourne la liste de toutes les UEs
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUE() {
        List<UniteEnseignement> liste = ueBusiness.getListeUE();
        return Response.status(200).entity(liste).build();
    }

    // GET /api/unites-enseignement/{code}
    // Retourne une UE par son code
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code) {
        UniteEnseignement ue = ueBusiness.getUEByCode(code);
        if (ue != null) {
            return Response.status(200).entity(ue).build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"UE avec le code " + code + " introuvable\"}")
                    .build();
        }
    }

    // GET /api/unites-enseignement/domaine/{domaine}
    // Retourne les UEs par domaine
    @GET
    @Path("/domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByDomaine(@PathParam("domaine") String domaine) {
        List<UniteEnseignement> liste = ueBusiness.getUEByDomaine(domaine);
        return Response.status(200).entity(liste).build();
    }

    // GET /api/unites-enseignement/semestre/{semestre}
    // Retourne les UEs par semestre
    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@PathParam("semestre") int semestre) {
        List<UniteEnseignement> liste = ueBusiness.getUEBySemestre(semestre);
        return Response.status(200).entity(liste).build();
    }

    // POST /api/unites-enseignement
    // Ajouter une nouvelle UE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        boolean added = ueBusiness.addUniteEnseignement(ue);
        if (added) {
            return Response.status(201)
                    .entity("{\"message\": \"UE ajoutée avec succès\"}")
                    .build();
        } else {
            return Response.status(400)
                    .entity("{\"message\": \"Erreur lors de l'ajout de l'UE\"}")
                    .build();
        }
    }

    // PUT /api/unites-enseignement/{code}
    // Mettre à jour une UE existante
    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
        boolean updated = ueBusiness.updateUniteEnseignement(code, updatedUE);
        if (updated) {
            return Response.status(200)
                    .entity("{\"message\": \"UE mise à jour avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"UE avec le code " + code + " introuvable\"}")
                    .build();
        }
    }

    // DELETE /api/unites-enseignement/{code}
    // Supprimer une UE par son code
    @DELETE
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUE(@PathParam("code") int code) {
        boolean deleted = ueBusiness.deleteUniteEnseignement(code);
        if (deleted) {
            return Response.status(200)
                    .entity("{\"message\": \"UE supprimée avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .entity("{\"message\": \"UE avec le code " + code + " introuvable\"}")
                    .build();
        }
    }
}
