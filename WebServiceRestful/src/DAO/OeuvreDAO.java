package DAO;

import com.google.gson.Gson;
import meserreurs.MonException;
import metier.Oeuvre;
import metier.Oeuvrevente;
import persistance.DialogueBd;

import java.util.ArrayList;
import java.util.List;

public class OeuvreDAO extends DAO {

    private static OeuvreDAO instance;
    private ProprietaireDAO proprietaireDAO;

    public OeuvreDAO() {
        super();
        this.proprietaireDAO = ProprietaireDAO.getInstance();
    }

    public static OeuvreDAO getInstance() {
        if (instance == null)
            instance = new OeuvreDAO();
        return instance;
    }

    public List<Oeuvrevente> list() throws MonException {
        List<Oeuvrevente> mesOeuvres = new ArrayList<>();
        String mysql  = "SELECT id_oeuvrevente, titre_oeuvrevente";
        mysql += " FROM oeuvrevente order by titre_oeuvrevente ;";
        List<Object> rs = this.getBd().lecture(mysql);
        int index = 0;
        while (index < rs.size()) {
            Oeuvrevente uneOeuvre = new Oeuvrevente();
            uneOeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(index).toString()));
            uneOeuvre.setTitreOeuvrevente(rs.get(index + 1).toString());
            index = index + 2;
            mesOeuvres.add(uneOeuvre);
        }
        return mesOeuvres;
    }

    public Oeuvrevente find(int id) throws MonException {
        String mysql = "SELECT id_oeuvrevente, titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente,id_proprietaire";
        mysql += " FROM oeuvrevente WHERE id_Oeuvrevente = " + id + ";";
        return findQuery(mysql);
    }

    public Oeuvrevente find(String name) throws MonException {
        String mysql = "select id_oeuvrevente, titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente,id_proprietaire";
        mysql += " from oeuvrevente where titre_oeuvrevente = '" + name + "';";
        return findQuery(mysql);
    }


    private Oeuvrevente findQuery(String sql) throws MonException {
        List<Object> rs = this.getBd().lecture(sql);
        Oeuvrevente oeuvre = null;
        if (rs.size() > 0) {
            oeuvre = new Oeuvrevente();
            oeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(0).toString()));
            oeuvre.setTitreOeuvrevente(rs.get(1).toString());
            oeuvre.setPrixOeuvrevente(Float.parseFloat(rs.get(3).toString()));
            int id = Integer.parseInt(rs.get(4).toString());
            oeuvre.setProprietaire(this.proprietaireDAO.find(id));
        }
        return oeuvre;
    }
}
