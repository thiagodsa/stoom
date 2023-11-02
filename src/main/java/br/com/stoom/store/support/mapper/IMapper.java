package br.com.stoom.store.support.mapper;

public interface IMapper <E, D> {

    D toDto(E entity);

    E toEntity(D dto);

}
