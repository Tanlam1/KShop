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

import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.repository.ShopCustomerVouchersRepository;
import com.kshop.main.repository.ShopVouchersRepository;
import com.kshop.main.service.ShopCustomerVouchersService;
import com.kshop.main.service.ShopVouchersService;

@Service
public class ShopCustomerVouchersServiceImpl implements ShopCustomerVouchersService {

	
	@Autowired
	ShopCustomerVouchersRepository shopCustomerVouchersRepository;
	@Autowired
	ShopVouchersRepository shopVouchersRepository;

    @Override
    public <S extends ShopCustomerVouchers> S save(S entity) {
        return shopCustomerVouchersRepository.save(entity);
    }

    @Override
    public <S extends ShopCustomerVouchers> Optional<S> findOne(Example<S> example) {
        return shopCustomerVouchersRepository.findOne(example);
    }

    @Override
    public List<ShopCustomerVouchers> findAll() {
        return shopCustomerVouchersRepository.findAll();
    }

    @Override
    public Page<ShopCustomerVouchers> findAll(Pageable pageable) {
        return shopCustomerVouchersRepository.findAll(pageable);
    }

    @Override
    public List<ShopCustomerVouchers> findAll(Sort sort) {
        return shopCustomerVouchersRepository.findAll(sort);
    }

    @Override
    public List<ShopCustomerVouchers> findAllById(Iterable<Long> ids) {
        return shopCustomerVouchersRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopCustomerVouchers> findById(Long id) {
        return shopCustomerVouchersRepository.findById(id);
    }

    @Override
    public <S extends ShopCustomerVouchers> List<S> saveAll(Iterable<S> entities) {
        return shopCustomerVouchersRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopCustomerVouchersRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopCustomerVouchersRepository.existsById(id);
    }

    @Override
    public <S extends ShopCustomerVouchers> S saveAndFlush(S entity) {
        return shopCustomerVouchersRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopCustomerVouchers> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopCustomerVouchersRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopCustomerVouchers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopCustomerVouchersRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopCustomerVouchers> entities) {
        shopCustomerVouchersRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopCustomerVouchers> long count(Example<S> example) {
        return shopCustomerVouchersRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopCustomerVouchers> entities) {
        shopCustomerVouchersRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopCustomerVouchersRepository.count();
    }

    @Override
    public <S extends ShopCustomerVouchers> boolean exists(Example<S> example) {
        return shopCustomerVouchersRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopCustomerVouchersRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopCustomerVouchersRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopCustomerVouchers entity) {
        shopCustomerVouchersRepository.delete(entity);
    }

    @Override
    public <S extends ShopCustomerVouchers, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopCustomerVouchersRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopCustomerVouchersRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopCustomerVouchersRepository.deleteAllInBatch();
    }

    @Override
    public ShopCustomerVouchers getOne(Long id) {
        return shopCustomerVouchersRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopCustomerVouchers> entities) {
        shopCustomerVouchersRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopCustomerVouchersRepository.deleteAll();
    }

    @Override
    public ShopCustomerVouchers getById(Long id) {
        return shopCustomerVouchersRepository.getById(id);
    }

    @Override
    public ShopCustomerVouchers getReferenceById(Long id) {
        return shopCustomerVouchersRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopCustomerVouchers> List<S> findAll(Example<S> example) {
        return shopCustomerVouchersRepository.findAll(example);
    }

    @Override
    public <S extends ShopCustomerVouchers> List<S> findAll(Example<S> example, Sort sort) {
        return shopCustomerVouchersRepository.findAll(example, sort);
    }

    @Override
    public boolean isUse(Long customer_id, Long voucher_id) {
        
        ShopVouchers shopVouchers = shopVouchersRepository.findById(voucher_id).get();
        if(shopVouchers != null) {
            ShopCustomerVouchers shopCustomerVouchers = shopCustomerVouchersRepository.selectCustomerCanUse(customer_id, voucher_id);
            
            if(shopCustomerVouchers != null) return !(shopCustomerVouchers.is_used());
        }
        
        return false;
    }

    @Override
    public ShopCustomerVouchers selectByCustomerIdAndVoucherId(Long customer_id, Long voucher_id) {
        // TODO Auto-generated method stub
        return shopCustomerVouchersRepository.selectCustomerCanUse(customer_id, voucher_id);
    }

    @Override
    public List<ShopCustomerVouchers> selectByCustomerIdUsed(Long customer_id) {
        // TODO Auto-generated method stub
        return shopCustomerVouchersRepository.selectByCustomerIdUsed(customer_id);
    }

    @Override
    public List<ShopCustomerVouchers> selectsByVoucherId(Long voucher_id) {
        // TODO Auto-generated method stub
        return shopCustomerVouchersRepository.selectsByVoucherId(voucher_id);
    }
	
	
}
