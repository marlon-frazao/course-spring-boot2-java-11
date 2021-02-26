package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import com.educandoweb.course.util.Convertible;

public interface GenericService<T extends Convertible<DTO>, DTO, ID> {

	JpaRepository<T, ID> getRepository();

	default DTO findById(ID id) {
		Optional<T> obj = getRepository().findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)).convert();
	}

	default List<DTO> findAll() {
		List<T> list = getRepository().findAll();
		return list.stream().map(x -> x.convert()).collect(Collectors.toList());
	}

	default DTO insert(T obj) {
		return getRepository().save(obj).convert();
	}

	default void delete(ID id) {
		try {
			getRepository().deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	default DTO update(ID id, T obj) {
		T entity = getRepository().getOne(id);
		updateData(entity, obj);
		return getRepository().save(entity).convert();
	}

	void updateData(T entity, T obj);
}
