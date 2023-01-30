package com.kshop.main.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.kshop.main.domain.Permissions;
import com.kshop.main.repository.PermissionsRepository;
import com.kshop.main.service.PermissionsService;

@Service
public class PermissionsServiceImpl implements PermissionsService{
	@Autowired
	PermissionsRepository permissionsRepository;

	@Override
	public <S extends Permissions> S save(S entity) {
		return permissionsRepository.save(entity);
	}

	@Override
	public <S extends Permissions> Optional<S> findOne(Example<S> example) {
		return permissionsRepository.findOne(example);
	}

	@Override
	public List<Permissions> findAll() {
		return permissionsRepository.findAll();
	}

	@Override
	public Page<Permissions> findAll(Pageable pageable) {
		return permissionsRepository.findAll(pageable);
	}

	@Override
	public List<Permissions> findAll(Sort sort) {
		return permissionsRepository.findAll(sort);
	}

	@Override
	public List<Permissions> findAllById(Iterable<Long> ids) {
		return permissionsRepository.findAllById(ids);
	}

	@Override
	public Optional<Permissions> findById(Long id) {
		return permissionsRepository.findById(id);
	}

	@Override
	public <S extends Permissions> List<S> saveAll(Iterable<S> entities) {
		return permissionsRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		permissionsRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return permissionsRepository.existsById(id);
	}

	@Override
	public <S extends Permissions> S saveAndFlush(S entity) {
		return permissionsRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Permissions> List<S> saveAllAndFlush(Iterable<S> entities) {
		return permissionsRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Permissions> Page<S> findAll(Example<S> example, Pageable pageable) {
		return permissionsRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Permissions> entities) {
		permissionsRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Permissions> long count(Example<S> example) {
		return permissionsRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Permissions> entities) {
		permissionsRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return permissionsRepository.count();
	}

	@Override
	public <S extends Permissions> boolean exists(Example<S> example) {
		return permissionsRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		permissionsRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		permissionsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Permissions entity) {
		permissionsRepository.delete(entity);
	}

	@Override
	public <S extends Permissions, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return permissionsRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		permissionsRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		permissionsRepository.deleteAllInBatch();
	}

	@Override
	public Permissions getOne(Long id) {
		return permissionsRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Permissions> entities) {
		permissionsRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		permissionsRepository.deleteAll();
	}

	@Override
	public Permissions getById(Long id) {
		return permissionsRepository.getById(id);
	}

	@Override
	public Permissions getReferenceById(Long id) {
		return permissionsRepository.getReferenceById(id);
	}

	@Override
	public <S extends Permissions> List<S> findAll(Example<S> example) {
		return permissionsRepository.findAll(example);
	}

	@Override
	public <S extends Permissions> List<S> findAll(Example<S> example, Sort sort) {
		return permissionsRepository.findAll(example, sort);
	}

}
