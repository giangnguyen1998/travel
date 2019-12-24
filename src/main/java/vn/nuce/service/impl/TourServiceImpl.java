package vn.nuce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.dto.TourDto;
import vn.nuce.entity.TourEntity;
import vn.nuce.repository.impl.TourRepositoryImpl;
import vn.nuce.service.TourService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepositoryImpl repository;

    private ModelMapper mapper = null;

    private ModelMapper getInstanceMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    @Override
    public void saveTour(TourDto tourDto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tourDto.setCreatedDate(timestamp);
        repository.save(getInstanceMapper().map(tourDto, TourEntity.class));
    }

    @Override
    public List<TourDto> findAllTours() {
        List<TourEntity> entities = repository.findAll();
        List<TourDto> dtos = new ArrayList<>();
        if (entities.size() > 0) {
            for (TourEntity entity : entities) {
                dtos.add(getInstanceMapper().map(entity, TourDto.class));
            }
        }
        return dtos;
    }

    @Override
    public TourDto findOneTour(Long id) {
        return getInstanceMapper().map(repository.findOne(id), TourDto.class);
    }

    @Override
    public TourDto updateTour(TourDto dto) {
        TourEntity entity = repository.update(getInstanceMapper().map(dto, TourEntity.class));
        return getInstanceMapper().map(
                entity,
                TourDto.class
        );
    }
}
