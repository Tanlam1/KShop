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

import com.kshop.main.domain.Category;
import com.kshop.main.repository.ShopCategoriesRepository;
import com.kshop.main.service.ShopCategoriesService;

@Service
public class ShopCategoriesServiceImpl implements ShopCategoriesService {

	
	@Autowired
	ShopCategoriesRepository shopCategoriesRepository;

	@Override
	public <S extends Category> S save(S entity) {
		return shopCategoriesRepository.save(entity);
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return shopCategoriesRepository.findOne(example);
	}

	@Override
	public List<Category> findAll() {
		return shopCategoriesRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return shopCategoriesRepository.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return shopCategoriesRepository.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return shopCategoriesRepository.findAllById(ids);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return shopCategoriesRepository.findById(id);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return shopCategoriesRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		shopCategoriesRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return shopCategoriesRepository.existsById(id);
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return shopCategoriesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return shopCategoriesRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return shopCategoriesRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		shopCategoriesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Category> long count(Example<S> example) {
		return shopCategoriesRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		shopCategoriesRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return shopCategoriesRepository.count();
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return shopCategoriesRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		shopCategoriesRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		shopCategoriesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Category entity) {
		shopCategoriesRepository.delete(entity);
	}

	@Override
	public <S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return shopCategoriesRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		shopCategoriesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		shopCategoriesRepository.deleteAllInBatch();
	}

	@Override
	public Category getOne(Long id) {
		return shopCategoriesRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		shopCategoriesRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		shopCategoriesRepository.deleteAll();
	}

	@Override
	public Category getById(Long id) {
		return shopCategoriesRepository.getById(id);
	}

	@Override
	public Category getReferenceById(Long id) {
		return shopCategoriesRepository.getReferenceById(id);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		return shopCategoriesRepository.findAll(example);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return shopCategoriesRepository.findAll(example, sort);
	}

	@Override
	public List<Category> findByLevel(String id) {
		// TODO Auto-generated method stub
		return shopCategoriesRepository.findByLevel(id);
	}

	@Override
	public List<Category> findByParentId(String id) {
		// TODO Auto-generated method stub
		return shopCategoriesRepository.findByParentId(id);
	}	
	
}
