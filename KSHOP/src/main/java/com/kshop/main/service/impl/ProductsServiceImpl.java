package com.kshop.main.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.kshop.main.domain.Products;
import com.kshop.main.repository.ProductsReponsitory;
import com.kshop.main.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{
	@Autowired
	ProductsReponsitory productsReponsitory;

	@Override
	public <S extends Products> S save(S entity) {
		return productsReponsitory.save(entity);
	}

	@Override
	public <S extends Products> Optional<S> findOne(Example<S> example) {
		return productsReponsitory.findOne(example);
	}

	@Override
	public List<Products> findAll() {
		return productsReponsitory.findAll();
	}

	@Override
	public Page<Products> findAll(Pageable pageable) {
		return productsReponsitory.findAll(pageable);
	}

	@Override
	public List<Products> findAll(Sort sort) {
		return productsReponsitory.findAll(sort);
	}

	@Override
	public List<Products> findAllById(Iterable<Long> ids) {
		return productsReponsitory.findAllById(ids);
	}

	@Override
	public Optional<Products> findById(Long id) {
		return productsReponsitory.findById(id);
	}

	@Override
	public <S extends Products> List<S> saveAll(Iterable<S> entities) {
		return productsReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		productsReponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return productsReponsitory.existsById(id);
	}

	@Override
	public <S extends Products> S saveAndFlush(S entity) {
		return productsReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productsReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Products> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productsReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Products> entities) {
		productsReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends Products> long count(Example<S> example) {
		return productsReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Products> entities) {
		productsReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productsReponsitory.count();
	}

	@Override
	public <S extends Products> boolean exists(Example<S> example) {
		return productsReponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		productsReponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productsReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Products entity) {
		productsReponsitory.delete(entity);
	}

	@Override
	public <S extends Products, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productsReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productsReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productsReponsitory.deleteAllInBatch();
	}

	@Override
	public Products getOne(Long id) {
		return productsReponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Products> entities) {
		productsReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productsReponsitory.deleteAll();
	}

	@Override
	public Products getById(Long id) {
		return productsReponsitory.getById(id);
	}

	@Override
	public Products getReferenceById(Long id) {
		return productsReponsitory.getReferenceById(id);
	}

	@Override
	public <S extends Products> List<S> findAll(Example<S> example) {
		return productsReponsitory.findAll(example);
	}

	@Override
	public <S extends Products> List<S> findAll(Example<S> example, Sort sort) {
		return productsReponsitory.findAll(example, sort);
	}

    @Override
    public List<Products> selectBestSellerProducts(int start, int total) {
        // TODO Auto-generated method stub
        return productsReponsitory.selectBestSellerProducts(start, total);
    }

    @Override
    public Page<Products> findAllByIsFeatured(PageRequest of) {
        // TODO Auto-generated method stub
        return productsReponsitory.findAllByIsFeatured(of);
    }

    @Override
    public List<Products> selectsAllByCategoryId(Long id) {
        // TODO Auto-generated method stub
        return productsReponsitory.selectsAllByCategoryId(id);
    }

    @Override
    public List<Products> sp_SwipLoaiSanPhamTrangPhuc() {
        // TODO Auto-generated method stub
        return productsReponsitory.sp_SwipLoaiSanPhamTrangPhuc();
    }

    @Override
    public List<Products> sp_SwipLoaiSanPhamDienTu() {
        // TODO Auto-generated method stub
        return productsReponsitory.sp_SwipLoaiSanPhamDienTu();
    }

    @Override
    public List<Products> sp_SwipLoaiSanPhamNhavsbep() {
        // TODO Auto-generated method stub
        return productsReponsitory.sp_SwipLoaiSanPhamNhavsbep();
    }

    @Override

    public List<Products> findByProductName(String name) {
        // TODO Auto-generated method stub
        return productsReponsitory.findByProductName(name);
    }

    public Products checkHasProduct(Long idProduct, String productCode) {
        // TODO Auto-generated method stub
        return productsReponsitory.checkHasProduct(idProduct, productCode);
    }

    @Override
    public List<Products> selectsAllByUserId(Long id) {
        // TODO Auto-generated method stub
        return productsReponsitory.selectsAllByUserId(id);
    }


}
