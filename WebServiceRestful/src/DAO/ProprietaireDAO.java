package DAO;

import meserreurs.MonException;
import metier.Proprietaire;
import persistance.DialogueBd;

import java.util.List;

public class ProprietaireDAO extends DAO {
    private static ProprietaireDAO instance;

    public static ProprietaireDAO getInstance() {
        if (instance == null)
            instance = new ProprietaireDAO();
        return instance;
    }

    public Proprietaire find(int id) throws MonException {
        Proprietaire proprietaire = null;
        String requete = " select * from proprietaire where id_Proprietaire =" + id + ";";
        List<Object> rs = this.getBd().lecture(requete);
        if (rs.size() > 0) {
            proprietaire = new Proprietaire();
            proprietaire.setIdProprietaire(Integer.parseInt(rs.get(0).toString()));
            proprietaire.setNomProprietaire(rs.get(1).toString());
            proprietaire.setPrenomProprietaire(rs.get(2).toString());
        }
        return proprietaire;
    }
}
