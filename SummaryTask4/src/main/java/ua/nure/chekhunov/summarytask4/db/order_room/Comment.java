package ua.nure.chekhunov.summarytask4.db.order_room;

/**
 * Simple Comment entity with getters and setters.
 *
 * @author IC
 */
public class Comment {
    private int commentId;
    private int orderId;
    private String text;
    private int userId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
