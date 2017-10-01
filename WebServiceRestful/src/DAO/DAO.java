package DAO;

import persistance.DialogueBd;

public abstract class DAO {
    private DialogueBd  bd;

    public DAO() {
        this.bd = DialogueBd.getInstance();
    }

    public DialogueBd getBd(){
        return bd;
    }
}
