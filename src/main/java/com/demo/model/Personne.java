package com.demo.model;

public class Personne {

    private Integer id;
    private String prenom;
    private String nom;

    public Personne() {
    }

    public Personne(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNotNull(Personne newData){
        if(newData.getPrenom() != null)
            this.prenom = newData.getPrenom();
        if(newData.getNom() != null)
            this.nom = newData.getNom();
    }

    @Override
    public String toString() {
        return "Personne{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
