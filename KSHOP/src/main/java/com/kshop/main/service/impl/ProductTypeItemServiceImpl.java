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
import com.kshop.main.domain.ProductTypeItem;
import com.kshop.main.domain.Supplier;
import com.kshop.main.repository.ProductImagesReponsitory;
import com.kshop.main.repository.ProductTypeItemReponsitory;
import com.kshop.main.repository.ProductTypeReponsitory;
import com.kshop.main.repository.SupplierReponsitory;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.ProductTypeItemService;

@Service
public class ProductTypeItemServiceImpl implements ProductTypeItemService {
    
    @Autowired
    ProductTypeItemReponsitory productTypeItemReponsitory;

    @Override
    public <S extends ProductTypeItem> S save(S entity) {
        return productTypeItemReponsitory.save(entity);
    }

    @Override
    public <S extends ProductTypeItem> Optional<S> findOne(Example<S> example) {
        return productTypeItemReponsitory.findOne(example);
    }

    @Override
    public List<ProductTypeItem> findAll() {
        return productTypeItemReponsitory.findAll();
    }

    @Override
    public Page<ProductTypeItem> findAll(Pageable pageable) {
        return productTypeItemReponsitory.findAll(pageable);
    }

    @Override
    public List<ProductTypeItem> findAll(Sort sort) {
        return productTypeItemReponsitory.findAll(sort);
    }

    @Override
    public List<ProductTypeItem> findAllById(Iterable<Long> ids) {
        return productTypeItemReponsitory.findAllById(ids);
    }

    @Override
    public Optional<ProductTypeItem> findById(Long id) {
        return productTypeItemReponsitory.findById(id);
    }

    @Override
    public <S extends ProductTypeItem> List<S> saveAll(Iterable<S> entities) {
        return productTypeItemReponsitory.saveAll(entities);
    }

    @Override
    public void flush() {
        productTypeItemReponsitory.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return productTypeItemReponsitory.existsById(id);
    }

    @Override
    public <S extends ProductTypeItem> S saveAndFlush(S entity) {
        return productTypeItemReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends ProductTypeItem> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productTypeItemReponsitory.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ProductTypeItem> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productTypeItemReponsitory.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ProductTypeItem> entities) {
        productTypeItemReponsitory.deleteInBatch(entities);
    }

    @Override
    public <S extends ProductTypeItem> long count(Example<S> example) {
        return productTypeItemReponsitory.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductTypeItem> entities) {
        productTypeItemReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return productTypeItemReponsitory.count();
    }

    @Override
    public <S extends ProductTypeItem> boolean exists(Example<S> example) {
        return productTypeItemReponsitory.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        productTypeItemReponsitory.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        productTypeItemReponsitory.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ProductTypeItem entity) {
        productTypeItemReponsitory.delete(entity);
    }

    @Override
    public <S extends ProductTypeItem, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return productTypeItemReponsitory.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        productTypeItemReponsitory.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        productTypeItemReponsitory.deleteAllInBatch();
    }

    @Override
    public ProductTypeItem getOne(Long id) {
        return productTypeItemReponsitory.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ProductTypeItem> entities) {
        productTypeItemReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productTypeItemReponsitory.deleteAll();
    }

    @Override
    public ProductTypeItem getById(Long id) {
        return productTypeItemReponsitory.getById(id);
    }

    @Override
    public ProductTypeItem getReferenceById(Long id) {
        return productTypeItemReponsitory.getReferenceById(id);
    }

    @Override
    public <S extends ProductTypeItem> List<S> findAll(Example<S> example) {
        return productTypeItemReponsitory.findAll(example);
    }

    @Override
    public <S extends ProductTypeItem> List<S> findAll(Example<S> example, Sort sort) {
        return productTypeItemReponsitory.findAll(example, sort);
    }

    @Override
    public List<ProductTypeItem> selectsByProductId(Long id) {
        // TODO Auto-generated method stub
        return productTypeItemReponsitory.selectsByProductId(id);
    }
    
    
}
