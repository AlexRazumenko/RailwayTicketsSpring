package org.alex.railway.tickets.dto;

import lombok.Builder;
import lombok.ToString;
import org.alex.railway.tickets.entity.User;

import java.util.List;


@Builder
@ToString
public class UsersDTO {
    private List<User> users;

    public UsersDTO(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
