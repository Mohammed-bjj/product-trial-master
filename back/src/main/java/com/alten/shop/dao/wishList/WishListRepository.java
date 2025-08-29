package com.alten.shop.dao.wishList;

import com.alten.shop.utils.entities.panier.Panier;
import com.alten.shop.utils.entities.user.UserEntity;
import com.alten.shop.utils.entities.wishList.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUser(UserEntity user);
}


