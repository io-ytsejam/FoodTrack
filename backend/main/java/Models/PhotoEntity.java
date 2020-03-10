package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "PHOTO", schema = "TEST", catalog = "")
public class PhotoEntity {
    private long photoid;
    private String photoLink;

    public PhotoEntity() {
    }

    @Id
    @Column(name = "PHOTOID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getPhotoid() {
        return photoid;
    }

    public void setPhotoid(long photoid) {
        this.photoid = photoid;
    }

    @Basic
    @Column(name = "PHOTO_LINK")
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
