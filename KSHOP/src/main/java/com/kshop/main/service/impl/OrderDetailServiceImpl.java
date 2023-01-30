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

import com.kshop.main.domain.OrderDetails;
import com.kshop.main.repository.OrderDetailRepository;
import com.kshop.main.service.OrderDetailService;
import com.kshop.main.service.OrderService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public <S extends OrderDetails> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public <S extends OrderDetails> Optional<S> findOne(Example<S> example) {
		return orderDetailRepository.findOne(example);
	}

	@Override
	public List<OrderDetails> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Page<OrderDetails> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetails> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public List<OrderDetails> findAllById(Iterable<Long> ids) {
		return orderDetailRepository.findAllById(ids);
	}

	@Override
	public Optional<OrderDetails> findById(Long id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public <S extends OrderDetails> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return orderDetailRepository.existsById(id);
	}

	@Override
	public <S extends OrderDetails> S saveAndFlush(S entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetails> entities) {
		orderDetailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetails> long count(Example<S> example) {
		return orderDetailRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetails> entities) {
		orderDetailRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public <S extends OrderDetails> boolean exists(Example<S> example) {
		return orderDetailRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		orderDetailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(OrderDetails entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public <S extends OrderDetails, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderDetailRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		orderDetailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailRepository.deleteAllInBatch();
	}

	@Override
	public OrderDetails getOne(Long id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetails> entities) {
		orderDetailRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}

	@Override
	public OrderDetails getById(Long id) {
		return orderDetailRepository.getById(id);
	}

	@Override
	public OrderDetails getReferenceById(Long id) {
		return orderDetailRepository.getReferenceById(id);
	}

	@Override
	public <S extends OrderDetails> List<S> findAll(Example<S> example) {
		return orderDetailRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetails> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailRepository.findAll(example, sort);
	}

    @Override
    public List<OrderDetails> selectByOrderId(Long id) {
        // TODO Auto-generated method stub
        return orderDetailRepository.selectByOrderId(id);
    }
	
	
}
