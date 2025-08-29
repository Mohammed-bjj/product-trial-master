package com.alten.shop.services.panier;

import com.alten.shop.utils.dtos.panier.output.PanierResponseDTO;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.entities.user.UserEntity;

import java.util.List;

public interface PanierService {
    PanierResponseDTO addProductToPanier(ProductPublicDTO product, UserEntity user);
    void removeProductFromPanier(ProductPublicDTO product, UserEntity user);
    void clearPanier(UserEntity user);
    public void createPanier(UserEntity user);
    public List<PanierResponseDTO> getPanier(UserEntity user);
}
