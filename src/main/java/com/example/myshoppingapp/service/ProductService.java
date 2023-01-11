package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.products.InputProductDTO;
import com.example.myshoppingapp.models.products.OutputProductDTO;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.ProductRepository;
import com.example.myshoppingapp.repositories.UserRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Getter
@Service
public class ProductService {
    private final UserService userService;


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProductService(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Modifying
    public void addProduct(InputProductDTO inputProductDTO) {
        User user = userService.findByUsername(userService.getLoggedInUser());
        Product product = this.modelMapper.map(inputProductDTO, Product.class);
        product.setUser(user);
//        long count = this.productRepository.findLatestId().orElse(0L);
//        product.setPosition(++count);
        this.productRepository.saveAndFlush(product);
        product.setPosition(product.getId());
        this.productRepository.saveAndFlush(product);

    }

    public String getAllProducts() {
        String currentUsername = this.userService.getLoggedInUser();
        Long currentUserId = this.userService.findByUsername(currentUsername).getId();

        return this.productRepository.findAllByUserId(currentUserId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> this.modelMapper.map(product, OutputProductDTO.class))
                .map(OutputProductDTO::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public List<OutputProductDTO> getListedProducts() {
        String currentUsername = this.userService.getLoggedInUser();
        Long currentUserId = this.userService.findByUsername(currentUsername).getId();

        List<OutputProductDTO> outputProductDTOS = this.productRepository.findAllByUserId(currentUserId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> this.modelMapper.map(product, OutputProductDTO.class))
                .toList();

        return outputProductDTOS.stream().sorted((a, b) -> b.getPosition().compareTo(a.getPosition())).toList();
    }

    public void updateProduct(Long id, String newName) {
        this.productRepository.updateProduct(id, newName);
    }

    @Modifying
    @Transactional
    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    public Product getProductById(Long id){
        return this.productRepository
                .getProductById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void moveUpProduct(long position) {
        this.productRepository.swapProductOrder(position, position +1);
    }

    public void moveDownProduct(long position) {
        this.productRepository.swapProductOrder(position, position - 1);
    }
}

