package main.java.Models;

import javax.persistence.*;

@Entity
public class Photo
{
    @Id
    @GeneratedValue
    @Column(name="PHOTO_ID", nullable=false)
    private int photoId;

    @Column(name="PHOTO_LINK", nullable=false)
    private String photo_link;

    public Photo() {
    }

    public Photo(int photoId, String photo_link) {
        this.photoId = photoId;
        this.photo_link = photo_link;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Recipe recipe;
}