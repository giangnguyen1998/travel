package vn.nuce.service;

import vn.nuce.dto.ImageDto;

import java.util.List;

public interface ImageService {
    void saveImage(ImageDto dto);
    List<ImageDto> findAllImage(Long tourId);
}
