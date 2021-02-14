package org.alex.railway.tickets.controller;

import lombok.extern.slf4j.Slf4j;
import org.alex.railway.tickets.dto.StationDTO;
import org.alex.railway.tickets.dto.TrainDTO;
import org.alex.railway.tickets.entity.Station;
import org.alex.railway.tickets.entity.Ticket;
import org.alex.railway.tickets.entity.Train;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.service.StationService;
import org.alex.railway.tickets.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@SessionAttributes("currentUser")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    private final ModelAndView TRAINPAGEVIEW = new ModelAndView();
    private final ModelAndView TIMETABLEPAGEVIEW = new ModelAndView("timetable");
    private final ModelAndView SINGLETRAINPAGEVIEW = new ModelAndView("train");
    private final User GUEST = User.builder().id(0L).firstName("Guest").role(RoleType.ROLE_GUEST).build();

//@Autowired
//    public TrainController(TrainService trainService) {//, StationService stationService
//        this.trainService = trainService;
////        this.stationService = stationService;
//        initTrainsPageView();
//    }

    @ModelAttribute("currentUser")
    public User createUser() {
        return GUEST;
    }

    private void initTrainsPageView() {
        TRAINPAGEVIEW.setViewName("trainsPage");
        TRAINPAGEVIEW.addObject("allTrains", trainService.getAll());
        TRAINPAGEVIEW.addObject("allStations", stationService.getAll());
//        TRAINPAGEVIEW.addObject("newTrain", new TrainDTO());
        TRAINPAGEVIEW.addObject("deletedTrain", new Train());
    }

    @GetMapping("/admin/trainsPage")
    public ModelAndView viewTrainsPage(@ModelAttribute("currentUser")User currentUser) {
        if (currentUser.getRole() != RoleType.ROLE_ADMIN)
            return new ModelAndView("index");
        TRAINPAGEVIEW.setViewName("trainsPage");
        TRAINPAGEVIEW.addObject("allTrains", trainService.getAll());
        TRAINPAGEVIEW.addObject("allStations", stationService.getAll());
        TRAINPAGEVIEW.addObject("newTrain", new TrainDTO());
        TRAINPAGEVIEW.addObject("deletedTrain", new Train());
        return TRAINPAGEVIEW;
    }

    @RequestMapping(value = "/admin/addTrain", method = RequestMethod.POST)
    public String addTrain (@ModelAttribute("newTrain") TrainDTO newTrain) {
//    ModelAndView modelAndView = new ModelAndView("trainsPage");
        System.out.println(newTrain);
        trainService.save(newTrain);
        TRAINPAGEVIEW.addObject("allTrains", trainService.getAll());
        return "redirect:/admin/trainsPage";
    }

    @GetMapping("/timetable")
    public ModelAndView viewTimetablePage(@ModelAttribute("currentUser")User currentUser) {
        System.out.println("In controller");
        if (currentUser.getRole() != RoleType.ROLE_USER)
            return new ModelAndView("index");
        TIMETABLEPAGEVIEW.setViewName("timetable");
        TIMETABLEPAGEVIEW.addObject("stations", new StationDTO());
        TIMETABLEPAGEVIEW.addObject("station", new Station());
        TIMETABLEPAGEVIEW.addObject("allStations", stationService.getAll());
        return TIMETABLEPAGEVIEW;
    }

    @GetMapping("/user/trainsByDepartStation")
    public ModelAndView getTrainsByDepartStation (@ModelAttribute("station") Station station) {
        TIMETABLEPAGEVIEW.setViewName("timetable");
        TIMETABLEPAGEVIEW.addObject("allStations", stationService.getAll());
         TIMETABLEPAGEVIEW.addObject("trains", trainService.findTrainsDepartingFrom(station));
         return TIMETABLEPAGEVIEW;
    }

    @GetMapping("/user/trainsBetweenStations")
    public ModelAndView getTrainsByDepartAndArriveStation (StationDTO stations) {
        TIMETABLEPAGEVIEW.setViewName("timetable");
        TIMETABLEPAGEVIEW.addObject("allStations", stationService.getAll());
        TIMETABLEPAGEVIEW.addObject("trains",
                trainService.findTrainsBetween(stations.getDepartStation(), stations.getArriveStation()));
        return TIMETABLEPAGEVIEW;
    }

    @GetMapping("/trainPage")
    public ModelAndView viewTrainPage (@ModelAttribute("train") Train train) {
        SINGLETRAINPAGEVIEW.setViewName("train");
        System.out.println(train);
        SINGLETRAINPAGEVIEW.addObject("train", train);
        SINGLETRAINPAGEVIEW.addObject("newTicket", new Ticket());
        return SINGLETRAINPAGEVIEW;
    }

}
