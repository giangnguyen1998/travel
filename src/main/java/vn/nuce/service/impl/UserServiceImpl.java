package vn.nuce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.dto.UserDto;
import vn.nuce.entity.UserEntity;
import vn.nuce.repository.impl.UserRepositoryImpl;
import vn.nuce.service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryImpl repository;

    private ModelMapper mapper = null;

    private ModelMapper getInstanceMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    @Override
    public Object[] checkLogin(String username, String password) {
        UserDto dto = null;
        Object[] objects = repository.checkLogin(username, password);
        boolean isSuccess = (boolean) objects[0];
        if (isSuccess) {
            dto = getInstanceMapper().map(objects[1], UserDto.class);
        }
        return new Object[]{isSuccess, dto};
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserEntity> entities = repository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        if (entities.size() > 0) {
            for (UserEntity entity : entities) {
                dtos.add(getInstanceMapper().map(entity, UserDto.class));
            }
        }
        return dtos;
    }

    @Override
    public void saveUser(UserDto userDto) {
        repository.save(getInstanceMapper().map(userDto, UserEntity.class));
    }

    @Override
    public Integer deleteUser(List<Long> ids) {
        return repository.delete(ids);
    }

    @Override
    public UserDto findOneUser(Long id) {
        return getInstanceMapper().map(repository.findOne(id),UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        UserEntity entity = repository.update(getInstanceMapper().map(dto,UserEntity.class));
        return getInstanceMapper().map(
                entity,
                UserDto.class
        );
    }
}
