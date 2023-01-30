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

import com.kshop.main.domain.ShopImportDetails;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.repository.ShopImportDetailRepository;
import com.kshop.main.repository.ShopImportsRepository;
import com.kshop.main.repository.ShopStoresRepository;
import com.kshop.main.service.ShopImportDetailsService;
import com.kshop.main.service.ShopImportsService;
import com.kshop.main.service.ShopStoresService;

@Service
public class ShopImportDetailsServiceImpl implements ShopImportDetailsService {

	@Autowired
	ShopImportDetailRepository shopImportDetailRepository;

    @Override
    public <S extends ShopImportDetails> S save(S entity) {
        return shopImportDetailRepository.save(entity);
    }

    @Override
    public <S extends ShopImportDetails> Optional<S> findOne(Example<S> example) {
        return shopImportDetailRepository.findOne(example);
    }

    @Override
    public List<ShopImportDetails> findAll() {
        return shopImportDetailRepository.findAll();
    }

    @Override
    public Page<ShopImportDetails> findAll(Pageable pageable) {
        return shopImportDetailRepository.findAll(pageable);
    }

    @Override
    public List<ShopImportDetails> findAll(Sort sort) {
        return shopImportDetailRepository.findAll(sort);
    }

    @Override
    public List<ShopImportDetails> findAllById(Iterable<Long> ids) {
        return shopImportDetailRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopImportDetails> findById(Long id) {
        return shopImportDetailRepository.findById(id);
    }

    @Override
    public <S extends ShopImportDetails> List<S> saveAll(Iterable<S> entities) {
        return shopImportDetailRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopImportDetailRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopImportDetailRepository.existsById(id);
    }

    @Override
    public <S extends ShopImportDetails> S saveAndFlush(S entity) {
        return shopImportDetailRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopImportDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopImportDetailRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopImportDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopImportDetailRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopImportDetails> entities) {
        shopImportDetailRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopImportDetails> long count(Example<S> example) {
        return shopImportDetailRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopImportDetails> entities) {
        shopImportDetailRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopImportDetailRepository.count();
    }

    @Override
    public <S extends ShopImportDetails> boolean exists(Example<S> example) {
        return shopImportDetailRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopImportDetailRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopImportDetailRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopImportDetails entity) {
        shopImportDetailRepository.delete(entity);
    }

    @Override
    public <S extends ShopImportDetails, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopImportDetailRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopImportDetailRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopImportDetailRepository.deleteAllInBatch();
    }

    @Override
    public ShopImportDetails getOne(Long id) {
        return shopImportDetailRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopImportDetails> entities) {
        shopImportDetailRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopImportDetailRepository.deleteAll();
    }

    @Override
    public ShopImportDetails getById(Long id) {
        return shopImportDetailRepository.getById(id);
    }

    @Override
    public ShopImportDetails getReferenceById(Long id) {
        return shopImportDetailRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopImportDetails> List<S> findAll(Example<S> example) {
        return shopImportDetailRepository.findAll(example);
    }

    @Override
    public <S extends ShopImportDetails> List<S> findAll(Example<S> example, Sort sort) {
        return shopImportDetailRepository.findAll(example, sort);
    }

	
	
}
