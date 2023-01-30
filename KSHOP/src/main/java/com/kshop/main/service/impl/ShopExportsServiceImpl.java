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

import com.kshop.main.domain.ShopExports;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.repository.ShopExportsRepository;
import com.kshop.main.repository.ShopImportsRepository;
import com.kshop.main.repository.ShopStoresRepository;
import com.kshop.main.service.ShopExportsService;
import com.kshop.main.service.ShopImportsService;
import com.kshop.main.service.ShopStoresService;

@Service
public class ShopExportsServiceImpl implements ShopExportsService {

	@Autowired
	ShopExportsRepository shopExportsRepository;

    @Override
    public <S extends ShopExports> S save(S entity) {
        return shopExportsRepository.save(entity);
    }

    @Override
    public <S extends ShopExports> Optional<S> findOne(Example<S> example) {
        return shopExportsRepository.findOne(example);
    }

    @Override
    public List<ShopExports> findAll() {
        return shopExportsRepository.findAll();
    }

    @Override
    public Page<ShopExports> findAll(Pageable pageable) {
        return shopExportsRepository.findAll(pageable);
    }

    @Override
    public List<ShopExports> findAll(Sort sort) {
        return shopExportsRepository.findAll(sort);
    }

    @Override
    public List<ShopExports> findAllById(Iterable<Long> ids) {
        return shopExportsRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopExports> findById(Long id) {
        return shopExportsRepository.findById(id);
    }

    @Override
    public <S extends ShopExports> List<S> saveAll(Iterable<S> entities) {
        return shopExportsRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopExportsRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopExportsRepository.existsById(id);
    }

    @Override
    public <S extends ShopExports> S saveAndFlush(S entity) {
        return shopExportsRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopExports> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopExportsRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopExports> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopExportsRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopExports> entities) {
        shopExportsRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopExports> long count(Example<S> example) {
        return shopExportsRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopExports> entities) {
        shopExportsRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopExportsRepository.count();
    }

    @Override
    public <S extends ShopExports> boolean exists(Example<S> example) {
        return shopExportsRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopExportsRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopExportsRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopExports entity) {
        shopExportsRepository.delete(entity);
    }

    @Override
    public <S extends ShopExports, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopExportsRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopExportsRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopExportsRepository.deleteAllInBatch();
    }

    @Override
    public ShopExports getOne(Long id) {
        return shopExportsRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopExports> entities) {
        shopExportsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopExportsRepository.deleteAll();
    }

    @Override
    public ShopExports getById(Long id) {
        return shopExportsRepository.getById(id);
    }

    @Override
    public ShopExports getReferenceById(Long id) {
        return shopExportsRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopExports> List<S> findAll(Example<S> example) {
        return shopExportsRepository.findAll(example);
    }

    @Override
    public <S extends ShopExports> List<S> findAll(Example<S> example, Sort sort) {
        return shopExportsRepository.findAll(example, sort);
    }  
	
	
	
}
