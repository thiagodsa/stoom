package br.com.stoom.store.model;

public class CategoryFactory {
    public static Category shoes() {
        return Category.builder()
                .id(1L)
                .name("Shoes")
                .description("")
                .active(true)
                .build();
    }

    public static Category accessory() {
        return Category.builder()
                .id(2L)
                .name("Accessory")
                .description("")
                .active(false)
                .build();
    }

}