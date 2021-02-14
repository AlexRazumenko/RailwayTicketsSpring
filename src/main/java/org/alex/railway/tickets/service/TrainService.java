package org.alex.railway.tickets.service;

import org.alex.railway.tickets.dto.TrainDTO;
import org.alex.railway.tickets.entity.Station;
import org.alex.railway.tickets.entity.Train;
import org.alex.railway.tickets.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TrainService {

    private TrainRepository repository;

    @Autowired
    public TrainService(TrainRepository repository) {
        this.repository = repository;
    }

    public Train findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Train with id " + id + " is not exists"));
    }

    public Train findByNumber (int number) {
        return repository.findByNumber(number);
    }

    public List<Train> getAll() {
        return repository.findAll();
    }

    public List<Train> findTrainsDepartingFrom (Station station) {
        return repository.findTrainsByDepartStation(station);
    }

    public List<Train> findTrainsBetween (Station departStation, Station arriveStation) {
        return repository.findTrainsBetween(departStation, arriveStation);
    }

    public void save (TrainDTO trainDTO) {
        LocalTime departureTime = LocalTime.of(trainDTO.getDepartHour(), trainDTO.getDepartMinute());
        LocalTime arrivalTime = LocalTime.of(trainDTO.getArriveHour(), trainDTO.getArriveMinute());

        Train newTrain = Train.builder().number(trainDTO.getNumber())
                .departStation(trainDTO.getDepartStation())
                .arriveStation(trainDTO.getArriveStation())
                .departTime(departureTime)
                .arriveTime(arrivalTime)
                .price(trainDTO.getPrice())
                .build();
        repository.save(newTrain);
    }



    public List<Train> findTrainsArrivingTo (Station station) {
        return repository.findTrainsByArriveStation(station);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }




}
