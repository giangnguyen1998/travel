package vn.nuce.repository;

import vn.nuce.data.CrudRepository;
import vn.nuce.entity.ImageEntity;

import java.util.List;

public interface ImageRepository extends CrudRepository<Long, ImageEntity> {
    List<ImageEntity> findAllImageByTour(Long tourId);
}
