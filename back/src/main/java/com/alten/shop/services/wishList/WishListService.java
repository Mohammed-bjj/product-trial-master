package com.alten.shop.services.wishList;

import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.dtos.wishList.output.WishListResponseDTO;
import com.alten.shop.utils.entities.user.UserEntity;

import java.util.List;

public interface WishListService {

    WishListResponseDTO addProductToWishList(ProductPublicDTO product, UserEntity user);
    void removeProductFromWishList(ProductPublicDTO product, UserEntity user);
    void clearWishList(UserEntity user);
    void createWishList(UserEntity user);
    List<WishListResponseDTO> getWishList(UserEntity user);
}
