package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "viewhistory", schema = "2019summervacation")
public class ViewhistoryEntity implements Comparable<ViewhistoryEntity>{
    private int historyId;
    private int userId;
    private int artworkId;
    private int viewTime;
    @Override
    public int compareTo(ViewhistoryEntity o) {
        if (this.viewTime > o.viewTime) {
            return -1;
        } else {
            return 1;
        }
    }
    public ViewhistoryEntity() {
    }

    public ViewhistoryEntity(int userId, int artworkId) {
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

    @Basic
    @Column(name = "viewTime")
    public int getViewTime() {
        return viewTime;
    }

    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewhistoryEntity that = (ViewhistoryEntity) o;
        return historyId == that.historyId &&
                userId == that.userId &&
                artworkId == that.artworkId &&
                viewTime == that.viewTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, userId, artworkId, viewTime);
    }
}
