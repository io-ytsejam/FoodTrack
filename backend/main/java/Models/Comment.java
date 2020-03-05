package Models;

import javax.persistence.*;


@Entity
public class Comment
{
    @Id @GeneratedValue
    @Column(name="COMMENT_ID", nullable=false)
    private int commentId;

    @Column(name="CONTENT", nullable=false)
    private String content;

    public Comment(int commentId, String content) {
        this.commentId = commentId;
        this.content=content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


