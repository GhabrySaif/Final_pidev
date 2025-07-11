package models;

import java.time.LocalDate;

public class HistoriqueSuivi {
    private LocalDate date;
    private String statut;
    private String localisation;
    private String commentaire;

    public HistoriqueSuivi(LocalDate date, String statut, String localisation, String commentaire) {
        this.date = date;
        this.statut = statut;
        this.localisation = localisation;
        this.commentaire = commentaire;
    }

    // Getters and setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
