package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.wishList.input.WishListRequestDTO;
import com.alten.shop.utils.dtos.wishList.output.WishListResponseDTO;
import com.alten.shop.utils.entities.jointure.WishListProduct;
import com.alten.shop.utils.entities.wishList.WishList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WishListMapper {


    public WishList toEntity(WishListRequestDTO wishListRequestDTO);

    @Mapping(source = "product.id", target = "product.id")
    @Mapping(source = "product.name", target = "product.name")
    @Mapping(source = "product.inventoryStatus", target = "product.inventoryStatus")
    @Mapping(source = "product.category", target = "product.category")
    public WishListResponseDTO toDTO(WishListProduct wishListProduct);


}
