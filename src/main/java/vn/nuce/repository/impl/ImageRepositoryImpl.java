package vn.nuce.repository.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.data.impl.CrudRepositoryImpl;
import vn.nuce.entity.ImageEntity;
import vn.nuce.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ImageRepositoryImpl extends CrudRepositoryImpl<Long, ImageEntity> implements ImageRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<ImageEntity> findAllImageByTour(Long tourId) {
        List<ImageEntity> imageEntities = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("FROM ImageEntity im");
            if (tourId != null) {
                sql.append(" WHERE im.tourEntity.tourId =:tourId");
            }
            Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
            if (tourId != null) {
                query.setParameter("tourId", tourId);
                imageEntities = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageEntities;
    }
}
