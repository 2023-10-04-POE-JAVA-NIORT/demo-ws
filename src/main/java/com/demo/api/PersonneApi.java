package com.demo.api;

import com.demo.model.Personne;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
   public void postPersonne(Personne newPersonne){
        newPersonne.setId(idCount++);
        personnes.add(newPersonne);
       System.out.println(newPersonne);
   }

   // GET 1 personn
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
   public Personne getPersonne(@PathParam("id") Integer id){
        for(Personne p : personnes){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
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
    public void updatePersonne(Personne newData, @PathParam("id") Integer id){

        int i = 0;
        while(i < personnes.size() && !personnes.get(i).getId().equals(id)){
            i++;
        }
        if(i < personnes.size()){
            newData.setId(id);
            personnes.set(i, newData);
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
}