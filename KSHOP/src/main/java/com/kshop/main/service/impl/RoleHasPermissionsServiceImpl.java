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

import com.kshop.main.domain.RoleHasPermissions;
import com.kshop.main.repository.RoleHasPermissionsRepository;
import com.kshop.main.service.RoleHasPermissionsService;

@Service
public class RoleHasPermissionsServiceImpl implements RoleHasPermissionsService{
	@Autowired
	RoleHasPermissionsRepository roleHasPermissionsRepository ;

	@Override
	public <S extends RoleHasPermissions> S save(S entity) {
		return roleHasPermissionsRepository.save(entity);
	}

	@Override
	public <S extends RoleHasPermissions> Optional<S> findOne(Example<S> example) {
		return roleHasPermissionsRepository.findOne(example);
	}

	@Override
	public List<RoleHasPermissions> findAll() {
		return roleHasPermissionsRepository.findAll();
	}

	@Override
	public Page<RoleHasPermissions> findAll(Pageable pageable) {
		return roleHasPermissionsRepository.findAll(pageable);
	}

	@Override
	public List<RoleHasPermissions> findAll(Sort sort) {
		return roleHasPermissionsRepository.findAll(sort);
	}

	@Override
	public List<RoleHasPermissions> findAllById(Iterable<Long> ids) {
		return roleHasPermissionsRepository.findAllById(ids);
	}

	@Override
	public Optional<RoleHasPermissions> findById(Long id) {
		return roleHasPermissionsRepository.findById(id);
	}

	@Override
	public <S extends RoleHasPermissions> List<S> saveAll(Iterable<S> entities) {
		return roleHasPermissionsRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		roleHasPermissionsRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return roleHasPermissionsRepository.existsById(id);
	}

	@Override
	public <S extends RoleHasPermissions> S saveAndFlush(S entity) {
		return roleHasPermissionsRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends RoleHasPermissions> List<S> saveAllAndFlush(Iterable<S> entities) {
		return roleHasPermissionsRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends RoleHasPermissions> Page<S> findAll(Example<S> example, Pageable pageable) {
		return roleHasPermissionsRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<RoleHasPermissions> entities) {
		roleHasPermissionsRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends RoleHasPermissions> long count(Example<S> example) {
		return roleHasPermissionsRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<RoleHasPermissions> entities) {
		roleHasPermissionsRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return roleHasPermissionsRepository.count();
	}

	@Override
	public <S extends RoleHasPermissions> boolean exists(Example<S> example) {
		return roleHasPermissionsRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		roleHasPermissionsRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		roleHasPermissionsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(RoleHasPermissions entity) {
		roleHasPermissionsRepository.delete(entity);
	}

	@Override
	public <S extends RoleHasPermissions, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return roleHasPermissionsRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		roleHasPermissionsRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		roleHasPermissionsRepository.deleteAllInBatch();
	}

	@Override
	public RoleHasPermissions getOne(Long id) {
		return roleHasPermissionsRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends RoleHasPermissions> entities) {
		roleHasPermissionsRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		roleHasPermissionsRepository.deleteAll();
	}

	@Override
	public RoleHasPermissions getById(Long id) {
		return roleHasPermissionsRepository.getById(id);
	}

	@Override
	public RoleHasPermissions getReferenceById(Long id) {
		return roleHasPermissionsRepository.getReferenceById(id);
	}

	@Override
	public <S extends RoleHasPermissions> List<S> findAll(Example<S> example) {
		return roleHasPermissionsRepository.findAll(example);
	}

	@Override
	public <S extends RoleHasPermissions> List<S> findAll(Example<S> example, Sort sort) {
		return roleHasPermissionsRepository.findAll(example, sort);
	}

}
