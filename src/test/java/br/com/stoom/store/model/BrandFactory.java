package br.com.stoom.store.model;

public class BrandFactory {
    public static Brand nike() {
        return Brand.builder()
                .id(1L)
                .name("Nike")
                .description("Just Do It")
                .active(true)
                .build();
    }

    public static Brand adidas() {
        return Brand.builder()
                .id(2L)
                .name("Adidas")
                .description("")
                .active(false)
                .build();
    }

}