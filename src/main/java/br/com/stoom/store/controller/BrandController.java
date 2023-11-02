package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.support.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @GetMapping(value = "/")
    public ResponseEntity<List<BrandDto>> findAll() {
        List<BrandDto> p = brandService.findAll();
        if (!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandDto> findById(@PathVariable Long id) {
        BrandDto p = brandService.findById(id);
        if (Objects.nonNull(p))
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<BrandDto> save(@RequestBody BrandDto brand) {
        BrandDto brandSave = brandService.save(brand);
        return new ResponseEntity<>(brandSave, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandDto> update(@PathVariable Long id, @RequestBody BrandDto brand) {
        BrandDto brandSave = brandService.update(id, brand);
        return new ResponseEntity<>(brandSave, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<BrandDto> activate(@PathVariable Long id) {
        BrandDto brandUpdated = brandService.activate(id);
        return new ResponseEntity<>(brandUpdated, HttpStatus.OK);
    }

}
