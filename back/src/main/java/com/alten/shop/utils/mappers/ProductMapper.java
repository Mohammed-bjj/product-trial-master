package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    // Méthode de conversion Instant -> Long
    default Long map(java.time.Instant instant) {
        return instant != null ? instant.toEpochMilli() : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryStatus", expression = "java(com.alten.shop.utils.entities.product.InventoryStatus.valueOf(product.inventoryStatus()))")
    @Mapping(target = "category", expression = "java(com.alten.shop.utils.entities.product.CategoryType.valueOf(product.category()))")
    Product toEntity(ProductCreateRequestDTO product);


    Product toEntity(ProductUpdateRequestDTO dto, @MappingTarget Product product);


    @Mapping(target = "inventoryStatus", expression = "java(product.getInventoryStatus().name())")
    @Mapping(target = "category", expression = "java(product.getCategory().name())")
    ProductResponsePublicDTO toPublicDTO(Product product);

    @Mapping(target = "inventoryStatus", expression = "java(product.getInventoryStatus().name())")
    @Mapping(target = "category", expression = "java(product.getCategory().name())")
    ProductResponseAdminDTO toAdminDTO(Product product);



}
