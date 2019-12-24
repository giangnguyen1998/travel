package vn.nuce.dto;

import org.springframework.web.multipart.MultipartFile;
import vn.nuce.entity.ImageEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class TourDto implements Serializable {
    private Long tourId;

    private String tourName;

    private Timestamp createdDate;

    private String tourAddress;

    private String tourDescription;

    private Long tourPrice;

    private Long tourBreakFast;

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
