package br.com.wiltoncosta.portfolio_mobile.model;

import java.util.Date;
import java.util.List;

public class SkillCategory {

    private long idSkillCategory;
    private String name;
    private String image;
    private Date dateCreated;

    public long getIdSkillCategory() {
        return idSkillCategory;
    }

    public void setIdSkillCategory(long idSkillCategory) {
        this.idSkillCategory = idSkillCategory;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkillCategory)) {
            return false;
        }
        return this.getIdSkillCategory() == ((SkillCategory)obj).getIdSkillCategory();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)idSkillCategory;
        return result;
    }
}
