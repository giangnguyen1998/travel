package vn.nuce.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.data.impl.CrudRepositoryImpl;
import vn.nuce.entity.BookEntity;
import vn.nuce.entity.TourEntity;
import vn.nuce.entity.UserEntity;
import vn.nuce.repository.BookRepository;


@Repository
@Transactional
public class BookRepositoryImpl extends CrudRepositoryImpl<Long, BookEntity> implements BookRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean getOneBook(UserEntity userEntity, TourEntity tourEntity) {
        BookEntity bookEntity = new BookEntity();
        boolean check = false;
        try {
            StringBuilder sql = new StringBuilder("FROM BookEntity bk");
            if (userEntity.getUserId() != null && tourEntity.getTourId() != null) {
                sql.append(" WHERE bk.userEntity.userId =:userId AND bk.tourEntity.tourId =:tourId");
            }
            Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
            if (userEntity.getUserId() != null && tourEntity.getTourId() != null) {
                query.setParameter("userId", userEntity.getUserId());
                query.setParameter("tourId", tourEntity.getTourId());
            }
            bookEntity = (BookEntity) query.uniqueResult();
            if (bookEntity != null) {
                check = true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return check;
    }
}
