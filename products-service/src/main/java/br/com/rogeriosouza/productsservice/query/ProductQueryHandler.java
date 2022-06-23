package br.com.rogeriosouza.productsservice.query;

import br.com.rogeriosouza.productsservice.core.data.ProductEntity;
import br.com.rogeriosouza.productsservice.core.data.ProductRepository;
import br.com.rogeriosouza.productsservice.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        List<ProductRestModel> productRestModels = new ArrayList<>();
        List<ProductEntity> products = productRepository.findAll();
        products.stream().forEach(productEntity -> {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRestModels.add(productRestModel);
        });

        return productRestModels;

    }
}
