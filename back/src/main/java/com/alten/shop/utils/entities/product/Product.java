package com.alten.shop.utils.entities.product;

import com.alten.shop.utils.entities.order.Order;
import com.alten.shop.utils.entities.panier.Panier;
import com.alten.shop.utils.entities.wishList.WishList;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Code is required")
    @Column( nullable = false)
    private String code;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Image is required")
    @Column(nullable = false)
    private String image;

    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "shellId is required")
    @Column(nullable = false)
    private Long shellId;

    @NotBlank(message = "Internal reference is required")
    @Column(nullable = false)
    private String internalReference;
    
    @NotNull(message = "Inventory status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus inventoryStatus;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Panier> paniers = new ArrayList<>();

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<WishList> wishLists = new ArrayList<>();

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
