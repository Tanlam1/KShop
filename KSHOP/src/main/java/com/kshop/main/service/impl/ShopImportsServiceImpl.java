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

import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.repository.ShopImportsRepository;
import com.kshop.main.repository.ShopStoresRepository;
import com.kshop.main.service.ShopImportsService;
import com.kshop.main.service.ShopStoresService;

@Service
public class ShopImportsServiceImpl implements ShopImportsService {

	@Autowired
	ShopImportsRepository shopImportsRepository;

    @Override
    public <S extends ShopImports> S save(S entity) {
        return shopImportsRepository.save(entity);
    }

    @Override
    public <S extends ShopImports> Optional<S> findOne(Example<S> example) {
        return shopImportsRepository.findOne(example);
    }

    @Override
    public List<ShopImports> findAll() {
        return shopImportsRepository.findAll();
    }

    @Override
    public Page<ShopImports> findAll(Pageable pageable) {
        return shopImportsRepository.findAll(pageable);
    }

    @Override
    public List<ShopImports> findAll(Sort sort) {
        return shopImportsRepository.findAll(sort);
    }

    @Override
    public List<ShopImports> findAllById(Iterable<Long> ids) {
        return shopImportsRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopImports> findById(Long id) {
        return shopImportsRepository.findById(id);
    }

    @Override
    public <S extends ShopImports> List<S> saveAll(Iterable<S> entities) {
        return shopImportsRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopImportsRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopImportsRepository.existsById(id);
    }

    @Override
    public <S extends ShopImports> S saveAndFlush(S entity) {
        return shopImportsRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopImports> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopImportsRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopImports> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopImportsRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopImports> entities) {
        shopImportsRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopImports> long count(Example<S> example) {
        return shopImportsRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopImports> entities) {
        shopImportsRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopImportsRepository.count();
    }

    @Override
    public <S extends ShopImports> boolean exists(Example<S> example) {
        return shopImportsRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopImportsRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopImportsRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopImports entity) {
        shopImportsRepository.delete(entity);
    }

    @Override
    public <S extends ShopImports, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopImportsRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopImportsRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopImportsRepository.deleteAllInBatch();
    }

    @Override
    public ShopImports getOne(Long id) {
        return shopImportsRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopImports> entities) {
        shopImportsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopImportsRepository.deleteAll();
    }

    @Override
    public ShopImports getById(Long id) {
        return shopImportsRepository.getById(id);
    }

    @Override
    public ShopImports getReferenceById(Long id) {
        return shopImportsRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopImports> List<S> findAll(Example<S> example) {
        return shopImportsRepository.findAll(example);
    }

    @Override
    public <S extends ShopImports> List<S> findAll(Example<S> example, Sort sort) {
        return shopImportsRepository.findAll(example, sort);
    }

	
	
	
}
