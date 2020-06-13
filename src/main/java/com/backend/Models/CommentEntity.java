package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTARY", schema = "FDTRCK")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","person","recipe"})
public class CommentEntity {
    @Id
    @Column(name = "COMMENTID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long commentid;
    @Basic
    @Column(name = "CONTENT",nullable = false)
    private String content;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity person;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    private RecipeEntity recipe;

    public CommentEntity(long commentid, String content)
    {
        this.commentid=commentid;
        this.content=content;
    }

    public CommentEntity() {
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonGetter
    public Long getPersonid(){return person.getPersonid();}

    @JsonGetter
    public Long getRecipeid(){return recipe.getRecipeid();}

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentid != that.commentid) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (commentid ^ (commentid >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}

