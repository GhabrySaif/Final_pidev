package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Paiement {
    private int id;
    private int utilisateurId;  // Référence vers l'utilisateur (client)
    private int colisId;  // Référence vers le colis associé
    private double montant;  // Montant du paiement
    private String modePaiement;  // "Carte bancaire", "Espèces", "PayPal", etc.
    private String statut;  // "Payé", "En attente", "Échoué"
    private String datePaiement;

    // Constructeur par défaut
    public Paiement() {
    }

    // Constructeur avec paramètres
    public Paiement(int id, int utilisateurId, int colisId, double montant,
                    String modePaiement, String statut, String datePaiement) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.colisId = colisId;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.statut = statut;
        this.datePaiement = datePaiement;
    }

    public Paiement(int id, int colisId, double montant, String modePaiement, String statut, LocalDateTime datePaiement) {
        this.id = id;
        this.colisId = colisId;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.statut = statut;
        this.datePaiement = datePaiement.toString();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getColisId() {
        return colisId;
    }

    public void setColisId(int colisId) {
        this.colisId = colisId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", utilisateurId=" + utilisateurId +
                ", colisId=" + colisId +
                ", montant=" + montant +
                ", modePaiement='" + modePaiement + '\'' +
                ", statut='" + statut + '\'' +
                ", datePaiement='" + datePaiement + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Paiement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return id == paiement.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
