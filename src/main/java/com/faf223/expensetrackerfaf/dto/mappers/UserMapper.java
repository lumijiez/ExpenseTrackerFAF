package com.faf223.expensetrackerfaf.dto.mappers;

import com.faf223.expensetrackerfaf.dto.UserCreationDTO;
import com.faf223.expensetrackerfaf.dto.UserDTO;
import com.faf223.expensetrackerfaf.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername());
    }

    public ArrayList<UserDTO> toDto(ArrayList<User> user) {

        ArrayList<UserDTO> list = new ArrayList<>();

        for (User u: user)
            list.add(toDto(u));

        return list;
    }

    public User toUser(UserCreationDTO userDTO) {
      
        User user = new User();
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        return user;
    }

}
