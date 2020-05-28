package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Subselect("select name,DESCRIPTION,PHOTO,NICKNAME,r1.RECIPEID,"+
        "(select avg(value) from RATING r3 where r3.RECIPEID=r1.RECIPEID) avgrating,"+
       "(select count(1) from COMMENTARY c1 where c1.RECIPEID=r1.RECIPEID) commentcount,"+
        "(select sum(time) from STEP s1 join RECIPE_STEP rs1 on s1.STEPID = rs1.STEPID where rs1.RECIPEID=r1.RECIPEID) totaltime "+
        "from recipe r1 join person p1 on r1.PERSONID=p1.PERSONID "+
        "left join photo ph1 on ph1.RECIPEID=r1.RECIPEID "+
        "where r1.ROWID||p1.ROWID||ph1.ROWID in "+
        "(select min(r2.ROWID||p2.ROWID||ph2.ROWID) "+
        "from recipe r2 join person p2 on r2.PERSONID=p2.PERSONID "+
        "left join photo ph2 on ph2.RECIPEID=r2.RECIPEID "+
        "group by r2.RECIPEID)")
@Immutable
@Synchronize({"RECIPE","PHOTO","COMMENTARY","RATING"})
public class RecipeThumbnail implements Serializable {
    @Id
    @Column(name="RECIPEID")
    private Long recipeid;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String photo;
    @Column
    private String nickname;

    private Long avgrating;

    private int commentcount;

    private Long totaltime;

    @ManyToMany
    @JoinTable(name = "recipe_ingredient",
            joinColumns = {@JoinColumn(name = "recipeid")},
            inverseJoinColumns = {@JoinColumn(name = "ingredientid")})
    private List<IngredientEntity> ingredientList;

    public Long getRecipeid() {
        return recipeid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getAvgrating() {
        return avgrating;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public Long getTotaltime() {
        return totaltime;
    }

    @JsonGetter
    @JsonProperty("ingredients")
    public List<String> getIngredients(){
       return ingredientList.stream().map(IngredientEntity::getName).collect(Collectors.toList());
    }
}
