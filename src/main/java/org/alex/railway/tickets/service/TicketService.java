package org.alex.railway.tickets.service;

import org.alex.railway.tickets.entity.Ticket;
import org.alex.railway.tickets.entity.Train;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TicketService {

    private TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket findById (Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Ticket with id " + id + " is not exists"));
    }

    public List<Ticket> findTicketsByUser (User user) {
        System.out.println("User: " + user);
        return repository.findByUserOrderByDepartDateDesc(user);
    }

//    public void addTicketsByTrainAndDate (Train train, LocalDate date, int count) {
//        Ticket templateTicket = Ticket.
//        for (int i = 1; i <= count ; i++) {
//
//        }
//
//    }

    public List<Ticket> findByTrainAndDepartDate (Train train, LocalDate date) {
        return repository.findByTrainAndDepartDate(train, date);
    }

    public List<Integer> findFreeSeatsByTrainAndDepartDate (Train train, LocalDate date) {
        List<Integer> occupiedSeats = repository.findByTrainAndDepartDate(train, date).stream()
                .mapToInt(Ticket::getPlace).boxed().collect(Collectors.toList());
        List<Integer> freeSeats = IntStream.rangeClosed(1,20).boxed().collect(Collectors.toList());
        freeSeats.removeAll(occupiedSeats);
        return freeSeats;
    }

    public List<Ticket> findByTrainAndDepartDateAndOccupied (Train train, LocalDate departDate, boolean isOccupied) {
        return repository.findByTrainAndDepartDateAndOccupied(train, departDate, isOccupied);
    }

    public List<Ticket> findByUserOrderByDepartDate (User user) {
        return repository.findByUserOrderByDepartDateDesc(user);
    }

    public List<Ticket> findByUserOrderByTrain (User user) {
        return repository.findByUserOrderByTrain(user);
    }

    public Ticket save (Ticket ticket) {
        return repository.save(ticket);
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
