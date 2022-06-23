package br.com.rogeriosouza.productsservice.command.rest;

import br.com.rogeriosouza.productsservice.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;


    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();

        try {
            return commandGateway.sendAndWait(createProductCommand);
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }

    }

//    @GetMapping
//    public String getProduct() {
//
//        return "GET" + env.getProperty("local.server.port");
//    }
//
//    @PutMapping
//    public String updateProduct(@RequestBody CreateProductRestModel createProductRestModel) {
//
//        return "PUT" + createProductRestModel.getTitle();
//    }
//
//    @DeleteMapping
//    public String deleteProduct() {
//        return "DELETE";
//    }
}
