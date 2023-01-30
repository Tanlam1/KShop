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

import com.kshop.main.domain.MoneyOrders;
import com.kshop.main.repository.MoneyOrdersRepository;
import com.kshop.main.service.MoneyOrdersService;

@Service
public class MoneyOrdersImpl implements MoneyOrdersService {
	
    @Autowired
    MoneyOrdersRepository moneyOrdersRepository;

    @Override
    public <S extends MoneyOrders> S save(S entity) {
        return moneyOrdersRepository.save(entity);
    }

    @Override
    public <S extends MoneyOrders> Optional<S> findOne(Example<S> example) {
        return moneyOrdersRepository.findOne(example);
    }

    @Override
    public List<MoneyOrders> findAll() {
        return moneyOrdersRepository.findAll();
    }

    @Override
    public Page<MoneyOrders> findAll(Pageable pageable) {
        return moneyOrdersRepository.findAll(pageable);
    }

    @Override
    public List<MoneyOrders> findAll(Sort sort) {
        return moneyOrdersRepository.findAll(sort);
    }

    @Override
    public List<MoneyOrders> findAllById(Iterable<Long> ids) {
        return moneyOrdersRepository.findAllById(ids);
    }

    @Override
    public Optional<MoneyOrders> findById(Long id) {
        return moneyOrdersRepository.findById(id);
    }

    @Override
    public <S extends MoneyOrders> List<S> saveAll(Iterable<S> entities) {
        return moneyOrdersRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        moneyOrdersRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return moneyOrdersRepository.existsById(id);
    }

    @Override
    public <S extends MoneyOrders> S saveAndFlush(S entity) {
        return moneyOrdersRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends MoneyOrders> List<S> saveAllAndFlush(Iterable<S> entities) {
        return moneyOrdersRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends MoneyOrders> Page<S> findAll(Example<S> example, Pageable pageable) {
        return moneyOrdersRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<MoneyOrders> entities) {
        moneyOrdersRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends MoneyOrders> long count(Example<S> example) {
        return moneyOrdersRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<MoneyOrders> entities) {
        moneyOrdersRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return moneyOrdersRepository.count();
    }

    @Override
    public <S extends MoneyOrders> boolean exists(Example<S> example) {
        return moneyOrdersRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        moneyOrdersRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        moneyOrdersRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(MoneyOrders entity) {
        moneyOrdersRepository.delete(entity);
    }

    @Override
    public <S extends MoneyOrders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return moneyOrdersRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        moneyOrdersRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        moneyOrdersRepository.deleteAllInBatch();
    }

    @Override
    public MoneyOrders getOne(Long id) {
        return moneyOrdersRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends MoneyOrders> entities) {
        moneyOrdersRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        moneyOrdersRepository.deleteAll();
    }

    @Override
    public MoneyOrders getById(Long id) {
        return moneyOrdersRepository.getById(id);
    }

    @Override
    public MoneyOrders getReferenceById(Long id) {
        return moneyOrdersRepository.getReferenceById(id);
    }

    @Override
    public <S extends MoneyOrders> List<S> findAll(Example<S> example) {
        return moneyOrdersRepository.findAll(example);
    }

    @Override
    public <S extends MoneyOrders> List<S> findAll(Example<S> example, Sort sort) {
        return moneyOrdersRepository.findAll(example, sort);
    }

    @Override
    public List<MoneyOrders> selectAllByUserId(Long id) {
        // TODO Auto-generated method stub
        return moneyOrdersRepository.selectAllByUserId(id);
    }

    @Override
    public MoneyOrders selectByUserIdAndOrderId(Long UserId, Long OrderId) {
        // TODO Auto-generated method stub
        return moneyOrdersRepository.selectByUserIdAndOrderId(UserId, OrderId);
    }

    @Override
    public List<MoneyOrders> selectAllByUserIdOfStore(Long id) {
        // TODO Auto-generated method stub
        return moneyOrdersRepository.selectAllByUserIdOfStore(id);
    }
    
    
	
}
