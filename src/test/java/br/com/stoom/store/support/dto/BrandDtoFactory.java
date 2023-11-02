package br.com.stoom.store.support.dto;

public class BrandDtoFactory {

    public static BrandDto nike() {
        return BrandDto.builder()
                .id(1L)
                .name("Nike")
                .description("Just Do It")
                .active(true)
                .build();
    }

    public static BrandDto adidas() {
        return BrandDto.builder()
                .id(2L)
                .name("Adidas")
                .description("")
                .active(false)
                .build();
    }

}