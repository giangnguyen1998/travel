package vn.nuce.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tour")
public class TourEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;
    @Column(name = "name")
    private String tourName;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @Column(name = "address")
    private String tourAddress;
    @Column(name = "description")
    private String tourDescription;
    @Column(name = "price")
    private Long tourPrice;
    @Column(name = "breakfast")
    private Long tourBreakFast;
    @OneToMany(mappedBy = "tourEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImageEntity> imageEntities;
    @OneToMany(mappedBy = "tourEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookEntity> bookEntities;

    public List<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(List<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getTourAddress() {
        return tourAddress;
    }

    public void setTourAddress(String tourAddress) {
        this.tourAddress = tourAddress;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public Long getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(Long tourPrice) {
        this.tourPrice = tourPrice;
    }

    public Long getTourBreakFast() {
        return tourBreakFast;
    }

    public void setTourBreakFast(Long tourBreakFast) {
        this.tourBreakFast = tourBreakFast;
    }
}
