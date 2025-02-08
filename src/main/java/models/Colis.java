package models;

import java.util.Objects;

public class Colis {
    private int id;
    private String description;
    private float poids;
    private int expediteurId;
    private int destinataireId;
    private String statut; // "En attente", "En cours", "Livré"
    private String adresseLivraison;
    private String codeSuivi;
    private String dateCreation;

    // Constructeur par défaut
    public Colis() {
    }

    // Constructeur avec paramètres
    public Colis(int id, String description, float poids, int expediteurId, int destinataireId,
                 String statut, String adresseLivraison, String codeSuivi, String dateCreation) {
        this.id = id;
        this.description = description;
        this.poids = poids;
        this.expediteurId = expediteurId;
        this.destinataireId = destinataireId;
        this.statut = statut;
        this.adresseLivraison = adresseLivraison;
        this.codeSuivi = codeSuivi;
        this.dateCreation = dateCreation;
    }

    public Colis(int id, int utilisateurId, String description, double poids, String adresseDestination, String statut) {
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getExpediteurId() {
        return expediteurId;
    }

    public void setExpediteurId(int expediteurId) {
        this.expediteurId = expediteurId;
    }

    public int getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(int destinataireId) {
        this.destinataireId = destinataireId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getCodeSuivi() {
        return codeSuivi;
    }

    public void setCodeSuivi(String codeSuivi) {
        this.codeSuivi = codeSuivi;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Colis{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", poids=" + poids +
                ", expediteurId=" + expediteurId +
                ", destinataireId=" + destinataireId +
                ", statut='" + statut + '\'' +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", codeSuivi='" + codeSuivi + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Colis
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colis colis = (Colis) o;
        return id == colis.id && Objects.equals(codeSuivi, colis.codeSuivi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeSuivi);
    }
}