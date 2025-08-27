package com.alten.shop.services.user;


import com.alten.shop.utils.dtos.user.input.LoginRequestDTO;
import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.LoginResponseDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import com.alten.shop.utils.exceptions.Uncheck.UserAlreadyExistsException;
import com.alten.shop.utils.exceptions.Uncheck.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<ProfileResponseDTO> addNewUSer(RegisterRequestDTO user) throws UserAlreadyExistsException;
    Optional<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO, Authentication authentication) ;
    Optional<List<ProfileResponseDTO>> loadAllUsers();
}
