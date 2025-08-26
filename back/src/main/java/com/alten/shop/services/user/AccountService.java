package com.alten.shop.services.user;


import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import org.springframework.security.core.userdetails.User;


public interface AccountService {

    ProfileResponseDTO addNewUSer(RegisterRequestDTO user);
    User loadUserByEmail(String email) throws Exception;


}
