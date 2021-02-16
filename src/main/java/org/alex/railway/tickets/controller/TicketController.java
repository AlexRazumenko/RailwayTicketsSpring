package org.alex.railway.tickets.controller;

import org.alex.railway.tickets.entity.Ticket;
import org.alex.railway.tickets.entity.Train;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@SessionAttributes("currentUser")
public class TicketController {

    private final ModelAndView TICKETSVIEW = new ModelAndView("tickets");
    private final ModelAndView SINGLETICKETVIEW = new ModelAndView("ticket");
    private final ModelAndView SINGLETRAINPAGEVIEW = new ModelAndView("train");
    private TicketService ticketService;
    private final User GUEST = User.builder().id(0L).firstName("Guest").role(RoleType.ROLE_GUEST).build();

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ModelAttribute("currentUser")
    public User createUser() {
        return GUEST;
    }

    @GetMapping("/tickets")
    public ModelAndView findTicketsByUser(@ModelAttribute("currentUser") User user) {
        TICKETSVIEW.setViewName("tickets");
        TICKETSVIEW.addObject("allTicketsByUser", ticketService.findTicketsByUser(user));
        return TICKETSVIEW;
    }

@GetMapping("/freeSeats")
    public ModelAndView searchFreeSeats (@ModelAttribute("train") Train train, @ModelAttribute("departDate") String departDate) {
    System.out.println(departDate);
    SINGLETRAINPAGEVIEW.setViewName("train");
SINGLETRAINPAGEVIEW.addObject("freeSeats", ticketService.findFreeSeatsByTrainAndDepartDate(train, LocalDate.parse(departDate)));
        SINGLETRAINPAGEVIEW.addObject("train", train);
        SINGLETRAINPAGEVIEW.addObject("newTicket", new Ticket());
        SINGLETRAINPAGEVIEW.addObject("departDate", departDate);
        return SINGLETRAINPAGEVIEW;
    }

@PostMapping("/bookTicket")
    public ModelAndView bookTicket (@RequestParam("train") Train train, @RequestParam("departDate") Date departDate,
                                   @RequestParam ("place") int seat , @ModelAttribute("currentUser") User user) {
  Ticket newTicket = Ticket.builder().train(train).departDate(departDate.toLocalDate())
          .user(user).place(seat).occupied(true).build();
    System.out.println(newTicket);
        ticketService.save(newTicket);
        SINGLETICKETVIEW.addObject("newTicket", newTicket);
//    SINGLETRAINPAGEVIEW.addObject("newTicket", new Ticket());
        return SINGLETICKETVIEW;
    }
}
