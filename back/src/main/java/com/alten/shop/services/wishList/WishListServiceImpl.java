package com.alten.shop.services.wishList;

import com.alten.shop.dao.wishList.WishListRepository;
import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.dtos.wishList.output.WishListResponseDTO;
import com.alten.shop.utils.entities.jointure.PanierProduct;
import com.alten.shop.utils.entities.jointure.WishListProduct;
import com.alten.shop.utils.entities.panier.Panier;
import com.alten.shop.utils.entities.user.UserEntity;
import com.alten.shop.utils.entities.wishList.WishList;
import com.alten.shop.utils.mappers.WishListMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final ProductService productRepository;
    private final WishListMapper wishListMapper;


    public WishListServiceImpl(WishListRepository wishListRepository,
                               ProductService productRepository,
                               WishListMapper wishListMapper
    ) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.wishListMapper = wishListMapper;
    }


    @Override
    public WishListResponseDTO addProductToWishList(ProductPublicDTO productDTO, UserEntity user) {

        WishList wishList = wishListRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("WishList not found"));


        WishListProduct existing = wishList.getWishListProducts()
                .stream()
                .filter(pp -> pp.getProduct().getId().equals(productDTO.id()))
                .findFirst()
                .orElse(null);

        if(existing == null){
            WishListProduct wishListProduct = new WishListProduct();
            wishListProduct.setWishList(wishList);
            wishListProduct.setProduct(productRepository.findById(productDTO.id())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            wishList.getWishListProducts().add(wishListProduct);
            wishListRepository.save(wishList);
            return wishListMapper.toDTO(wishListProduct);
        }

        return null;
    }

    @Override
    public void removeProductFromWishList(ProductPublicDTO product, UserEntity user) {
        WishList wishList = wishListRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("WishList not found"));

        WishListProduct existing = wishList.getWishListProducts()
                .stream()
                .filter(pp -> pp.getProduct().getId().equals(product.id()))
                .findFirst()
                .orElse(null);

        if(existing != null){
            wishList.getWishListProducts().remove(existing);
            wishListRepository.save(wishList);
        }
    }

    @Override
    public void clearWishList(UserEntity user) {

        WishList wishList = wishListRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        wishList.getWishListProducts().clear();
        wishListRepository.save(wishList);
    }

    @Override
    public void createWishList(UserEntity user) {
        wishListRepository.findByUser(user).orElseGet(() -> {
            WishList wishList = new WishList();
            wishList.setUser(user);
            wishListRepository.save(wishList);
            return wishList;
        });

    }

    @Override
    public List<WishListResponseDTO> getWishList(UserEntity user) {
        WishList wishList = wishListRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("WishList not found"));

        return wishList.getWishListProducts().stream()
                .map(wishListMapper::toDTO)
                .toList();
    }




}
