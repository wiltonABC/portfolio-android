package br.com.wiltoncosta.portfolio_mobile.datahandler;

import java.util.ArrayList;
import java.util.List;

import br.com.wiltoncosta.portfolio_mobile.model.Skill;
import br.com.wiltoncosta.portfolio_mobile.model.SkillCategory;

public class SkillsDataHandler {

    //Creates a hybrid list containing ordered Categories and Skills
    public static List getCategoriesAndSkillsList(List<Skill> skills) {

        List<SkillCategory> categories = new ArrayList<SkillCategory>();

        for (Skill skill : skills) {

            if (!categories.contains(skill.getSkillCategory())) {
                categories.add(skill.getSkillCategory());
            }

        }

        List skillsList = new ArrayList();

        for (SkillCategory category : categories) {
            skillsList.add(category);

            for (Skill skill : skills) {
                if (skill.getSkillCategory().getIdSkillCategory() == category.getIdSkillCategory())
                    skillsList.add(skill);
            }
        }

        return skillsList;

    }

}
