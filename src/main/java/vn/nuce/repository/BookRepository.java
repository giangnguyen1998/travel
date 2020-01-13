package vn.nuce.repository;

import org.springframework.stereotype.Repository;
import vn.nuce.data.CrudRepository;
import vn.nuce.entity.BookEntity;
import vn.nuce.entity.TourEntity;
import vn.nuce.entity.UserEntity;

public interface BookRepository extends CrudRepository<Long, BookEntity> {
    boolean getOneBook(UserEntity userEntity, TourEntity tourEntity);
}
