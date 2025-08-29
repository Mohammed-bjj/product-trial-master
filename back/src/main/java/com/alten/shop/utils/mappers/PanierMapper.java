package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.panier.input.PanierRequestDTO;
import com.alten.shop.utils.dtos.panier.output.PanierResponseDTO;
import com.alten.shop.utils.entities.jointure.PanierProduct;
import com.alten.shop.utils.entities.panier.Panier;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PanierMapper {

    public Panier toEntity(PanierRequestDTO panierRequestDTO);

     @Mapping(source = "product.id", target = "product.id")
     @Mapping(source = "product.name", target = "product.name")
     @Mapping(source = "product.inventoryStatus", target = "product.inventoryStatus")
     @Mapping(source = "product.category", target = "product.category")
     public PanierResponseDTO toDTO(PanierProduct panierProduct);


}