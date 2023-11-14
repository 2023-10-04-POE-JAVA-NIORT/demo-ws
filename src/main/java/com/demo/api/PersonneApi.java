package com.demo.api;

import com.demo.model.Personne;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
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

}