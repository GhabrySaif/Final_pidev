package models;

public class Colis {
    private int id;
    private String description;
    private String statut; // "En attente", "En cours", "Livré"
    private String dateCreation;

    // Constructeur par défaut
    public Colis() {
    }

    // Constructeur avec paramètres
    public Colis(int id, String description, String statut, String dateCreation) {
        this.id = id;
        this.description = description;
        this.statut = statut;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
                ", statut='" + statut + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Colis
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Colis colis = (Colis) o;
//        return id == colis.id && Objects.equals(codeSuivi, colis.codeSuivi);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, codeSuivi);
//    }
}