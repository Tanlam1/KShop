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

import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.repository.ShopVouchersRepository;
import com.kshop.main.service.ShopVouchersService;

@Service
public class ShopVouchersServiceImpl implements ShopVouchersService {

	
	@Autowired
	ShopVouchersRepository shopVouchersRepository;

	@Override
	public <S extends ShopVouchers> S save(S entity) {
		return shopVouchersRepository.save(entity);
	}

	@Override
	public <S extends ShopVouchers> Optional<S> findOne(Example<S> example) {
		return shopVouchersRepository.findOne(example);
	}

	@Override
	public List<ShopVouchers> findAll() {
		return shopVouchersRepository.findAll();
	}

	@Override
	public Page<ShopVouchers> findAll(Pageable pageable) {
		return shopVouchersRepository.findAll(pageable);
	}

	@Override
	public List<ShopVouchers> findAll(Sort sort) {
		return shopVouchersRepository.findAll(sort);
	}

	@Override
	public List<ShopVouchers> findAllById(Iterable<Long> ids) {
		return shopVouchersRepository.findAllById(ids);
	}

	@Override
	public Optional<ShopVouchers> findById(Long id) {
		return shopVouchersRepository.findById(id);
	}

	@Override
	public <S extends ShopVouchers> List<S> saveAll(Iterable<S> entities) {
		return shopVouchersRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		shopVouchersRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return shopVouchersRepository.existsById(id);
	}

	@Override
	public <S extends ShopVouchers> S saveAndFlush(S entity) {
		return shopVouchersRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ShopVouchers> List<S> saveAllAndFlush(Iterable<S> entities) {
		return shopVouchersRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ShopVouchers> Page<S> findAll(Example<S> example, Pageable pageable) {
		return shopVouchersRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ShopVouchers> entities) {
		shopVouchersRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ShopVouchers> long count(Example<S> example) {
		return shopVouchersRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ShopVouchers> entities) {
		shopVouchersRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return shopVouchersRepository.count();
	}

	@Override
	public <S extends ShopVouchers> boolean exists(Example<S> example) {
		return shopVouchersRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		shopVouchersRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		shopVouchersRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ShopVouchers entity) {
		shopVouchersRepository.delete(entity);
	}

	@Override
	public <S extends ShopVouchers, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return shopVouchersRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		shopVouchersRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		shopVouchersRepository.deleteAllInBatch();
	}

	@Override
	public ShopVouchers getOne(Long id) {
		return shopVouchersRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ShopVouchers> entities) {
		shopVouchersRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		shopVouchersRepository.deleteAll();
	}

	@Override
	public ShopVouchers getById(Long id) {
		return shopVouchersRepository.getById(id);
	}

	@Override
	public ShopVouchers getReferenceById(Long id) {
		return shopVouchersRepository.getReferenceById(id);
	}

	@Override
	public <S extends ShopVouchers> List<S> findAll(Example<S> example) {
		return shopVouchersRepository.findAll(example);
	}

	@Override
	public <S extends ShopVouchers> List<S> findAll(Example<S> example, Sort sort) {
		return shopVouchersRepository.findAll(example, sort);
	}

    @Override
    public Optional<ShopVouchers> selectByVoucherName(String voucher_name) {
        // TODO Auto-generated method stub
        return shopVouchersRepository.selectByVoucherName(voucher_name);
    }
	
	
}
