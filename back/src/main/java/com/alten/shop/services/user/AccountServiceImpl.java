package com.alten.shop.services.user;

import com.alten.shop.dao.user.UserRepository;
import com.alten.shop.services.panier.PanierService;
import com.alten.shop.services.wishList.WishListService;
import com.alten.shop.utils.dtos.user.input.LoginRequestDTO;
import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.LoginResponseDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import com.alten.shop.utils.entities.user.UserEntity;
import com.alten.shop.utils.exceptions.Uncheck.user.UserAlreadyExistsException;
import com.alten.shop.utils.mappers.UserMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtEncoder jwtEncoder;
    private final PanierService panierService;
    private final WishListService wishListService;
    
    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                             UserMapper userMapper, JwtEncoder jwtEncoder, PanierService panierService, WishListService wishListService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtEncoder = jwtEncoder;
        this.panierService = panierService;
        this.wishListService = wishListService;
    }
    
    @Override
    public Optional<ProfileResponseDTO> addNewUSer(RegisterRequestDTO userDto) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(userDto.email())){
            throw new UserAlreadyExistsException("Email already exists");
        }
        UserEntity user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        UserEntity savedUser = userRepository.save(user);
        panierService.createPanier(savedUser);
        wishListService.createWishList(savedUser);
        return Optional.of(userMapper.toProfileDto(savedUser));
    }



    @Override
    public Optional<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO, Authentication authentication) {
        Instant instant = Instant.now();

        String scopeAuth = this.getRole(authentication)
                .orElseThrow(() -> new RuntimeException("role not found"));
        
        UserEntity user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                .subject(loginRequestDTO.email())
                .claim("scope", scopeAuth)
                .claim("authorities", scopeAuth)
                .claim("lastName", user.getLastName())
                .build();

        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Optional.of(userMapper.toLoginResponseDto(jwt));
    }

    @Override
    public Optional<List<ProfileResponseDTO>> loadAllUsers() {
        return Optional.of(userRepository.findAll()
                .stream()
                .map(userMapper::toProfileDto)
                .toList());
    }


    @Override
    public Optional<String> getRole(Authentication authentication) {
      return  Optional.of(authentication.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(" ")));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return  User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }


    @Override
    public Optional<UserEntity> getProfile(SecurityContext securityContext) {
        return userRepository.findByEmail(securityContext.getAuthentication().getName());
    }

}
