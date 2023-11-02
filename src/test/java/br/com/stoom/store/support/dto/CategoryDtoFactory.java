package br.com.stoom.store.support.dto;

public class CategoryDtoFactory {

    public static CategoryDto shoes() {
        return CategoryDto.builder()
                .id(1L)
                .name("Shoes")
                .description("")
                .active(true)
                .build();
    }

    public static CategoryDto accessory() {
        return CategoryDto.builder()
                .id(2L)
                .name("Accessory")
                .description("")
                .active(false)
                .build();
    }

}