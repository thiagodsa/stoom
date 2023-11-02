package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.support.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryBO categoryService;

    @GetMapping(value = "/")
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> p = categoryService.findAll();
        if (!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        CategoryDto p = categoryService.findById(id);
        if (Objects.nonNull(p))
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto category) {
        CategoryDto categorySave = categoryService.save(category);
        return new ResponseEntity<>(categorySave, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto category) {
        CategoryDto categorySave = categoryService.update(id, category);
        return new ResponseEntity<>(categorySave, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<CategoryDto> activate(@PathVariable Long id) {
        CategoryDto categoryUpdated = categoryService.activate(id);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }

}
