package com.daki.domain.service;

import com.daki.domain.model.AbstractEntity;
import com.daki.domain.model.dto.system.AbstractDto;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface IAbstractService<E extends AbstractEntity, I, DTO extends AbstractDto> {

    JpaRepository<E, I> getRepository();

    DTO findById(I id);

    List<DTO> findAll();

    Page<DTO> findAll(Pageable pageable);

    Page<DTO> findAll(Pageable pageable, Map filters, ExampleMatcher.StringMatcher matchFilter);

    Page<DTO> findAll(Pageable pageable, Map filters);

    E findAndValidate(I id);

}
