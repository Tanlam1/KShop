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

import com.kshop.main.domain.ShopStores;
import com.kshop.main.repository.ShopStoresRepository;
import com.kshop.main.service.ShopStoresService;

@Service
public class ShopStoresServiceImpl implements ShopStoresService {

	@Autowired
	ShopStoresRepository shopStoresRepository;

	@Override
	public <S extends ShopStores> S save(S entity) {
		return shopStoresRepository.save(entity);
	}

	@Override
	public <S extends ShopStores> Optional<S> findOne(Example<S> example) {
		return shopStoresRepository.findOne(example);
	}

	@Override
	public List<ShopStores> findAll() {
		return shopStoresRepository.findAll();
	}

	@Override
	public Page<ShopStores> findAll(Pageable pageable) {
		return shopStoresRepository.findAll(pageable);
	}

	@Override
	public List<ShopStores> findAll(Sort sort) {
		return shopStoresRepository.findAll(sort);
	}

	@Override
	public List<ShopStores> findAllById(Iterable<Long> ids) {
		return shopStoresRepository.findAllById(ids);
	}

	@Override
	public Optional<ShopStores> findById(Long id) {
		return shopStoresRepository.findById(id);
	}

	@Override
	public <S extends ShopStores> List<S> saveAll(Iterable<S> entities) {
		return shopStoresRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		shopStoresRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return shopStoresRepository.existsById(id);
	}

	@Override
	public <S extends ShopStores> S saveAndFlush(S entity) {
		return shopStoresRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ShopStores> List<S> saveAllAndFlush(Iterable<S> entities) {
		return shopStoresRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ShopStores> Page<S> findAll(Example<S> example, Pageable pageable) {
		return shopStoresRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ShopStores> entities) {
		shopStoresRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ShopStores> long count(Example<S> example) {
		return shopStoresRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ShopStores> entities) {
		shopStoresRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return shopStoresRepository.count();
	}

	@Override
	public <S extends ShopStores> boolean exists(Example<S> example) {
		return shopStoresRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		shopStoresRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		shopStoresRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ShopStores entity) {
		shopStoresRepository.delete(entity);
	}

	@Override
	public <S extends ShopStores, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return shopStoresRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		shopStoresRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		shopStoresRepository.deleteAllInBatch();
	}

	@Override
	public ShopStores getOne(Long id) {
		return shopStoresRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ShopStores> entities) {
		shopStoresRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		shopStoresRepository.deleteAll();
	}

	@Override
	public ShopStores getById(Long id) {
		return shopStoresRepository.getById(id);
	}

	@Override
	public ShopStores getReferenceById(Long id) {
		return shopStoresRepository.getReferenceById(id);
	}

	@Override
	public <S extends ShopStores> List<S> findAll(Example<S> example) {
		return shopStoresRepository.findAll(example);
	}

	@Override
	public <S extends ShopStores> List<S> findAll(Example<S> example, Sort sort) {
		return shopStoresRepository.findAll(example, sort);
	}

//	@Override
//	public ShopStores checkHasVender(Long idVender, String venderCode) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
}
