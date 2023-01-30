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

import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.Supplier;
import com.kshop.main.repository.ProductImagesReponsitory;
import com.kshop.main.repository.SupplierReponsitory;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.SupplierService;

@Service
public class ProductImagesServiceImpl implements ProductImagesService{
	
	@Autowired
	ProductImagesReponsitory productImagesReponsitory;

	@Override
	public <S extends ProductImages> S save(S entity) {
		return productImagesReponsitory.save(entity);
	}

	@Override
	public <S extends ProductImages> Optional<S> findOne(Example<S> example) {
		return productImagesReponsitory.findOne(example);
	}

	@Override
	public List<ProductImages> findAll() {
		return productImagesReponsitory.findAll();
	}

	@Override
	public Page<ProductImages> findAll(Pageable pageable) {
		return productImagesReponsitory.findAll(pageable);
	}

	@Override
	public List<ProductImages> findAll(Sort sort) {
		return productImagesReponsitory.findAll(sort);
	}

	@Override
	public List<ProductImages> findAllById(Iterable<Long> ids) {
		return productImagesReponsitory.findAllById(ids);
	}

	@Override
	public Optional<ProductImages> findById(Long id) {
		return productImagesReponsitory.findById(id);
	}

	@Override
	public <S extends ProductImages> List<S> saveAll(Iterable<S> entities) {
		return productImagesReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		productImagesReponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return productImagesReponsitory.existsById(id);
	}

	@Override
	public <S extends ProductImages> S saveAndFlush(S entity) {
		return productImagesReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductImages> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productImagesReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ProductImages> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productImagesReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ProductImages> entities) {
		productImagesReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductImages> long count(Example<S> example) {
		return productImagesReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductImages> entities) {
		productImagesReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productImagesReponsitory.count();
	}

	@Override
	public <S extends ProductImages> boolean exists(Example<S> example) {
		return productImagesReponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		productImagesReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productImagesReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ProductImages entity) {
		productImagesReponsitory.delete(entity);
	}

	@Override
	public <S extends ProductImages, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productImagesReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productImagesReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productImagesReponsitory.deleteAllInBatch();
	}

	@Override
	public ProductImages getOne(Long id) {
		return productImagesReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductImages> entities) {
		productImagesReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productImagesReponsitory.deleteAll();
	}

	@Override
	public ProductImages getById(Long id) {
		return productImagesReponsitory.getById(id);
	}

	@Override
	public ProductImages getReferenceById(Long id) {
		return productImagesReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends ProductImages> List<S> findAll(Example<S> example) {
		return productImagesReponsitory.findAll(example);
	}

	@Override
	public <S extends ProductImages> List<S> findAll(Example<S> example, Sort sort) {
		return productImagesReponsitory.findAll(example, sort);
	}

    @Override
    public List<ProductImages> selectsByProductId(Long id) {
        // TODO Auto-generated method stub
        return productImagesReponsitory.selectsByProductId(id);
    }
	
	
	
}
