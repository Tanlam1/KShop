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

import com.kshop.main.domain.ProductReviews;
import com.kshop.main.repository.ProductReviewsReponsitory;
import com.kshop.main.repository.SupplierReponsitory;
import com.kshop.main.service.ProductReviewsService;

@Service
public class ProductReviewsServiceImpl implements ProductReviewsService{
	
	@Autowired
	ProductReviewsReponsitory productReviewsReponsitory;

	@Override
	public <S extends ProductReviews> S save(S entity) {
		return productReviewsReponsitory.save(entity);
	}

	@Override
	public <S extends ProductReviews> Optional<S> findOne(Example<S> example) {
		return productReviewsReponsitory.findOne(example);
	}

	@Override
	public List<ProductReviews> findAll() {
		return productReviewsReponsitory.findAll();
	}

	@Override
	public Page<ProductReviews> findAll(Pageable pageable) {
		return productReviewsReponsitory.findAll(pageable);
	}

	@Override
	public List<ProductReviews> findAll(Sort sort) {
		return productReviewsReponsitory.findAll(sort);
	}

	@Override
	public List<ProductReviews> findAllById(Iterable<Long> ids) {
		return productReviewsReponsitory.findAllById(ids);
	}

	@Override
	public Optional<ProductReviews> findById(Long id) {
		return productReviewsReponsitory.findById(id);
	}

	@Override
	public <S extends ProductReviews> List<S> saveAll(Iterable<S> entities) {
		return productReviewsReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		productReviewsReponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return productReviewsReponsitory.existsById(id);
	}

	@Override
	public <S extends ProductReviews> S saveAndFlush(S entity) {
		return productReviewsReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductReviews> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productReviewsReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ProductReviews> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productReviewsReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ProductReviews> entities) {
		productReviewsReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductReviews> long count(Example<S> example) {
		return productReviewsReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductReviews> entities) {
		productReviewsReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productReviewsReponsitory.count();
	}

	@Override
	public <S extends ProductReviews> boolean exists(Example<S> example) {
		return productReviewsReponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		productReviewsReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productReviewsReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ProductReviews entity) {
		productReviewsReponsitory.delete(entity);
	}

	@Override
	public <S extends ProductReviews, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productReviewsReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productReviewsReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productReviewsReponsitory.deleteAllInBatch();
	}

	@Override
	public ProductReviews getOne(Long id) {
		return productReviewsReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductReviews> entities) {
		productReviewsReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productReviewsReponsitory.deleteAll();
	}

	@Override
	public ProductReviews getById(Long id) {
		return productReviewsReponsitory.getById(id);
	}

	@Override
	public ProductReviews getReferenceById(Long id) {
		return productReviewsReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends ProductReviews> List<S> findAll(Example<S> example) {
		return productReviewsReponsitory.findAll(example);
	}

	@Override
	public <S extends ProductReviews> List<S> findAll(Example<S> example, Sort sort) {
		return productReviewsReponsitory.findAll(example, sort);
	}

	@Override
	public List<ProductReviews> selectAllByProductId(Long id) {
		// TODO Auto-generated method stub
		return productReviewsReponsitory.selectAllByProductId(id);
	}

    @Override
    public ProductReviews selectByCustomerIdAndProductId(Long customerID, Long productID) {
        // TODO Auto-generated method stub
        return productReviewsReponsitory.selectByCustomerIdAndProductId(customerID, productID);
    }
	
}
