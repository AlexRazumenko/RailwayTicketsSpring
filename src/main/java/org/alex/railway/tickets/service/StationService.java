package org.alex.railway.tickets.service;

import lombok.extern.slf4j.Slf4j;
import org.alex.railway.tickets.entity.Station;
import org.alex.railway.tickets.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StationService {

    private StationRepository repository;

    @Autowired
    public StationService(StationRepository repository) {
        this.repository = repository;
    }

    public List<Station> getAll() {
        return repository.findAll();
    }

    public Station findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Station with name " + id + " is not exists."));
    }

    public Station findByName(String name) {
        return repository.getByName(name).orElseThrow(()-> new IllegalArgumentException("Station with name " + name + " is not exists."));
    }

    public void save(Station station) {
        try {
            repository.save(station);
        } catch (Exception e) {
            log.warn("Station name must be unique.");
        }
    }

    public void delete(String name) {
        repository.delete(repository.getByName(name).orElseThrow(()-> new IllegalArgumentException("Station with name " + name + " is not exists.")));
    }

    public void delete(Long id) {
        repository.deleteById(id);

    }

}
