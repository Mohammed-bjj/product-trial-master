package com.alten.shop.utils.entities.jointure;

import com.alten.shop.utils.entities.product.Product;
import com.alten.shop.utils.entities.wishList.WishList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wishlist_products")
public class WishListProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishList wishList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
