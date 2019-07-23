package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deletehistory", schema = "2019summervacation")
public class DeletehistoryEntity {
    private int historyId;
    private int userId;
    private int artworkId;

    public DeletehistoryEntity() {
    }

    public DeletehistoryEntity(int userId, int artworkId) {
        this.userId = userId;
        this.artworkId = artworkId;
    }

    @Id
    @Column(name = "historyId")
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "artworkId")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletehistoryEntity that = (DeletehistoryEntity) o;
        return historyId == that.historyId &&
                userId == that.userId &&
                artworkId == that.artworkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, userId, artworkId);
    }
}
