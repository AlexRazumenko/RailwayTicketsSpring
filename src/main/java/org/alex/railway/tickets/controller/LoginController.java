package org.alex.railway.tickets.controller;

import org.alex.railway.tickets.dto.UserDTO;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    private final ModelAndView INDEXVIEW = new ModelAndView();
    private final User GUEST = User.builder().id(0L).firstName("Guest").role(RoleType.ROLE_GUEST).build();
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
        initIndexView();
    }

    @ModelAttribute("currentUser")
    public User createUser() {
        return GUEST;
    }

    private void initIndexView() {
        INDEXVIEW.setViewName("index");
        INDEXVIEW.addObject("mainmessage", "");
    }

@GetMapping(value = {"/", "/index"})
    public ModelAndView getINDEXVIEW(@ModelAttribute("currentUser") User currentUser){
    INDEXVIEW.setViewName("index");
    INDEXVIEW.addObject("currentUser", currentUser);
        return INDEXVIEW;
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("logUserDTO") UserDTO logUserDTO) { //, @ModelAttribute("currentUser") User currentUser

        User currentUser = userService.findByLogin(logUserDTO);
        System.out.println(currentUser);

        INDEXVIEW.addObject("currentUser", currentUser);//currentUser
        INDEXVIEW.addObject("mainmessage", currentUser.getId() == 0L ? "Wrong credentials. " : "You logged as " + currentUser.getFirstName() + " " + currentUser.getLastName());//currentUser
        INDEXVIEW.setViewName("index");
        return INDEXVIEW;
    }

    @GetMapping("/loginPage")
    public ModelAndView loginPage(@ModelAttribute("logUserDTO") UserDTO logUserDTO) {
        logUserDTO = new UserDTO();
        ModelAndView loginView = new ModelAndView();
        loginView.setViewName("loginPage");
//        loginView.addObject("currentUser", currentUser);
        loginView.addObject("logUserDTO", new UserDTO());
        loginView.addObject("newUser", new User());
        return loginView;
    }

    @GetMapping("/register")
    public String redirectToRegisterPage(@ModelAttribute("newUser") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String register (@ModelAttribute("newUser") User newUser) {
        newUser.setRole(RoleType.ROLE_USER);
        INDEXVIEW.addObject("mainmessage", "User " + newUser.getFirstName() + " " + newUser.getLastName() + " successfully registered. Please log in.");
        userService.save(newUser);
        return "redirect:/index";
    }

//    public ModelAndView adminPage(@ModelAttribute)




}
