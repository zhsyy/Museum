package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "favor", schema = "2019summervacation")
public class FavorEntity {
    private int favorId;
    private int userId;
    private int artworkId;
    private Timestamp time;

    public FavorEntity() {
    }

    public FavorEntity(int favorId, int userId, int artworkId, Timestamp time) {
        this.favorId = favorId;
        this.userId = userId;
        this.artworkId = artworkId;
        this.time = time;
    }

    @Id
    @Column(name = "favorId")
    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
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
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavorEntity that = (FavorEntity) o;
        return favorId == that.favorId &&
                userId == that.userId &&
                artworkId == that.artworkId &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorId, userId, artworkId, time);
    }
}
