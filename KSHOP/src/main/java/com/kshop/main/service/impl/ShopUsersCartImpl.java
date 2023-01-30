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

import com.kshop.main.domain.Products;
import com.kshop.main.domain.ShopUsersCart;
import com.kshop.main.repository.ShopUsersCartRepository;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.ShopUsersCartService;

@Service
public class ShopUsersCartImpl implements ShopUsersCartService {
	@Autowired
	ShopUsersCartRepository shopUsersCartRepository;
	@Autowired
	ProductsService productsService;

    @Override
    public <S extends ShopUsersCart> S save(S entity) {
        return shopUsersCartRepository.save(entity);
    }

    @Override
    public <S extends ShopUsersCart> Optional<S> findOne(Example<S> example) {
        return shopUsersCartRepository.findOne(example);
    }

    @Override
    public List<ShopUsersCart> findAll() {
        return shopUsersCartRepository.findAll();
    }

    @Override
    public Page<ShopUsersCart> findAll(Pageable pageable) {
        return shopUsersCartRepository.findAll(pageable);
    }

    @Override
    public List<ShopUsersCart> findAll(Sort sort) {
        return shopUsersCartRepository.findAll(sort);
    }

    @Override
    public List<ShopUsersCart> findAllById(Iterable<Long> ids) {
        return shopUsersCartRepository.findAllById(ids);
    }

    @Override
    public Optional<ShopUsersCart> findById(Long id) {
        return shopUsersCartRepository.findById(id);
    }

    @Override
    public <S extends ShopUsersCart> List<S> saveAll(Iterable<S> entities) {
        return shopUsersCartRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        shopUsersCartRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return shopUsersCartRepository.existsById(id);
    }

    @Override
    public <S extends ShopUsersCart> S saveAndFlush(S entity) {
        return shopUsersCartRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends ShopUsersCart> List<S> saveAllAndFlush(Iterable<S> entities) {
        return shopUsersCartRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends ShopUsersCart> Page<S> findAll(Example<S> example, Pageable pageable) {
        return shopUsersCartRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<ShopUsersCart> entities) {
        shopUsersCartRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends ShopUsersCart> long count(Example<S> example) {
        return shopUsersCartRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<ShopUsersCart> entities) {
        shopUsersCartRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return shopUsersCartRepository.count();
    }

    @Override
    public <S extends ShopUsersCart> boolean exists(Example<S> example) {
        return shopUsersCartRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        shopUsersCartRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        shopUsersCartRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(ShopUsersCart entity) {
        shopUsersCartRepository.delete(entity);
    }

    @Override
    public <S extends ShopUsersCart, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return shopUsersCartRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        shopUsersCartRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        shopUsersCartRepository.deleteAllInBatch();
    }

    @Override
    public ShopUsersCart getOne(Long id) {
        return shopUsersCartRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends ShopUsersCart> entities) {
        shopUsersCartRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        shopUsersCartRepository.deleteAll();
    }

    @Override
    public ShopUsersCart getById(Long id) {
        return shopUsersCartRepository.getById(id);
    }

    @Override
    public ShopUsersCart getReferenceById(Long id) {
        return shopUsersCartRepository.getReferenceById(id);
    }

    @Override
    public <S extends ShopUsersCart> List<S> findAll(Example<S> example) {
        return shopUsersCartRepository.findAll(example);
    }

    @Override
    public <S extends ShopUsersCart> List<S> findAll(Example<S> example, Sort sort) {
        return shopUsersCartRepository.findAll(example, sort);
    }

    @Override
    public List<ShopUsersCart> selectAllByCustomerId(Long id) {
        // TODO Auto-generated method stub
        return shopUsersCartRepository.selectAllByCustomerId(id);
    }

    @Override
    public boolean addToCart(ShopUsersCart shopUsersCart, Long customerId) {
        // TODO Auto-generated method stub
        ShopUsersCart shopUsersCart2 = shopUsersCartRepository.selectByProductIdOfCustomerAndTypeId(shopUsersCart.getProduct().getId(), shopUsersCart.getType_item_id(), customerId);        

        List<ShopUsersCart> list = shopUsersCartRepository.selectAllByProductIdOfCustomer(shopUsersCart.getProduct().getId(), customerId);
        Optional<Products> products = productsService.findById(shopUsersCart.getProduct().getId());

        Double totalQuantity = 999999D;
        if(products.isPresent()) {
            totalQuantity = products.get().getQuantity();
        }
        Double quantityAdd = shopUsersCart.getQuantity();
        
        for(ShopUsersCart item: list) {
            quantityAdd += item.getQuantity();
            
        }
        
        if(quantityAdd > totalQuantity) {
            return false;
        }
        
        if(shopUsersCart2 != null) {
            Double quantity = shopUsersCart2.getQuantity() + shopUsersCart.getQuantity() > shopUsersCart2.getProduct().getQuantity() ? shopUsersCart2.getProduct().getQuantity() : shopUsersCart2.getQuantity() + shopUsersCart.getQuantity();
            shopUsersCart.setQuantity(quantity);
            shopUsersCart.setId(shopUsersCart2.getId());  
            shopUsersCart.setShopProductVouchers(shopUsersCart2.getShopProductVouchers());
            shopUsersCart.set_discount(shopUsersCart2.is_discount());
        } 
        shopUsersCartRepository.save(shopUsersCart);        
                
        return true;
    }
	
}
