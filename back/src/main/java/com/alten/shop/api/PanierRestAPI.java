package com.alten.shop.api;


import com.alten.shop.services.panier.PanierService;
import com.alten.shop.services.user.AccountService;
import com.alten.shop.utils.dtos.panier.output.PanierResponseDTO;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.entities.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/panier")
public class PanierRestAPI {



    private final PanierService panierService;
    private final AccountService accountService;

    public PanierRestAPI(PanierService panierService, AccountService accountService) {
        this.panierService = panierService;
        this.accountService = accountService;
    }


    @Operation(summary = "Add product to panier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to panier"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/addProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<PanierResponseDTO> addProductToPanier(@Valid @RequestBody ProductPublicDTO product) {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        PanierResponseDTO panier = panierService.addProductToPanier(product, user);
        return ResponseEntity.status(HttpStatus.OK).body(panier);
    }



    @Operation(summary = "Remove product from panier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product removed from panier"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/removeProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<Void> removeProductFromPanier(@Valid @RequestBody ProductPublicDTO product) {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        panierService.removeProductFromPanier(product, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Clear panier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Panier cleared"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/clearPanier")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<Void> clearPanier() {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        panierService.clearPanier(user);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get panier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Panier retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/getPanier")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<PanierResponseDTO>> getPanier() {
        UserEntity user = accountService.getProfile(SecurityContextHolder.getContext())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<PanierResponseDTO> panier = panierService.getPanier(user);
        return ResponseEntity.status(HttpStatus.OK).body(panier);
    }

}
