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
import com.kshop.main.domain.ProductType;
import com.kshop.main.domain.Supplier;
import com.kshop.main.repository.ProductImagesReponsitory;
import com.kshop.main.repository.ProductTypeReponsitory;
import com.kshop.main.repository.SupplierReponsitory;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.ProductTypeService;
import com.kshop.main.service.SupplierService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    
    @Autowired
    ProductTypeReponsitory productTypeReponsitory;

    @Override
    public <S extends ProductType> S save(S entity) {
        return productTypeReponsitory.save(entity);
    }

    @Override
    public <S extends ProductType> Optional<S> findOne(Example<S> example) {
        return productTypeReponsitory.findOne(example);
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeReponsitory.findAll();
    }

    @Override
    public Page<ProductType> findAll(Pageable pageable) {
        return productTypeReponsitory.findAll(pageable);
    }

    @Override
    public List<ProductType> findAll(Sort sort) {
        return productTypeReponsitory.findAll(sort);
    }

    @Override
    public List<ProductType> findAllById(Iterable<Long> ids) {
        return productTypeReponsitory.findAllById(ids);
    }

    @Override
    public Optional<ProductType> findById(Long id) {
        return productTypeReponsitory.findById(id);
    }

    @Override
    public <S extends ProductType> List<S> saveAll(Iterable<S> entities) {
        return productTypeReponsitory.saveAll(entities);
    }

    @Override
    public void flush() {
        productTypeReponsitory.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return productTypeReponsitory.existsById(id);
    }

    @Override
    public <S extends ProductType> S saveAndFlush(S entity) {
        return productTypeReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends ProductType> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productTypeReponsitory.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ProductType> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productTypeReponsitory.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ProductType> entities) {
        productTypeReponsitory.deleteInBatch(entities);
    }

    @Override
    public <S extends ProductType> long count(Example<S> example) {
        return productTypeReponsitory.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductType> entities) {
        productTypeReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return productTypeReponsitory.count();
    }

    @Override
    public <S extends ProductType> boolean exists(Example<S> example) {
        return productTypeReponsitory.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        productTypeReponsitory.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        productTypeReponsitory.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ProductType entity) {
        productTypeReponsitory.delete(entity);
    }

    @Override
    public <S extends ProductType, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return productTypeReponsitory.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        productTypeReponsitory.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        productTypeReponsitory.deleteAllInBatch();
    }

    @Override
    public ProductType getOne(Long id) {
        return productTypeReponsitory.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ProductType> entities) {
        productTypeReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productTypeReponsitory.deleteAll();
    }

    @Override
    public ProductType getById(Long id) {
        return productTypeReponsitory.getById(id);
    }

    @Override
    public ProductType getReferenceById(Long id) {
        return productTypeReponsitory.getReferenceById(id);
    }

    @Override
    public <S extends ProductType> List<S> findAll(Example<S> example) {
        return productTypeReponsitory.findAll(example);
    }

    @Override
    public <S extends ProductType> List<S> findAll(Example<S> example, Sort sort) {
        return productTypeReponsitory.findAll(example, sort);
    }

    
    
}
