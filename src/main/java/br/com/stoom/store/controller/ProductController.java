package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.support.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> p = productService.findAll();
        if (!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto p = productService.findById(id);
        if (Objects.nonNull(p))
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductDto>> findAllByBrand(@PathVariable Long brandId) {
        List<ProductDto> p = productService.findAllByBrand(brandId);
        if (!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> findAllByCategory(@PathVariable Long categoryId) {
        List<ProductDto> p = productService.findAllByCategory(categoryId);
        if (!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        ProductDto productSave = productService.save(product);
        return new ResponseEntity<>(productSave, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto product) {
        ProductDto productSave = productService.update(id, product);
        return new ResponseEntity<>(productSave, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<ProductDto> activate(@PathVariable Long id) {
        ProductDto productUpdated = productService.activate(id);
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

}
