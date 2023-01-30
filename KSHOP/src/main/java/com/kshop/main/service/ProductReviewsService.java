package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ProductReviews;

public interface ProductReviewsService{

	<S extends ProductReviews> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductReviews> List<S> findAll(Example<S> example);

	ProductReviews getReferenceById(Long id);

	ProductReviews getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ProductReviews> entities);

	ProductReviews getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends ProductReviews, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(ProductReviews entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends ProductReviews> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<ProductReviews> entities);

	<S extends ProductReviews> long count(Example<S> example);

	void deleteInBatch(Iterable<ProductReviews> entities);

	<S extends ProductReviews> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ProductReviews> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductReviews> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends ProductReviews> List<S> saveAll(Iterable<S> entities);

	Optional<ProductReviews> findById(Long id);

	List<ProductReviews> findAllById(Iterable<Long> ids);

	List<ProductReviews> findAll(Sort sort);

	Page<ProductReviews> findAll(Pageable pageable);

	List<ProductReviews> findAll();

	<S extends ProductReviews> Optional<S> findOne(Example<S> example);

	<S extends ProductReviews> S save(S entity);

	List<ProductReviews> selectAllByProductId(Long id);

    ProductReviews selectByCustomerIdAndProductId(Long customerID, Long productID);

}
