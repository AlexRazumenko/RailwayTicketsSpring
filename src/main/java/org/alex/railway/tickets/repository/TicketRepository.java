package org.alex.railway.tickets.repository;

import org.alex.railway.tickets.entity.Ticket;
import org.alex.railway.tickets.entity.Train;
import org.alex.railway.tickets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository <Ticket, Long> {

    public List<Ticket> findByTrainAndDepartDate (Train train, LocalDate departDate);

    public List<Ticket> findByTrainAndDepartDateAndOccupied (Train train, LocalDate departDate, boolean isOccupied);

public List<Ticket> findByUserOrderByDepartDateDesc (User user);

public List<Ticket> findByUserOrderByTrain (User user);


}
