package vn.nuce.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class BookEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "tourid")
    private TourEntity tourEntity;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    public TourEntity getTourEntity() {
        return tourEntity;
    }

    public void setTourEntity(TourEntity tourEntity) {
        this.tourEntity = tourEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
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
