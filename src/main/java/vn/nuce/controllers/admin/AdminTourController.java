package vn.nuce.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.nuce.Utils;
import vn.nuce.dto.BookDto;
import vn.nuce.dto.ImageDto;
import vn.nuce.dto.TourDto;
import vn.nuce.dto.UserDto;
import vn.nuce.service.BookService;
import vn.nuce.service.impl.ImageServiceImpl;
import vn.nuce.service.impl.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RequestMapping("/admin")
@Controller
public class AdminTourController {

    @Autowired
    TourServiceImpl service;

    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    BookService bookService;

    @GetMapping("/tours")
    public String showPage(HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);
        List<TourDto> tours = new ArrayList<>();
        List<BookDto> books = new ArrayList<>();
        try {
            tours = service.findAllTours();
            books = bookService.getAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("tours", tours);
        modelMap.addAttribute("books", books);
        if (session.getAttribute("status") != null) {
            modelMap.addAttribute("status", session.getAttribute("status"));
            session.removeAttribute("status");
        }
        return "/admin/tour";
    }

    @GetMapping("/tour/{id}")
    public String showTourById(@PathVariable Long id, HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);

        TourDto dto = service.findOneTour(id);
        modelMap.addAttribute("images", imageService.findAllImage(id));
        modelMap.addAttribute("tour", dto);

        return "/admin/tour_info";
    }

    @GetMapping("/tour/info")
    public String insertTourForm(HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);

        return "/admin/tour_info";
    }

    @PostMapping("/tour/info")
    public String insertTour(@RequestParam(name = "action") String action,
                             @RequestParam(name = "tour_description") String description,
                             @RequestParam(name = "tour_name") String name,
                             @RequestParam(name = "breakfast") Long breakfast,
                             @RequestParam(name = "price") Long price,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "images") List<MultipartFile> files,
                             HttpSession session, HttpServletRequest request) {
        TourDto dto = new TourDto();
        dto.setTourName(name);
        dto.setTourPrice(price);
        dto.setTourBreakFast(breakfast);
        dto.setTourAddress(address);
        dto.setTourDescription(description);
        switch (action) {
            case "create":
                List<String> fileNames = new ArrayList<>();
                if (null != files && files.size() > 0) {
                    for (MultipartFile multipartFile : files) {

                        String fileName = multipartFile.getOriginalFilename();
                        fileNames.add(fileName);
                        File imageFile = new File("E:\\anroid\\travel\\src\\main\\webapp\\resources\\image", fileName);
                        try {
                            multipartFile.transferTo(imageFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    dto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    TourDto tourDto = service.updateTour(dto);
                    for (String fileName : fileNames) {
                        ImageDto imageDto = new ImageDto();
                        imageDto.setImageUrl("resources/image/" + fileName);
                        imageDto.setTourEntity(tourDto);
                        imageService.saveImage(imageDto);
                    }
                    session.setAttribute("status", "success");
                } catch (Exception e) {
                    session.setAttribute("status", "fail");
                    e.printStackTrace();
                }
                break;
            case "update":
                try {
                    Long tourId = Long.valueOf(request.getParameter("tourId"));
                    Timestamp createdDate = Timestamp.valueOf(request.getParameter("createdDate"));
                    dto.setTourId(tourId);
                    dto.setCreatedDate(createdDate);
                    service.updateTour(dto);
                    session.setAttribute("status", "success");
                } catch (Exception e) {
                    session.setAttribute("status", "fail");
                    e.printStackTrace();
                }
                break;
            default:
        }
        return "redirect:/admin/tours";
    }

    private void setUser(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("user") != null) {
            UserDto dto = (UserDto) session.getAttribute("user");
            modelMap.addAttribute("dto", dto);
        }
    }

}
