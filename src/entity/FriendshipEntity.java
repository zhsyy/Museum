package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friendship", schema = "2019summervacation")
public class FriendshipEntity {
    private int friendshipId;
    private int senderId;
    private int receiverId;
    private String status;

    public FriendshipEntity() {
    }

    @Id
    @Column(name = "friendshipId")
    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    @Basic
    @Column(name = "senderId")
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receiverId")
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipEntity that = (FriendshipEntity) o;
        return friendshipId == that.friendshipId &&
                senderId == that.senderId &&
                receiverId == that.receiverId &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendshipId, senderId, receiverId, status);
    }
}
