package com.demo.api;

import com.demo.model.Personne;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/personnes")
public class PersonneApi {

    private static List<Personne> personnes = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public List<Personne> getPersonnes(){

       return personnes;
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public void postPersonne(Personne newPersonne){
        personnes.add(newPersonne);
       System.out.println(newPersonne);
   }
}