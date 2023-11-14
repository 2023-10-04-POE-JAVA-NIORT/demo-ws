package com.demo.api;

import com.demo.model.Personne;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/personnes")
public class PersonneApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public List<Personne> getPersonnes(){
       List<Personne> personnes = new ArrayList<>();
       personnes.add(new Personne("Alain", "Delon"));
       personnes.add(new Personne("Marie", "Dupont"));
       return personnes;
   }
}