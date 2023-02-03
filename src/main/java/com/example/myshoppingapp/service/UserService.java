package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.enums.UserRole;
import com.example.myshoppingapp.domain.products.Product;
import com.example.myshoppingapp.domain.users.*;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Getter
@Setter
@Service
public class UserService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;

    }

    public LoggedUser login(LoginDTO loginDTO) {
        User user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).orElseThrow(NoSuchElementException::new);

        this.loggedUser
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setUserRole(user.getUserRole());
        return loggedUser;
    }

    public void logout() {
        this.loggedUser.clearFields();
    }

    @Modifying
    public void register(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerUserDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        User user = this.modelMapper.map(registerUserDTO, User.class);
        if (this.userRepository.count() == 0) {
            user.setUserRole(UserRole.ADMIN);
        } else user.setUserRole(UserRole.USER);
        this.userRepository.save(user);
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    public Long getLoggedUserId() {
        return this.loggedUser.getId();
    }

    public UserOutputDTO getLoggedUserDTO() {
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername()).orElseThrow(NoSuchElementException::new);
        return this.modelMapper.map(user, UserOutputDTO.class);
    }

    public void updateUser(UserInputDTO userInputDTO) {
        Long idToUpdate = this.getLoggedUserId();
        String newUsername = userInputDTO.getUsername();
        String newPassword = userInputDTO.getPassword();
        String newEmail = userInputDTO.getEmail();
        if (!newUsername.equals(this.loggedUser.getUsername())) {
            this.loggedUser.setUsername(newUsername);
        }
        this.userRepository.updateUser(idToUpdate, newUsername, newPassword, newEmail);

    }

    //delete user, but first delete all his product lists (if any)!
    public void deleteUserById(long id) {
        Optional<List<Product>> userProducts= this.productRepository.findAllByUserId(id);
        if (userProducts.isPresent()) {
            for (Product userProduct : userProducts.get()) {
               this.productRepository.deleteById(userProduct.getId());
            }
        }
        this.userRepository.deleteById(id);
    }

    public void addBoughtProductToUser(Product product) {
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername()).orElseThrow(NoSuchElementException::new);
        user.getBoughtProducts().add(product);
        this.userRepository.saveAndFlush(user);
    }

    public User getLoggedUser (){
        return this.userRepository.findByUsername(this.loggedUser.getUsername()).orElseThrow(NoSuchElementException::new);
    }


}


