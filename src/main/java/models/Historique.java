package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Historique {
    private int id;
    private int livraisonId;  // Référence vers la livraison concernée
    private String statut;  // Statut de la livraison à ce moment
    private LocalDateTime dateAction;

    // Constructeur par défaut
    public Historique() {
    }

    // Constructeur avec paramètres
    public Historique(int id, int livraisonId, String statut, LocalDateTime dateAction) {
        this.id = id;
        this.livraisonId = livraisonId;
        this.statut = statut;
        this.dateAction = dateAction;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(int livraisonId) {
        this.livraisonId = livraisonId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        this.dateAction = dateAction;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", livraisonId=" + livraisonId +
                ", statut='" + statut + '\'' +
                ", dateAction=" + dateAction +
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
