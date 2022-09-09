package br.com.rogeriosouza.productsservice.query;

import br.com.rogeriosouza.productsservice.core.data.ProductEntity;
import br.com.rogeriosouza.productsservice.core.data.ProductRepository;
import br.com.rogeriosouza.productsservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductsEventsHandler {

    private final ProductRepository productRepository;

    public ProductsEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handler(IllegalArgumentException exception) {

    }

    @ExceptionHandler(resultType = Exception.class)
    public void handler(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        try {
            productRepository.save(productEntity);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }

        if (true) throw new Exception("An error took place in the CreaterProductCommand @CommandHandler");

    }

}
