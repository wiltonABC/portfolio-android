package br.com.wiltoncosta.portfolio_mobile.model;

import java.util.Date;

public class WorkDone {

    private long idWorkDone;
    private Profile profile;
    private String name;
    private String image;
    private String description;
    private Date dateCreated;
    private Date dateModified;

    public long getIdWorkDone() {
        return idWorkDone;
    }

    public void setIdWorkDone(long idWorkDone) {
        this.idWorkDone = idWorkDone;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

}
