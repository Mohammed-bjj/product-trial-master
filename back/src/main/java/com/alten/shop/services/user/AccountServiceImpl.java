package com.alten.shop.services.user;

import com.alten.shop.dao.user.UserRepository;
import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import com.alten.shop.utils.entities.User;
import com.alten.shop.utils.exceptions.Uncheck.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public ProfileResponseDTO addNewUSer(RegisterRequestDTO userDto) {
        System.out.println("hello service test");
        if(userRepository.existsByEmail(userDto.email())){
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());

        
        User savedUser = userRepository.save(user);
        
        return new ProfileResponseDTO(
            savedUser.getEmail(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            java.time.LocalDateTime.now()
        );
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByEmail(String email) throws Exception {
        return null;
    }


}
