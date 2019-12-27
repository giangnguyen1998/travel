package vn.nuce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.nuce.dto.UserDto;
import vn.nuce.service.impl.TourServiceImpl;

import javax.servlet.http.HttpSession;

@RequestMapping("/offers")
@Controller
public class OffersController {

    @Autowired
    TourServiceImpl service;

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
        return "single";
    }
}
