package org.alex.railway.tickets.controller;

import lombok.extern.slf4j.Slf4j;
import org.alex.railway.tickets.dto.UsersDTO;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@SessionAttributes(value = "currentUser")
public class UserController {
    private final ModelAndView INDEXVIEW = new ModelAndView();
//    private final User GUEST = User.builder().id(0L).firstName("Guest").role(RoleType.ROLE_GUEST).build();
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @ModelAttribute("currentUser")
//    public User createUser() {
//        return GUEST;
//    }

    private void initIndexView() {
        INDEXVIEW.setViewName("index");
        INDEXVIEW.addObject("mainmessage", "");
    }

    @GetMapping("/admin/adminPage")
    public ModelAndView adminPage (@ModelAttribute("currentUser") User currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser.getRole() != RoleType.ROLE_ADMIN) {
            modelAndView.setViewName("index");
            modelAndView.addObject("message", "Access denied!");
            return modelAndView;
        } else {
            modelAndView.setViewName("adminPage");
            modelAndView.addObject("adminRole", RoleType.ROLE_ADMIN);
            return modelAndView;
        }
    }

    @GetMapping("/admin/viewAllUsers")
    public ModelAndView viewAllUsers(@ModelAttribute("currentUser") User currentUser, @ModelAttribute("allUsers") UsersDTO allUsers) {
        ModelAndView modelAndView = new ModelAndView("users");
        if (currentUser.getRole() != RoleType.ROLE_ADMIN) {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("message", "Access denied.");
            return modelAndView;
        }
        modelAndView.addObject("allUsers", userService.getAll());
        modelAndView.addObject("deletedUser", new User());
        modelAndView.addObject("roles", RoleType.values());
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public ModelAndView viewUserPage(@PathVariable("id") Long id, @ModelAttribute("currentUser") User currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser.getRole() != RoleType.ROLE_ADMIN || !currentUser.getId().equals(id)) {
            modelAndView.setViewName("index");
            return modelAndView;
        }
        modelAndView.setViewName("user");
        modelAndView.addObject("user", userService.findById(id));
        if (currentUser.getRole() == RoleType.ROLE_ADMIN){
            modelAndView.addObject("userRoles", RoleType.values());
        }
        return modelAndView;
    }

    @PostMapping("/admin/deleteUser") ///{id}
    public String deleteUser(@ModelAttribute("deletedUser") User deletedUser, @ModelAttribute("currentUser") User currentUser) {//@PathVariable("id") Long id
        userService.delete(deletedUser.getId());
        return currentUser.getRole() == RoleType.ROLE_ADMIN ? "redirect:/admin/viewAllUsers" : "redirect:/";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser (@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.save(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/adminRights/{id}")
    public String grantAdminRights (@ModelAttribute("currentUser") User currentUser, @PathVariable("id") Long id) {
        if (currentUser.getRole() != RoleType.ROLE_ADMIN) return "redirect:/indexPage";
        userService.setAdminRights(userService.findById(id));
        return "redirect:/admin/viewAllUsers";
    }

    @GetMapping("/userRights/{id}")
    public String grantUserRights (@ModelAttribute("currentUser") User currentUser, @PathVariable("id") Long id) {
        if (currentUser.getRole() != RoleType.ROLE_ADMIN) return "redirect:/indexPage";
        userService.setUserRights(userService.findById(id));
        return "redirect:/admin/viewAllUsers";
    }
}
