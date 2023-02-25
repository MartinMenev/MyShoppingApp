package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.products.InputProductDTO;
import com.example.myshoppingapp.domain.products.OutputProductDTO;
import com.example.myshoppingapp.domain.products.Product;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import com.example.myshoppingapp.repository.ProductRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Getter
@Service
public class ProductService {
    private final UserService userService;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public ProductService(UserService userService, ProductRepository productRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userService = userService;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Modifying
    public void addProduct(InputProductDTO inputProductDTO) {
<<<<<<< HEAD
        UserEntity userEntity = userService.findByUsername(this.loggedUser.getUsername());
        Product product = this.modelMapper.map(inputProductDTO, Product.class);
        product.setUserEntity(userEntity);
=======
        User user = userService.findByUsername(this.loggedUser.getUsername());
        Product product = this.modelMapper.map(inputProductDTO, Product.class);
        product.setUser(user);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        product.setBoughtOn(null);
        this.productRepository.saveAndFlush(product);
        product.setPosition(product.getId());
        this.productRepository.saveAndFlush(product);

    }

    public String getAllProducts() {
        String currentUsername = this.loggedUser.getUsername();
        Long currentUserId = this.userService.findByUsername(currentUsername).getId();

<<<<<<< HEAD
        return this.productRepository.findAllByUserEntityId(currentUserId)
=======
        return this.productRepository.findAllByUserId(currentUserId)
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> this.modelMapper.map(product, OutputProductDTO.class))
                .map(OutputProductDTO::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public List<OutputProductDTO> getListedProducts() {
        String currentUsername = this.loggedUser.getUsername();
        Long currentUserId = this.userService.findByUsername(currentUsername).getId();

<<<<<<< HEAD
        List<OutputProductDTO> outputProductDTOS = this.productRepository.findAllByUserEntityId(currentUserId)
=======
        List<OutputProductDTO> outputProductDTOS = this.productRepository.findAllByUserId(currentUserId)
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .filter(product -> product.getBoughtOn() ==null)
                .map(product -> this.modelMapper.map(product, OutputProductDTO.class))
                .toList();

        return outputProductDTOS.stream().sorted((a, b) -> b.getPosition().compareTo(a.getPosition())).toList();
    }

    public void updateProduct(InputProductDTO inputProductDTO) {
        Long idToUpdate = inputProductDTO.getId();
        String newName = inputProductDTO.getName();
        this.productRepository.updateProductName(idToUpdate, newName);
    }

    @Modifying
    @Transactional
    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    public OutputProductDTO getProductById(Long id) {
        Product product = this.productRepository
                .getProductById(id)
                .orElseThrow(NoSuchElementException::new);
        return modelMapper.map(product, OutputProductDTO.class);
    }

    public void moveUpProduct(long position) {
        long userId = this.userService.getLoggedUserId();
<<<<<<< HEAD
        if (this.productRepository.findFirstByPositionGreaterThanAndUserEntityIdOrderByPositionAsc(position, userId) !=null) {
            long newPosition = this.productRepository.
                    findFirstByPositionGreaterThanAndUserEntityIdOrderByPositionAsc(position, userId).getPosition();
=======
        if (this.productRepository.findFirstByPositionGreaterThanAndUserIdOrderByPositionAsc(position, userId) !=null) {
            long newPosition = this.productRepository.
                    findFirstByPositionGreaterThanAndUserIdOrderByPositionAsc(position, userId).getPosition();
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
            this.productRepository.swapProductOrder(position, newPosition);
        }

    }

    public void moveDownProduct(long position) {
        long userId = this.userService.getLoggedUserId();
<<<<<<< HEAD
        if (this.productRepository.findFirstByPositionLessThanAndUserEntityIdOrderByPositionDesc(position, userId) != null) {
            long newPosition = this.productRepository.
                    findFirstByPositionLessThanAndUserEntityIdOrderByPositionDesc(position, userId).getPosition();
=======
        if (this.productRepository.findFirstByPositionLessThanAndUserIdOrderByPositionDesc(position, userId) != null) {
            long newPosition = this.productRepository.
                    findFirstByPositionLessThanAndUserIdOrderByPositionDesc(position, userId).getPosition();
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
            this.productRepository.swapProductOrder(position, newPosition);
        }
    }

    @Transactional
    @Modifying
    public void buyProduct(long id) {
        Product product = this.productRepository.getProductById(id).orElseThrow(NoSuchElementException::new);
        product.setBoughtOn(LocalDate.now());
<<<<<<< HEAD
        UserEntity userEntity = this.userService.getLoggedUser();
        product.setBuyer(userEntity);
=======
        User user = this.userService.getLoggedUser();
        product.setBuyer(user);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        this.productRepository.save(product);
    }

    public List<OutputProductDTO> showBoughtProducts() {
        Long userId = this.userService.getLoggedUserId();
        return this.productRepository.findAllByBuyerId(userId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(p -> modelMapper.map(p, OutputProductDTO.class))
                .toList();
    }

    @Transactional
    @Modifying
    public void reuseProduct(long id) {
        Product product = this.productRepository
                .getProductById(id)
                .orElseThrow(NoSuchElementException::new);
         product.setBoughtOn(null)
                .setBuyer(null);
        this.productRepository.saveAndFlush(product);
    }

    public void saveProduct(Product product) {
        this.productRepository.saveAndFlush(product);
    }

    @Transactional
    @Modifying
    public void addProductToMyList(String name) {
       Product product = new Product(name);
<<<<<<< HEAD
        UserEntity userEntity = userService.findByUsername(this.loggedUser.getUsername());
        product.setUserEntity(userEntity);
=======
        User user = userService.findByUsername(this.loggedUser.getUsername());
        product.setUser(user);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        product.setBoughtOn(null);
        this.productRepository.saveAndFlush(product);
        product.setPosition(product.getId());
        this.productRepository.saveAndFlush(product);
    }

    public Product getProductByName(String productName) {
        return this.productRepository.findByName(productName);
    }

    public Product findProductById(Long productId) {
        return this.productRepository.getProductById(productId).orElseThrow(NoSuchElementException::new);
    }
}

