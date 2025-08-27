package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Méthode de conversion Instant -> Long
    default Long map(java.time.Instant instant) {
        return instant != null ? instant.toEpochMilli() : null;
    }


    @Mapping(target = "inventoryStatus", expression = "java(product.getInventoryStatus().name())")
    ProductResponsePublicDTO toPublicDTO(Product product);

    @Mapping(target = "inventoryStatus", expression = "java(product.getInventoryStatus().name())")
    ProductResponseAdminDTO toAdminDTO(Product product);



}


/**
 *    @Mapping(target = "inventoryStatus", expression = "java(InventoryStatus.valueOf(dto.inventoryStatus()))")
 *     @Mapping(target = "id", ignore = true)
 *     void updateEntity(@MappingTarget Product product, ProductRequestDTO dto);
 */
