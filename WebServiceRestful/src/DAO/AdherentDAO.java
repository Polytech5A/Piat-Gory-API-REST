package DAO;

import meserreurs.MonException;
import metier.Adherent;

import java.util.ArrayList;
import java.util.List;

public class AdherentDAO extends DAO {
    private static AdherentDAO instance;

    public static AdherentDAO getInstance() {
        if (instance == null)
            instance = new AdherentDAO();
        return instance;
    }


    public void add(Adherent adherent) throws MonException {
        String mysql = "";
        mysql = "INSERT INTO adherent (nom_adherent, prenom_adherent, ville_adherent) ";
        mysql += " VALUES ( \'" + adherent.getNomAdherent() + "\', \'" + adherent.getPrenomAdherent();
        mysql += "  \', \'" + adherent.getVilleAdherent() + "\') ";
        this.getBd().insertionBD(mysql);
    }

    public List<Adherent> list() throws MonException {
        String mysql = "SELECT * FROM adherent ORDER BY id_adherent ASC";
        List<Object> raw = this.getBd().lecture(mysql);
        List<Adherent> adherents = new ArrayList<Adherent>();

        int index = 0;
        while (index < raw.size()) {
            Adherent unAdh = new Adherent();
            unAdh.setIdAdherent(Integer.parseInt(raw.get(index + 0).toString()));
            unAdh.setNomAdherent(raw.get(index + 1).toString());
            unAdh.setPrenomAdherent(raw.get(index + 2).toString());
            unAdh.setVilleAdherent(raw.get(index + 3).toString());
            index = index + 4;
            adherents.add(unAdh);
        }

        return adherents;
    }
}
