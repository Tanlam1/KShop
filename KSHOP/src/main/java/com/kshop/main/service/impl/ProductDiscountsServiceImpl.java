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

import com.kshop.main.domain.ProductDiscounts;
import com.kshop.main.repository.ProductDiscountsReponsitory;
import com.kshop.main.service.ProductDiscountsService;

@Service
public class ProductDiscountsServiceImpl implements ProductDiscountsService{

	@Autowired
	ProductDiscountsReponsitory productDiscountsReponsitory;

	@Override
	public <S extends ProductDiscounts> S save(S entity) {
		return productDiscountsReponsitory.save(entity);
	}

	@Override
	public <S extends ProductDiscounts> Optional<S> findOne(Example<S> example) {
		return productDiscountsReponsitory.findOne(example);
	}

	@Override
	public List<ProductDiscounts> findAll() {
		return productDiscountsReponsitory.findAll();
	}

	@Override
	public Page<ProductDiscounts> findAll(Pageable pageable) {
		return productDiscountsReponsitory.findAll(pageable);
	}

	@Override
	public List<ProductDiscounts> findAll(Sort sort) {
		return productDiscountsReponsitory.findAll(sort);
	}

	@Override
	public List<ProductDiscounts> findAllById(Iterable<Long> ids) {
		return productDiscountsReponsitory.findAllById(ids);
	}

	@Override
	public Optional<ProductDiscounts> findById(Long id) {
		return productDiscountsReponsitory.findById(id);
	}

	@Override
	public <S extends ProductDiscounts> List<S> saveAll(Iterable<S> entities) {
		return productDiscountsReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		productDiscountsReponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return productDiscountsReponsitory.existsById(id);
	}

	@Override
	public <S extends ProductDiscounts> S saveAndFlush(S entity) {
		return productDiscountsReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductDiscounts> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDiscountsReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ProductDiscounts> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDiscountsReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ProductDiscounts> entities) {
		productDiscountsReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductDiscounts> long count(Example<S> example) {
		return productDiscountsReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductDiscounts> entities) {
		productDiscountsReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productDiscountsReponsitory.count();
	}

	@Override
	public <S extends ProductDiscounts> boolean exists(Example<S> example) {
		return productDiscountsReponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		productDiscountsReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productDiscountsReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ProductDiscounts entity) {
		productDiscountsReponsitory.delete(entity);
	}

	@Override
	public <S extends ProductDiscounts, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productDiscountsReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productDiscountsReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productDiscountsReponsitory.deleteAllInBatch();
	}

	@Override
	public ProductDiscounts getOne(Long id) {
		return productDiscountsReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductDiscounts> entities) {
		productDiscountsReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productDiscountsReponsitory.deleteAll();
	}

	@Override
	public ProductDiscounts getById(Long id) {
		return productDiscountsReponsitory.getById(id);
	}

	@Override
	public ProductDiscounts getReferenceById(Long id) {
		return productDiscountsReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends ProductDiscounts> List<S> findAll(Example<S> example) {
		return productDiscountsReponsitory.findAll(example);
	}

	@Override
	public <S extends ProductDiscounts> List<S> findAll(Example<S> example, Sort sort) {
		return productDiscountsReponsitory.findAll(example, sort);
	}
	
}
