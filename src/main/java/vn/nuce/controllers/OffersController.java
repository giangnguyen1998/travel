package vn.nuce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vn.nuce.dto.BookDto;
import vn.nuce.dto.TourDto;
import vn.nuce.dto.UserDto;
import vn.nuce.repository.BookRepository;
import vn.nuce.service.BookService;
import vn.nuce.service.impl.BookServiceImpl;
import vn.nuce.service.impl.TourServiceImpl;

import javax.servlet.http.HttpSession;

@SuppressWarnings("ALL")
@RequestMapping("/offers")
@Controller
public class OffersController {

    @Autowired
    TourServiceImpl service;

    @Autowired
    BookServiceImpl bookService;

    @GetMapping
    public String showPage(ModelMap modelMap, HttpSession session) {
        UserDto dto = null;
        if (session.getAttribute("user") != null) {
            dto = (UserDto) session.getAttribute("user");
        }
        if (dto != null) {
            modelMap.addAttribute("dto", dto);
        }
        modelMap.addAttribute("tours", service.findAllTours());
        return "offers";
    }

    @PostMapping("/books")
    public String bookTour(
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "tourId") Long tourId,
            HttpSession session,
            ModelMap modelMap
    ) {
        UserDto dto = null;
        if (session.getAttribute("user") != null) {
            dto = (UserDto) session.getAttribute("user");
        }
        if (dto != null) {
            modelMap.addAttribute("dto", dto);
            try {
                TourDto tourDto = new TourDto();
                tourDto.setTourId(tourId);
                BookDto bookDto = new BookDto();
                if (bookService.getOneBook(dto, tourDto)) {
                    session.setAttribute("status","Đã book rồi :v");
                } else {
                    bookDto.setTourEntity(tourDto);
                    bookDto.setUserEntity(dto);
                    bookDto.setPhone(phone);
                    bookDto.setAddress(address);
                    bookService.saveBook(bookDto);
                    session.setAttribute("status","Book thành công :v");
                }
            } catch (Exception e) {
                session.setAttribute("status","Failure :v");
                e.printStackTrace();
            }
        }
        return "redirect:/offers/" + tourId;
    }

    @GetMapping("/{id}")
    public String showSingleTour(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
        UserDto dto = null;
        if (session.getAttribute("user") != null) {
            dto = (UserDto) session.getAttribute("user");
        }
        if (dto != null) {
            modelMap.addAttribute("dto", dto);
        }
        modelMap.addAttribute("tour", service.findOneTour(id));
        modelMap.addAttribute("status", session.getAttribute("status"));
        session.removeAttribute("status");
        return "single";
    }
}
