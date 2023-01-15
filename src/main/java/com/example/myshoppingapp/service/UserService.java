package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.enums.UserRole;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.*;
import com.example.myshoppingapp.repositories.ProductRepository;
import com.example.myshoppingapp.repositories.UserRepository;
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
    private final ModelMapper modelMapper = new ModelMapper();
    private String loggedInUser;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.loggedInUser = null;
    }

    public User login(LoginDTO loginDTO) {
        User user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).orElseThrow(NoSuchElementException::new);
        this.loggedInUser = user.getUsername();
        return user;
    }

    @Modifying
    public Boolean register(RegisterUserDTO registerUserDTO) {
        User user = this.modelMapper.map(registerUserDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setUserRole(UserRole.ADMIN);
        } else user.setUserRole(UserRole.USER);
        this.userRepository.save(user);
        this.setLoggedInUser(user.getUsername());
        return true;
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    public Long getLoggedUserId() {
        return this.userRepository.findByUsername(getLoggedInUser()).get().getId();
    }

    public UserOutputDTO getLoggedUserDTO() {
        User currentuser = this.findByUsername(getLoggedInUser());
        return this.modelMapper.map(currentuser, UserOutputDTO.class);
    }

    public void updateUser(UserInputDTO userInputDTO) {
        Long idToUpdate = this.getLoggedUserId();
        String newUsername = userInputDTO.getUsername();
        String newPassword = userInputDTO.getPassword();
        String newEmail = userInputDTO.getEmail();
        if (!newUsername.equals(this.loggedInUser)) {
            this.loggedInUser = newUsername;
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
}


