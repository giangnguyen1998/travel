package vn.nuce.service;

import org.springframework.stereotype.Service;
import vn.nuce.dto.BookDto;
import vn.nuce.dto.TourDto;
import vn.nuce.dto.UserDto;

import java.util.List;

public interface BookService {
    void saveBook(BookDto dto);
    boolean getOneBook(UserDto userDto, TourDto tourDto);
    List<BookDto> getAllBooks();
}
