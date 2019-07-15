package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favor", schema = "2019summervacation", catalog = "")
public class FavorEntity {
    private int favorId;
    private int userId;
    private int artworkId;

    @Id
    @Column(name = "favorID")
    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
    }

    @Basic
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "artworkID")
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
        FavorEntity that = (FavorEntity) o;
        return favorId == that.favorId &&
                userId == that.userId &&
                artworkId == that.artworkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorId, userId, artworkId);
    }
}
