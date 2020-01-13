package vn.nuce.dto;

import java.io.Serializable;

public class BookDto implements Serializable {
    private TourDto tourEntity;
    private UserDto userEntity;
    private String phone;
    private String address;

    public TourDto getTourEntity() {
        return tourEntity;
    }

    public void setTourEntity(TourDto tourEntity) {
        this.tourEntity = tourEntity;
    }

    public UserDto getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserDto userEntity) {
        this.userEntity = userEntity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
