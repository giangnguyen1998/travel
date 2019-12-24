package vn.nuce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.dto.ImageDto;
import vn.nuce.entity.ImageEntity;
import vn.nuce.repository.impl.ImageRepositoryImpl;
import vn.nuce.service.ImageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepositoryImpl repository;

    private ModelMapper mapper = null;

    private ModelMapper getInstanceMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    @Override
    public void saveImage(ImageDto dto) {
        repository.save(getInstanceMapper().map(dto, ImageEntity.class));
    }

    @Override
    public List<ImageDto> findAllImage(Long tourId) {
        List<ImageEntity> entities = repository.findAllImageByTour(tourId);
        List<ImageDto> dtos = new ArrayList<>();
        if (entities.size() > 0) {
            for (ImageEntity entity : entities) {
                dtos.add(getInstanceMapper().map(entity, ImageDto.class));
            }
        }
        return dtos;
    }
}
