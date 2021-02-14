package org.alex.railway.tickets.service;

import org.alex.railway.tickets.dto.UserDTO;
import org.alex.railway.tickets.dto.UsersDTO;
import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

//@Slf4j
@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
        init();
    }

    private void init () {
        //TODO Optional
            if (repository.getCount() == 0) {
                User admin = User.builder().email("admin@railway.com")
                        .firstName("Ad")
                        .lastName("Min")
                        .password("123")
                        .role(RoleType.ROLE_ADMIN)
                        .build();
                repository.save(admin);
            }
    }

    public UsersDTO getAll() {
        return  new UsersDTO(repository.findAll());
    }

    public User findByLogin(UserDTO userDTO) {
        return repository.findByEmail(userDTO.getEmail())
                .orElseGet(()->User.builder().id(0L).firstName("Guest").role(RoleType.ROLE_GUEST).build());
    }

    public User findById(Long id) {
        return  repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " is not exists"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + s + " is not exists"));
    }

    public void save(User user) {
        try {
            repository.save(user);
        } catch (Exception e) {
            logger.error("Error occured while saving");
        }
    }

    public void delete (Long id) {
        repository.delete(repository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id " + id + " not exists!")));
    }

    @GetMapping("/adminRights/")
    public void setAdminRights (User user) {
        if (user.getRole() != RoleType.ROLE_ADMIN) {
            user.setRole(RoleType.ROLE_ADMIN);
            repository.save(user);
        }
    }

    public void setUserRights (User user) {;
        if (user.getRole() != RoleType.ROLE_USER) {
            user.setRole(RoleType.ROLE_USER);
            repository.save(user);
        }
    }
}
