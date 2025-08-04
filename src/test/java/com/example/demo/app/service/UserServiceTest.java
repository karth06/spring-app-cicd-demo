package com.example.demo.app.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.app.model.User;
import com.example.demo.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void saveUser(){
       User user = mockUser(1,"Karthik","test@example.com");
       when(userRepository.save(user)).thenReturn(user);
       User savedUser = userService.saveUser(user);
       assertEquals(user.getName(),savedUser.getName());
       assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
public void testUpdateUser() {
    int userId = 1;

    // Existing user in DB
    User existingUser = new User();
    existingUser.setUserId(userId);
    existingUser.setName("Old Name");
    existingUser.setEmail("old@example.com");

    // New data to update
    User newUserData = new User();
    newUserData.setName("New Name");
    newUserData.setEmail("new@example.com");

    // Expected updated user
    User updatedUser = new User();
    updatedUser.setUserId(userId);
    updatedUser.setName("New Name");
    updatedUser.setEmail("new@example.com");

    // Mocks
    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(existingUser)).thenReturn(updatedUser);

    // Act
    User result = userService.updateUser(userId, newUserData);

    // Assert
    assertEquals("New Name", result.getName());
    assertEquals("new@example.com", result.getEmail());

    // Optional: verify interactions
    verify(userRepository).findById(userId);
    verify(userRepository).save(existingUser);
}


    private User mockUser(int userId, String userName, String email){
        User user = new User();
        user.setUserId(userId);
        user.setName(userName);
        user.setEmail(email);
        return user;
    }


}
