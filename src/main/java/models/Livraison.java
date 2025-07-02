package models;

import java.util.Objects;

public class Livraison {
    private int id;
    private int colisId; // Référence vers le colis
    private int livreurId; // Référence vers le livreur
    private String statut; // "En cours", "Livré", "Échoué"
    private String dateDebut;
    private String dateLivraison;
    private String commentaires;
    private String AdresseLivraison;

    // Constructeur par défaut
    public Livraison() {
    }

    // Constructeur avec paramètres
    public Livraison(int id, int colisId, int livreurId, String statut,
            String dateDebut, String dateLivraison, String commentaires) {
        this.id = id;
        this.colisId = colisId;
        this.livreurId = livreurId;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.dateLivraison = dateLivraison;
        this.commentaires = commentaires;
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

    public int getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(int livreurId) {
        this.livreurId = livreurId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

//    public String getDateDebut() {
//        return dateDebut;
//    }
//
//    public void setDateDebut(String dateDebut) {
//        this.dateDebut = dateDebut;
//    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

//    public String getCommentaires() {
//        return commentaires;
//    }
//
//    public void setCommentaires(String commentaires) {
//        this.commentaires = commentaires;
//    }
//
//    public String getAdresseLivraison() {
//        return AdresseLivraison;
//    }
//
//    public void setAdresseLivraison(String adresseLivraison) {
//        this.AdresseLivraison = adresseLivraison;
//    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Livraison{" +
                "id=" + id +
                ", colisId=" + colisId +
                ", livreurId=" + livreurId +
                ", statut='" + statut + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateLivraison='" + dateLivraison + '\'' +
                ", commentaires='" + commentaires + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Livraison
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Livraison livraison = (Livraison) o;
        return id == livraison.id;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}