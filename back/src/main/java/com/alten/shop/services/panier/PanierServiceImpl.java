package com.alten.shop.services.panier;

import com.alten.shop.dao.panier.PanierRepository;
import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.panier.output.PanierResponseDTO;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.entities.jointure.PanierProduct;
import com.alten.shop.utils.entities.panier.Panier;
import com.alten.shop.utils.entities.product.Product;
import com.alten.shop.utils.entities.user.UserEntity;
import com.alten.shop.utils.mappers.PanierMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PanierServiceImpl implements  PanierService {

    private final PanierRepository panierRepository;
    private final PanierMapper panierMapper;
    private final ProductService productRepository;

    public PanierServiceImpl(
            PanierRepository panierRepository,
            PanierMapper panierMapper,
            ProductService productRepository
    ) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
        this.productRepository = productRepository;
    }


    @Override
    public PanierResponseDTO addProductToPanier(ProductPublicDTO productDTO, UserEntity user) {

        Panier panier = panierRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Panier not found"));

        PanierProduct existing = panier.getPanierProducts()
                .stream()
                .filter(pp -> pp.getProduct().getId().equals(productDTO.id()))
                .findFirst()
                .orElse(null);
        PanierProduct panierProduct = new PanierProduct() ;

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            Product product = productRepository.findById(productDTO.id())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            panierProduct.setProduct(product);
            panierProduct.setPanier(panier);
            panierProduct.setQuantity(1);
            panier.getPanierProducts().add(panierProduct);
        }
        panierRepository.save(panier);
        return panierMapper.toDTO(panierProduct);
    }

    @Override
    public void removeProductFromPanier(ProductPublicDTO product, UserEntity user) {

            
            Panier panier = panierRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Panier not found"));

            PanierProduct toRemove = panier.getPanierProducts()
                    .stream()
                    .filter(pp -> pp.getProduct().getId().equals(product.id()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found in panier"));

            panier.getPanierProducts().remove(toRemove);
            panierRepository.save(panier);
        }

    @Override
    public void clearPanier(UserEntity user) {
        Panier panier = panierRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Panier not found"));
        panier.getPanierProducts().clear();
        panierRepository.save(panier);
    }

    @Override
    public void createPanier(UserEntity user) {
        panierRepository.findByUser(user).orElseGet(() -> {
            Panier panier = new Panier();
            panier.setUser(user);
            panierRepository.save(panier);
            return panier;
        });
    }

    @Override
    public List<PanierResponseDTO> getPanier(UserEntity user) {
        Panier panier = panierRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Panier not found"));
        return panier.getPanierProducts().stream()
                .map(panierMapper::toDTO)
                .toList();
    }
}



