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

import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.repository.UserHasRolesRepository;
import com.kshop.main.service.UserHasRolesService;

@Service
public class UserHasRolesServiceImpl implements UserHasRolesService{
	@Autowired
	UserHasRolesRepository userHasRolesRepository ;

	@Override
	public <S extends UserHasRoles> S save(S entity) {
		return userHasRolesRepository.save(entity);
	}

	@Override
	public <S extends UserHasRoles> Optional<S> findOne(Example<S> example) {
		return userHasRolesRepository.findOne(example);
	}

	@Override
	public List<UserHasRoles> findAll() {
		return userHasRolesRepository.findAll();
	}

	@Override
	public Page<UserHasRoles> findAll(Pageable pageable) {
		return userHasRolesRepository.findAll(pageable);
	}

	@Override
	public List<UserHasRoles> findAll(Sort sort) {
		return userHasRolesRepository.findAll(sort);
	}

	@Override
	public List<UserHasRoles> findAllById(Iterable<Long> ids) {
		return userHasRolesRepository.findAllById(ids);
	}

	@Override
	public Optional<UserHasRoles> findById(Long id) {
		return userHasRolesRepository.findById(id);
	}

	@Override
	public <S extends UserHasRoles> List<S> saveAll(Iterable<S> entities) {
		return userHasRolesRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		userHasRolesRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return userHasRolesRepository.existsById(id);
	}

	@Override
	public <S extends UserHasRoles> S saveAndFlush(S entity) {
		return userHasRolesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends UserHasRoles> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userHasRolesRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends UserHasRoles> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userHasRolesRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<UserHasRoles> entities) {
		userHasRolesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends UserHasRoles> long count(Example<S> example) {
		return userHasRolesRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<UserHasRoles> entities) {
		userHasRolesRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return userHasRolesRepository.count();
	}

	@Override
	public <S extends UserHasRoles> boolean exists(Example<S> example) {
		return userHasRolesRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		userHasRolesRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		userHasRolesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(UserHasRoles entity) {
		userHasRolesRepository.delete(entity);
	}

	@Override
	public <S extends UserHasRoles, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userHasRolesRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		userHasRolesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		userHasRolesRepository.deleteAllInBatch();
	}

	@Override
	public UserHasRoles getOne(Long id) {
		return userHasRolesRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends UserHasRoles> entities) {
		userHasRolesRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userHasRolesRepository.deleteAll();
	}

	@Override
	public UserHasRoles getById(Long id) {
		return userHasRolesRepository.getById(id);
	}

	@Override
	public UserHasRoles getReferenceById(Long id) {
		return userHasRolesRepository.getReferenceById(id);
	}

	@Override
	public <S extends UserHasRoles> List<S> findAll(Example<S> example) {
		return userHasRolesRepository.findAll(example);
	}

	@Override
	public <S extends UserHasRoles> List<S> findAll(Example<S> example, Sort sort) {
		return userHasRolesRepository.findAll(example, sort);
	}

    @Override
    public List<UserHasRoles> selectsByUserId(Long id) {
        // TODO Auto-generated method stub
        return userHasRolesRepository.selectsByUserId(id);
    }

}
