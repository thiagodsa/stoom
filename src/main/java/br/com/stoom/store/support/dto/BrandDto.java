package br.com.stoom.store.support.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrandDto {

    private Long id;
    private String name;
    private String description;
    private boolean active;

}
