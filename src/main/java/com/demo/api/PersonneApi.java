package com.demo.api;

import com.demo.model.Personne;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/personnes")
public class PersonneApi {

    private static int idCount = 0;
    private static List<Personne> personnes = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public List<Personne> getPersonnes(){

       return personnes;
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response postPersonne(Personne newPersonne){
        if(newPersonne.getPrenom() == null || newPersonne.getNom()==null
                || newPersonne.getPrenom().isBlank() || newPersonne.getNom().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Les champs doivent être non vides").build();
        }
        else {
            newPersonne.setId(idCount++);
            personnes.add(newPersonne);
            System.out.println(newPersonne);
            return Response.status(Response.Status.CREATED).entity(newPersonne).build();
        }
   }

   // GET 1 personn
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
   public Response getPersonne(@PathParam("id") Integer id){
        for(Personne p : personnes){
            if(p.getId().equals(id)){
                return Response.status(Response.Status.OK).entity(p).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("id inexistant").build();
   }

   @DELETE
   @Path("/{id}")
    public void deletePersonne(@PathParam("id") Integer id){

        Iterator<Personne> it = personnes.listIterator();
        boolean continuer = true;
        while(it.hasNext() && continuer){
            Personne p = it.next();
            if(p.getId().equals(id)) {
                personnes.remove(p);
                continuer = false;
            }
        }
        /*
       int i = 0;
       while(i < personnes.size() && !personnes.get(i).getId().equals(id)){
          i++;
       }
       if(i < personnes.size()){
           personnes.remove(i);
       }*/

/* ATTENTION : Exception Access Concurrent
       for(Personne p : personnes){
           if(p.getId().equals(id)){
               personnes.remove(p);
           }
       }*/
    }

    @PUT
    @Path("/{id}")
    public Response updatePersonne(Personne newData, @PathParam("id") Integer id){

        if(!id.equals(newData.getId())){
            return Response.status(Response.Status.BAD_REQUEST).entity("deux id différents").build();
        }
        else {

            if(findPersonne(id) == null){
                return Response.status(Response.Status.NOT_FOUND).entity("id inexistant").build();
            }
            else {
                int i = 0;
                while (i < personnes.size() && !personnes.get(i).getId().equals(id)) {
                    i++;
                }
                if (i < personnes.size()) {
                    newData.setId(id);
                    personnes.set(i, newData);
                    return Response.ok().build();
                }
                return Response.status(Response.Status.BAD_REQUEST).entity("").build();
            }
        }
    }

    @PATCH
    @Path("/{id}")
    public void patchPersonne(Personne newData, @PathParam("id") Integer id){
        int i = 0;
        while(i < personnes.size() && !personnes.get(i).getId().equals(id)){
            i++;
        }
        if(i < personnes.size()){
            // algo PATCH
            Personne dbPersonne = personnes.get(i);
            dbPersonne.setNotNull(newData);
        }

    }

    private Personne findPersonne(Integer id){
        for(Personne p : personnes){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
}