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
import com.kshop.main.domain.ShopProductVouchers;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.repository.ShopCustomerVouchersRepository;
import com.kshop.main.repository.ShopProductVouchersRepository;
import com.kshop.main.repository.ShopVouchersRepository;
import com.kshop.main.service.ShopCustomerVouchersService;
import com.kshop.main.service.ShopProductVouchersService;
import com.kshop.main.service.ShopVouchersService;

@Service
public class ShopProductVouchersServiceImpl implements ShopProductVouchersService {

	
	@Autowired
	ShopProductVouchersRepository shopProductVouchersRepository;
	@Autowired
	ShopVouchersRepository shopVouchersRepository;

    @Override
    public ShopProductVouchers selectCustomerCanUse(Long product_id, Long voucher_id) {
        return shopProductVouchersRepository.selectProductCanUse(product_id, voucher_id);
    }

    @Override
    public List<ShopProductVouchers> selectsByProductId(Long product_id) {
        return shopProductVouchersRepository.selectsByProductId(product_id);
    }

    @Override
    public <S extends ShopProductVouchers> S save(S entity) {
        return shopProductVouchersRepository.save(entity);
    }

    @Override
    public <S extends ShopProductVouchers> Optional<S> findOne(Example<S> example) {
        return shopProductVouchersRepository.findOne(example);
    }

    @Override
    public List<ShopProductVouchers> findAll() {
        return shopProductVouchersRepository.findAll();
    }

    @Override
    public Page<ShopProductVouchers> findAll(Pageable pageable) {
        return shopProductVouchersRepository.findAll(pageable);
    }

    @Override
    public List<ShopProductVouchers> findAll(Sort sort) {
        return shopProductVouchersRepository.findAll(sort);
    }

    @Override
    public List<ShopProductVouchers> findAllById(Iterable<Long> ids) {
        return shopProductVouchersRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopProductVouchers> findById(Long id) {
        return shopProductVouchersRepository.findById(id);
    }

    @Override
    public <S extends ShopProductVouchers> List<S> saveAll(Iterable<S> entities) {
        return shopProductVouchersRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopProductVouchersRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopProductVouchersRepository.existsById(id);
    }

    @Override
    public <S extends ShopProductVouchers> S saveAndFlush(S entity) {
        return shopProductVouchersRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopProductVouchers> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopProductVouchersRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopProductVouchers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopProductVouchersRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopProductVouchers> entities) {
        shopProductVouchersRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopProductVouchers> long count(Example<S> example) {
        return shopProductVouchersRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopProductVouchers> entities) {
        shopProductVouchersRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopProductVouchersRepository.count();
    }

    @Override
    public <S extends ShopProductVouchers> boolean exists(Example<S> example) {
        return shopProductVouchersRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopProductVouchersRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopProductVouchersRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopProductVouchers entity) {
        shopProductVouchersRepository.delete(entity);
    }

    @Override
    public <S extends ShopProductVouchers, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopProductVouchersRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopProductVouchersRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopProductVouchersRepository.deleteAllInBatch();
    }

    @Override
    public ShopProductVouchers getOne(Long id) {
        return shopProductVouchersRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopProductVouchers> entities) {
        shopProductVouchersRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopProductVouchersRepository.deleteAll();
    }

    @Override
    public ShopProductVouchers getById(Long id) {
        return shopProductVouchersRepository.getById(id);
    }

    @Override
    public ShopProductVouchers getReferenceById(Long id) {
        return shopProductVouchersRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopProductVouchers> List<S> findAll(Example<S> example) {
        return shopProductVouchersRepository.findAll(example);
    }

    @Override
    public <S extends ShopProductVouchers> List<S> findAll(Example<S> example, Sort sort) {
        return shopProductVouchersRepository.findAll(example, sort);
    }

    @Override
    public boolean isUse(Long product_id, Long voucher_id) {
        // TODO Auto-generated method stub
        ShopVouchers shopVouchers = shopVouchersRepository.findById(voucher_id).get();
        if(shopVouchers != null) {
            ShopProductVouchers shopProductVouchers = shopProductVouchersRepository.selectProductCanUse(product_id, voucher_id);
            
            if(shopProductVouchers != null) return true;
        }
        
        return false;
    }

    @Override
    public ShopProductVouchers selectByProductIdAndVoucherId(Long product_id, Long voucher_id) {
        // TODO Auto-generated method stub
        return shopProductVouchersRepository.selectProductCanUse(product_id, voucher_id);
    }

    @Override
    public Optional<ShopProductVouchers> selectByProductId(Long id) {
        // TODO Auto-generated method stub
        return shopProductVouchersRepository.selectByProductId(id);
    }

    @Override
    public List<ShopProductVouchers> selectsByVoucherId(Long voucher_id) {
        // TODO Auto-generated method stub
        return shopProductVouchersRepository.selectsByVoucherId(voucher_id);
    }
	
	
}
