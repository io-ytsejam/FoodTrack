package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "PHOTO", schema = "TEST", catalog = "")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","recipeEntity"})
public class PhotoEntity {

    @Id
    @Column(name = "PHOTOID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long photoid;

    @Lob
    @Column(name = "PHOTO",columnDefinition = "CLOB NOT NULL")
    private String photoLink;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    private RecipeEntity recipeEntity;

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    public PhotoEntity() {

    }

    public PhotoEntity(String photoLink)
    {
        this.photoLink=photoLink;
    }

    public long getPhotoid() {
        return photoid;
    }

    public void setPhotoid(long photoid) {
        this.photoid = photoid;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoEntity that = (PhotoEntity) o;

        if (photoid != that.photoid) return false;
        if (photoLink != null ? !photoLink.equals(that.photoLink) : that.photoLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (photoid ^ (photoid >>> 32));
        result = 31 * result + (photoLink != null ? photoLink.hashCode() : 0);
        return result;
    }
}

