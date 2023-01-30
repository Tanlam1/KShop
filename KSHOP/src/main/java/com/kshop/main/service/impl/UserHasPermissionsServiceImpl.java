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

import com.kshop.main.domain.UserHasPermissions;
import com.kshop.main.repository.UserHasPermissionsRepository;
import com.kshop.main.service.UserHasPermissionsService;

@Service
public class UserHasPermissionsServiceImpl implements UserHasPermissionsService{
	@Autowired
	UserHasPermissionsRepository userHasPermissionsRepository ;

	@Override
	public <S extends UserHasPermissions> S save(S entity) {
		return userHasPermissionsRepository.save(entity);
	}

	@Override
	public <S extends UserHasPermissions> Optional<S> findOne(Example<S> example) {
		return userHasPermissionsRepository.findOne(example);
	}

	@Override
	public List<UserHasPermissions> findAll() {
		return userHasPermissionsRepository.findAll();
	}

	@Override
	public Page<UserHasPermissions> findAll(Pageable pageable) {
		return userHasPermissionsRepository.findAll(pageable);
	}

	@Override
	public List<UserHasPermissions> findAll(Sort sort) {
		return userHasPermissionsRepository.findAll(sort);
	}

	@Override
	public List<UserHasPermissions> findAllById(Iterable<Long> ids) {
		return userHasPermissionsRepository.findAllById(ids);
	}

	@Override
	public Optional<UserHasPermissions> findById(Long id) {
		return userHasPermissionsRepository.findById(id);
	}

	@Override
	public <S extends UserHasPermissions> List<S> saveAll(Iterable<S> entities) {
		return userHasPermissionsRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		userHasPermissionsRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return userHasPermissionsRepository.existsById(id);
	}

	@Override
	public <S extends UserHasPermissions> S saveAndFlush(S entity) {
		return userHasPermissionsRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends UserHasPermissions> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userHasPermissionsRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends UserHasPermissions> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userHasPermissionsRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<UserHasPermissions> entities) {
		userHasPermissionsRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends UserHasPermissions> long count(Example<S> example) {
		return userHasPermissionsRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<UserHasPermissions> entities) {
		userHasPermissionsRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return userHasPermissionsRepository.count();
	}

	@Override
	public <S extends UserHasPermissions> boolean exists(Example<S> example) {
		return userHasPermissionsRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		userHasPermissionsRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		userHasPermissionsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(UserHasPermissions entity) {
		userHasPermissionsRepository.delete(entity);
	}

	@Override
	public <S extends UserHasPermissions, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userHasPermissionsRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		userHasPermissionsRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		userHasPermissionsRepository.deleteAllInBatch();
	}

	@Override
	public UserHasPermissions getOne(Long id) {
		return userHasPermissionsRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends UserHasPermissions> entities) {
		userHasPermissionsRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userHasPermissionsRepository.deleteAll();
	}

	@Override
	public UserHasPermissions getById(Long id) {
		return userHasPermissionsRepository.getById(id);
	}

	@Override
	public UserHasPermissions getReferenceById(Long id) {
		return userHasPermissionsRepository.getReferenceById(id);
	}

	@Override
	public <S extends UserHasPermissions> List<S> findAll(Example<S> example) {
		return userHasPermissionsRepository.findAll(example);
	}

	@Override
	public <S extends UserHasPermissions> List<S> findAll(Example<S> example, Sort sort) {
		return userHasPermissionsRepository.findAll(example, sort);
	}

}
