package com.alten.shop.api;


import com.alten.shop.services.user.AccountService;
import com.alten.shop.services.wishList.WishListService;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.dtos.wishList.output.WishListResponseDTO;
import com.alten.shop.utils.entities.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
public class WishListRestAPI {

    private final WishListService wishListService;
    private final AccountService accountService;
    public WishListRestAPI(WishListService wishListService, AccountService accountService){
        this.wishListService = wishListService;
        this.accountService = accountService;
    }


    @Operation(summary = "Add product to wish list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to wish list"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/addProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<WishListResponseDTO> addProductToWishList(@RequestBody ProductPublicDTO product) {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        WishListResponseDTO wishList = wishListService.addProductToWishList(product, user);
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }



    @Operation(summary = "Remove product from wish list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product removed from wish list"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/removeProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<Void> removeProductFromWishList(@RequestBody ProductPublicDTO product) {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        wishListService.removeProductFromWishList(product, user);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Clear wish list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wish list cleared"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/clearWishList")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<Void> clearWishList() {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        wishListService.clearWishList(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Get wish list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wish list retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/getWishList")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<WishListResponseDTO>> getWishList() {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<WishListResponseDTO> wishList = wishListService.getWishList(user);
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }








}
