package com.backend.Models;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Subselect("select name,DESCRIPTION,PHOTO,NICKNAME,r1.RECIPEID " +
        "from recipe r1 join person p1 on r1.PERSONID=p1.PERSONID " +
        "    left join photo ph1 on ph1.RECIPEID=r1.RECIPEID " +
        "where r1.ROWID||p1.ROWID||ph1.ROWID in " +
        "      (select min(r2.ROWID||p2.ROWID||ph2.ROWID) " +
        "          from recipe r2 join person p2 on r2.PERSONID=p2.PERSONID " +
        "          left join photo ph2 on ph2.RECIPEID=r2.RECIPEID " +
        "          group by r2.RECIPEID)")
@Immutable
@Synchronize({"RECIPE","PHOTO"})
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
}
