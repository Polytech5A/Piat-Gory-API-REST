package service;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.*;

import DAO.AdherentDAO;
import DAO.OeuvreDAO;
import com.google.gson.Gson;


import meserreurs.MonException;
import metier.*;
import persistance.DialogueBd;

@Path("/mediatheque")
public class WService {


    /***************************************************/
    /***************Partie sur les adhérents ***********/
    /***************************************************/
    @POST
    @Path("/Adherents/ajout/{unAdh}")
    @Consumes("application/json")
    public void insertionAdherent(String jsonAdherent) throws MonException {
        AdherentDAO adherentDAO = AdherentDAO.getInstance();
        Gson gson = new Gson();
        Adherent adherent = gson.fromJson(jsonAdherent, Adherent.class);
        adherentDAO.add(adherent);
    }


    @GET
    @Path("/Adherents")
    @Produces("application/json")
    public String rechercheLesAdherents() throws MonException {
        AdherentDAO adherentDAO = AdherentDAO.getInstance();
        List<Adherent> adherents = adherentDAO.list();
        return this.toJson(adherents);
    }


    /***************************************************/
    /***************Partie sur les oeuvres  ************/
    /***************************************************/


    @GET
    @Path("/Oeuvres/{Id}")
    @Produces("application/json")
    public String rechercherOeuvreId(@PathParam("Id") String idOeuvre) throws MonException {
        OeuvreDAO oeuvreDAO = OeuvreDAO.getInstance();
        Oeuvrevente uneOeuvre = oeuvreDAO.find(Integer.parseInt(idOeuvre));
        return this.toJson(uneOeuvre);
    }

    @GET
    @Path("/Oeuvres/lib/{libelle}")
    @Produces("application/json")
    public String rechercheOeuvreLibelle(@PathParam("libelle") String lib) throws MonException {
        OeuvreDAO oeuvreDAO = OeuvreDAO.getInstance();
        Oeuvrevente uneOeuvre = oeuvreDAO.find(lib);
        return this.toJson(uneOeuvre);
    }


    //****************************
    // Liste des oeuvres
    //****************************


    @GET
    @Path("/Oeuvres")
    @Produces("application/json")
    public String consulterListeOeuvre() throws MonException {
        OeuvreDAO oeuvreDAO = OeuvreDAO.getInstance();
        List<Oeuvrevente> mesOeuvres = oeuvreDAO.list();
        return this.toJson(mesOeuvres);
    }

    public String toJson(Object data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }


}