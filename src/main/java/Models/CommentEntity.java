package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTARY", schema = "TEST")
public class CommentEntity {
    private long commentid;
    private String content;

    public CommentEntity(long commentid, String content)
    {
        this.commentid=commentid;
        this.content=content;
    }

    public CommentEntity() {
    }

    @Id
    @Column(name = "COMMENTID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
