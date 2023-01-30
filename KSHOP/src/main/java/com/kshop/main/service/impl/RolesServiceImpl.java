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

import com.kshop.main.domain.Roles;
import com.kshop.main.repository.RolesRepository;
import com.kshop.main.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	@Autowired
	RolesRepository rolesRepository;

	@Override
	public <S extends Roles> S save(S entity) {
		return rolesRepository.save(entity);
	}

	@Override
	public <S extends Roles> Optional<S> findOne(Example<S> example) {
		return rolesRepository.findOne(example);
	}

	@Override
	public List<Roles> findAll() {
		return rolesRepository.findAll();
	}

	@Override
	public Page<Roles> findAll(Pageable pageable) {
		return rolesRepository.findAll(pageable);
	}

	@Override
	public List<Roles> findAll(Sort sort) {
		return rolesRepository.findAll(sort);
	}

	@Override
	public List<Roles> findAllById(Iterable<Long> ids) {
		return rolesRepository.findAllById(ids);
	}

	@Override
	public Optional<Roles> findById(Long id) {
		return rolesRepository.findById(id);
	}

	@Override
	public <S extends Roles> List<S> saveAll(Iterable<S> entities) {
		return rolesRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		rolesRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return rolesRepository.existsById(id);
	}

	@Override
	public <S extends Roles> S saveAndFlush(S entity) {
		return rolesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Roles> List<S> saveAllAndFlush(Iterable<S> entities) {
		return rolesRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Roles> Page<S> findAll(Example<S> example, Pageable pageable) {
		return rolesRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Roles> entities) {
		rolesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Roles> long count(Example<S> example) {
		return rolesRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Roles> entities) {
		rolesRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return rolesRepository.count();
	}

	@Override
	public <S extends Roles> boolean exists(Example<S> example) {
		return rolesRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		rolesRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		rolesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Roles entity) {
		rolesRepository.delete(entity);
	}

	@Override
	public <S extends Roles, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return rolesRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		rolesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		rolesRepository.deleteAllInBatch();
	}

	@Override
	public Roles getOne(Long id) {
		return rolesRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Roles> entities) {
		rolesRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		rolesRepository.deleteAll();
	}

	@Override
	public Roles getById(Long id) {
		return rolesRepository.getById(id);
	}

	@Override
	public Roles getReferenceById(Long id) {
		return rolesRepository.getReferenceById(id);
	}

	@Override
	public <S extends Roles> List<S> findAll(Example<S> example) {
		return rolesRepository.findAll(example);
	}

	@Override
	public <S extends Roles> List<S> findAll(Example<S> example, Sort sort) {
		return rolesRepository.findAll(example, sort);
	}
	
}
