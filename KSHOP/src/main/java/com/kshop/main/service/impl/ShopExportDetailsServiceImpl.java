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

import com.kshop.main.domain.ShopExportDetails;
import com.kshop.main.domain.ShopImportDetails;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.repository.ShopExportDetailRepository;
import com.kshop.main.repository.ShopImportDetailRepository;
import com.kshop.main.repository.ShopImportsRepository;
import com.kshop.main.repository.ShopStoresRepository;
import com.kshop.main.service.ShopExportDetailsService;
import com.kshop.main.service.ShopImportDetailsService;
import com.kshop.main.service.ShopImportsService;
import com.kshop.main.service.ShopStoresService;

@Service
public class ShopExportDetailsServiceImpl implements ShopExportDetailsService {
    
    @Autowired
    ShopExportDetailRepository shopExportDetailRepository;

    @Override
    public <S extends ShopExportDetails> S save(S entity) {
        return shopExportDetailRepository.save(entity);
    }

    @Override
    public <S extends ShopExportDetails> Optional<S> findOne(Example<S> example) {
        return shopExportDetailRepository.findOne(example);
    }

    @Override
    public List<ShopExportDetails> findAll() {
        return shopExportDetailRepository.findAll();
    }

    @Override
    public Page<ShopExportDetails> findAll(Pageable pageable) {
        return shopExportDetailRepository.findAll(pageable);
    }

    @Override
    public List<ShopExportDetails> findAll(Sort sort) {
        return shopExportDetailRepository.findAll(sort);
    }

    @Override
    public List<ShopExportDetails> findAllById(Iterable<Long> ids) {
        return shopExportDetailRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopExportDetails> findById(Long id) {
        return shopExportDetailRepository.findById(id);
    }

    @Override
    public <S extends ShopExportDetails> List<S> saveAll(Iterable<S> entities) {
        return shopExportDetailRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopExportDetailRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopExportDetailRepository.existsById(id);
    }

    @Override
    public <S extends ShopExportDetails> S saveAndFlush(S entity) {
        return shopExportDetailRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopExportDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopExportDetailRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopExportDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopExportDetailRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopExportDetails> entities) {
        shopExportDetailRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopExportDetails> long count(Example<S> example) {
        return shopExportDetailRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopExportDetails> entities) {
        shopExportDetailRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopExportDetailRepository.count();
    }

    @Override
    public <S extends ShopExportDetails> boolean exists(Example<S> example) {
        return shopExportDetailRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopExportDetailRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopExportDetailRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopExportDetails entity) {
        shopExportDetailRepository.delete(entity);
    }

    @Override
    public <S extends ShopExportDetails, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopExportDetailRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopExportDetailRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopExportDetailRepository.deleteAllInBatch();
    }

    @Override
    public ShopExportDetails getOne(Long id) {
        return shopExportDetailRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopExportDetails> entities) {
        shopExportDetailRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopExportDetailRepository.deleteAll();
    }

    @Override
    public ShopExportDetails getById(Long id) {
        return shopExportDetailRepository.getById(id);
    }

    @Override
    public ShopExportDetails getReferenceById(Long id) {
        return shopExportDetailRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopExportDetails> List<S> findAll(Example<S> example) {
        return shopExportDetailRepository.findAll(example);
    }

    @Override
    public <S extends ShopExportDetails> List<S> findAll(Example<S> example, Sort sort) {
        return shopExportDetailRepository.findAll(example, sort);
    }
    
    
}
