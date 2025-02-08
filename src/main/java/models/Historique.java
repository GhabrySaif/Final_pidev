package models;

import java.util.Objects;

public class Historique {
    private int id;
    private int colisId;  // Référence vers le colis concerné
    private int utilisateurId;  // Référence vers l'utilisateur (client, livreur, admin)
    private String action;  // Exemple : "Colis créé", "Colis pris en charge", "Colis livré"
    private String dateAction;

    // Constructeur par défaut
    public Historique() {
    }

    // Constructeur avec paramètres
    public Historique(int id, int colisId, int utilisateurId, String action, String dateAction) {
        this.id = id;
        this.colisId = colisId;
        this.utilisateurId = utilisateurId;
        this.action = action;
        this.dateAction = dateAction;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColisId() {
        return colisId;
    }

    public void setColisId(int colisId) {
        this.colisId = colisId;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", colisId=" + colisId +
                ", utilisateurId=" + utilisateurId +
                ", action='" + action + '\'' +
                ", dateAction='" + dateAction + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Historique
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historique that = (Historique) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
