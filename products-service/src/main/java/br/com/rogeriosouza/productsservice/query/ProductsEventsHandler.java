package br.com.rogeriosouza.productsservice.query;

import br.com.rogeriosouza.productsservice.core.data.ProductEntity;
import br.com.rogeriosouza.productsservice.core.data.ProductRepository;
import br.com.rogeriosouza.productsservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductsEventsHandler {

    private final ProductRepository productRepository;

    public ProductsEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productRepository.save(productEntity);

    }

}
