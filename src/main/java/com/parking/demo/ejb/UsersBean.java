package com.parking.demo.ejb;

import com.parking.demo.common.UserDto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.parkinglot.entities.User;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersBean
{

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers()
    {
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users)
        {
            userDtoList.add(new UserDto(user.getUsername(), user.getEmail()));
        }

        return userDtoList;
    }
}