package vn.nuce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.nuce.dto.BookDto;
import vn.nuce.dto.TourDto;
import vn.nuce.dto.UserDto;
import vn.nuce.entity.BookEntity;
import vn.nuce.entity.TourEntity;
import vn.nuce.entity.UserEntity;
import vn.nuce.repository.impl.BookRepositoryImpl;
import vn.nuce.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepositoryImpl repository;

    private ModelMapper mapper = null;

    private ModelMapper getInstanceMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    @Override
    public void saveBook(BookDto dto) {
        repository.save(
                getInstanceMapper().map(
                        dto,
                        BookEntity.class
                )
        );
    }

    @Override
    public boolean getOneBook(UserDto userDto, TourDto tourDto) {
        return
                repository.getOneBook(
                        getInstanceMapper().map(
                                userDto, UserEntity.class
                        ),
                        getInstanceMapper().map(
                                tourDto, TourEntity.class
                        )
                );
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> entities = repository.findAll();
        List<BookDto> dtos = new ArrayList<>();
        if (entities.size() > 0) {
            for (BookEntity entity : entities) {
                BookDto dto = new BookDto();
                dto.setUserEntity(getInstanceMapper().map(
                    entity.getUserEntity(),
                    UserDto.class
                ));
                entity.getTourEntity().setImageEntities(null);
                dto.setTourEntity(getInstanceMapper().map(
                    entity.getTourEntity(),
                    TourDto.class
                ));
                dto.setPhone(entity.getPhone());
                dto.setAddress(entity.getAddress());
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
