package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "artworks", schema = "2019summervacation")
public class ArtworksEntity {
    private int artworkId;
    private String imageFileName;
    private String videoFileName;
    private String title;
    private String description;
    private int yearOfWork;
    private String location;
    private int view;
    private String type;
    private Timestamp timeReleased;

    public ArtworksEntity() {
    }

    public ArtworksEntity(String imageFileName, String videoFileName, String title, String description, int yearOfWork, String location, int view, String type, Timestamp timeReleased) {
        this.imageFileName = imageFileName;
        this.videoFileName = videoFileName;
        this.title = title;
        this.description = description;
        this.yearOfWork = yearOfWork;
        this.location = location;
        this.view = view;
        this.type = type;
        this.timeReleased = timeReleased;
    }

    @Id
    @Column(name = "artworkId")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Basic
    @Column(name = "imageFileName")
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Basic
    @Column(name = "videoFileName")
    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "yearOfWork")
    public int getYearOfWork() {
        return yearOfWork;
    }

    public void setYearOfWork(int yearOfWork) {
        this.yearOfWork = yearOfWork;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "view")
    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "timeReleased")
    public Timestamp getTimeReleased() {
        return timeReleased;
    }

    public void setTimeReleased(Timestamp timeReleased) {
        this.timeReleased = timeReleased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworksEntity that = (ArtworksEntity) o;
        return artworkId == that.artworkId &&
                yearOfWork == that.yearOfWork &&
                view == that.view &&
                Objects.equals(imageFileName, that.imageFileName) &&
                Objects.equals(videoFileName, that.videoFileName) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(location, that.location) &&
                Objects.equals(type, that.type) &&
                Objects.equals(timeReleased, that.timeReleased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artworkId, imageFileName, videoFileName, title, description, yearOfWork, location, view, type, timeReleased);
    }
}
