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

import com.kshop.main.domain.Supplier;
import com.kshop.main.repository.SupplierReponsitory;
import com.kshop.main.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	SupplierReponsitory supplierReponsitory;

	@Override
	public <S extends Supplier> S save(S entity) {
		return supplierReponsitory.save(entity);
	}

	@Override
	public <S extends Supplier> Optional<S> findOne(Example<S> example) {
		return supplierReponsitory.findOne(example);
	}

	@Override
	public List<Supplier> findAll() {
		return supplierReponsitory.findAll();
	}

	@Override
	public Page<Supplier> findAll(Pageable pageable) {
		return supplierReponsitory.findAll(pageable);
	}

	@Override
	public List<Supplier> findAll(Sort sort) {
		return supplierReponsitory.findAll(sort);
	}

	@Override
	public List<Supplier> findAllById(Iterable<Long> ids) {
		return supplierReponsitory.findAllById(ids);
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return supplierReponsitory.findById(id);
	}

	@Override
	public <S extends Supplier> List<S> saveAll(Iterable<S> entities) {
		return supplierReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		supplierReponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return supplierReponsitory.existsById(id);
	}

	@Override
	public <S extends Supplier> S saveAndFlush(S entity) {
		return supplierReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Supplier> List<S> saveAllAndFlush(Iterable<S> entities) {
		return supplierReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Supplier> Page<S> findAll(Example<S> example, Pageable pageable) {
		return supplierReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Supplier> entities) {
		supplierReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends Supplier> long count(Example<S> example) {
		return supplierReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Supplier> entities) {
		supplierReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return supplierReponsitory.count();
	}

	@Override
	public <S extends Supplier> boolean exists(Example<S> example) {
		return supplierReponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		supplierReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		supplierReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Supplier entity) {
		supplierReponsitory.delete(entity);
	}

	@Override
	public <S extends Supplier, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return supplierReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		supplierReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		supplierReponsitory.deleteAllInBatch();
	}

	@Override
	public Supplier getOne(Long id) {
		return supplierReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Supplier> entities) {
		supplierReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		supplierReponsitory.deleteAll();
	}

	@Override
	public Supplier getById(Long id) {
		return supplierReponsitory.getById(id);
	}

	@Override
	public Supplier getReferenceById(Long id) {
		return supplierReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends Supplier> List<S> findAll(Example<S> example) {
		return supplierReponsitory.findAll(example);
	}

	@Override
	public <S extends Supplier> List<S> findAll(Example<S> example, Sort sort) {
		return supplierReponsitory.findAll(example, sort);
	}
	
	
}
