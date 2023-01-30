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

import com.kshop.main.domain.PaymentTypes;
import com.kshop.main.repository.PaymentTypeRepository;
import com.kshop.main.service.PaymentTypeService;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService{
	@Autowired
	PaymentTypeRepository paymentTypeRepository;

	@Override
	public <S extends PaymentTypes> S save(S entity) {
		return paymentTypeRepository.save(entity);
	}

	@Override
	public <S extends PaymentTypes> Optional<S> findOne(Example<S> example) {
		return paymentTypeRepository.findOne(example);
	}

	@Override
	public List<PaymentTypes> findAll() {
		return paymentTypeRepository.findAll();
	}

	@Override
	public Page<PaymentTypes> findAll(Pageable pageable) {
		return paymentTypeRepository.findAll(pageable);
	}

	@Override
	public List<PaymentTypes> findAll(Sort sort) {
		return paymentTypeRepository.findAll(sort);
	}

	@Override
	public List<PaymentTypes> findAllById(Iterable<Long> ids) {
		return paymentTypeRepository.findAllById(ids);
	}

	@Override
	public Optional<PaymentTypes> findById(Long id) {
		return paymentTypeRepository.findById(id);
	}

	@Override
	public <S extends PaymentTypes> List<S> saveAll(Iterable<S> entities) {
		return paymentTypeRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		paymentTypeRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return paymentTypeRepository.existsById(id);
	}

	@Override
	public <S extends PaymentTypes> S saveAndFlush(S entity) {
		return paymentTypeRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends PaymentTypes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return paymentTypeRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends PaymentTypes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return paymentTypeRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<PaymentTypes> entities) {
		paymentTypeRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends PaymentTypes> long count(Example<S> example) {
		return paymentTypeRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<PaymentTypes> entities) {
		paymentTypeRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return paymentTypeRepository.count();
	}

	@Override
	public <S extends PaymentTypes> boolean exists(Example<S> example) {
		return paymentTypeRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		paymentTypeRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		paymentTypeRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(PaymentTypes entity) {
		paymentTypeRepository.delete(entity);
	}

	@Override
	public <S extends PaymentTypes, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return paymentTypeRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		paymentTypeRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		paymentTypeRepository.deleteAllInBatch();
	}

	@Override
	public PaymentTypes getOne(Long id) {
		return paymentTypeRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends PaymentTypes> entities) {
		paymentTypeRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		paymentTypeRepository.deleteAll();
	}

	@Override
	public PaymentTypes getById(Long id) {
		return paymentTypeRepository.getById(id);
	}

	@Override
	public PaymentTypes getReferenceById(Long id) {
		return paymentTypeRepository.getReferenceById(id);
	}

	@Override
	public <S extends PaymentTypes> List<S> findAll(Example<S> example) {
		return paymentTypeRepository.findAll(example);
	}

	@Override
	public <S extends PaymentTypes> List<S> findAll(Example<S> example, Sort sort) {
		return paymentTypeRepository.findAll(example, sort);
	}
	
	
}
