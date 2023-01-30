package com.kshop.main.service.impl;

import java.util.Date;
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

import com.kshop.main.common.APIResponse;
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.Users;
import com.kshop.main.exception.UserNotFoundException;
import com.kshop.main.model.LoginDTO;
import com.kshop.main.model.RegisterDTO;
import com.kshop.main.repository.CustomerRepository;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.utils.MD5;


@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
    SessionService sessionService;
	
	@Override
    public APIResponse regiter(RegisterDTO registerDTO) {
        
        APIResponse response = new APIResponse();
        
        Customers userOptional = customerRepository.findOneByEmail(registerDTO.getEmail());
        if(userOptional != null) {
            
            response.setError("Email đã tồn tại");
            response.setStatus(401);            
        } else {
            
            Customers customers = new Customers();
            String[] emails = registerDTO.getEmail().split("@");        
            
            customers.setUsername(emails[0]);
            customers.setCode("");
            customers.setEmail(registerDTO.getEmail());
            customers.setLast_name(registerDTO.getLastName());
            customers.setFirst_name(registerDTO.getFirstName());
            customers.setPassword(MD5.encode(registerDTO.getPassword()));
            customers.setCreated_at(new Date());
            customers.setStatus(false);
            
            customerRepository.save(customers);
            response.setData(customerRepository.findFirstByOrderByIdDesc());
            response.setStatus(200);
            sessionService.set("userLogin", response.getData());
        }               
        
        return response;
    }
    
    
    @Override
    public APIResponse login(LoginDTO loginDTO) {
        
        String password = loginDTO.getPassword();
        String[] emails = loginDTO.getEmail().split("@");   
        
        Customers customers = customerRepository.findByEmailOrUsername(loginDTO.getEmail(), emails[0]);
        
        APIResponse response = new APIResponse();
        
        if(customers != null && MD5.encode(password).equals(customers.getPassword())) {
            Customers customersRp = customers;

            customersRp.setPassword("");
            
            response.setData(customersRp);
            response.setStatus(200);
            
            sessionService.set("userLogin", customersRp);
            
        } else {
            
            response.setData("Login error");
            
            response.setStatus(400);
            
            response.setError("Email hoặc mật khẩu chưa chính xác.");
        }
        
        return response;
        
    }

	@Override
	public <S extends Customers> S save(S entity) {
		return customerRepository.save(entity);
	}

	@Override
	public <S extends Customers> Optional<S> findOne(Example<S> example) {
		return customerRepository.findOne(example);
	}

	@Override
	public List<Customers> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Page<Customers> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public List<Customers> findAll(Sort sort) {
		return customerRepository.findAll(sort);
	}

	@Override
	public List<Customers> findAllById(Iterable<Long> ids) {
		return customerRepository.findAllById(ids);
	}

	@Override
	public Optional<Customers> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public <S extends Customers> List<S> saveAll(Iterable<S> entities) {
		return customerRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		customerRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return customerRepository.existsById(id);
	}

	@Override
	public <S extends Customers> S saveAndFlush(S entity) {
		return customerRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Customers> List<S> saveAllAndFlush(Iterable<S> entities) {
		return customerRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Customers> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Customers> entities) {
		customerRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Customers> long count(Example<S> example) {
		return customerRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Customers> entities) {
		customerRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return customerRepository.count();
	}

	@Override
	public <S extends Customers> boolean exists(Example<S> example) {
		return customerRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		customerRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Customers entity) {
		customerRepository.delete(entity);
	}

	@Override
	public <S extends Customers, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return customerRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		customerRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		customerRepository.deleteAllInBatch();
	}

	@Override
	public Customers getOne(Long id) {
		return customerRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Customers> entities) {
		customerRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}

	@Override
	public Customers getById(Long id) {
		return customerRepository.getById(id);
	}

	@Override
	public Customers getReferenceById(Long id) {
		return customerRepository.getReferenceById(id);
	}

	@Override
	public <S extends Customers> List<S> findAll(Example<S> example) {
		return customerRepository.findAll(example);
	}

	@Override
	public <S extends Customers> List<S> findAll(Example<S> example, Sort sort) {
		return customerRepository.findAll(example, sort);
	}
	
	@Override
	public void updateResetPassword(String token,String email) throws UserNotFoundException {
		Customers customers = customerRepository.findByEmailForActivateCode(email);
    	
    	if (customers != null) {
    		customers.setActivateCode(token);
			customerRepository.save(customers);
		}else {
			throw new UserNotFoundException("Could not find any customer with email " + email); 
		}
    }
    
    @Override
	public Customers getToken(String activateCode) {
    	return customerRepository.findByActivateCode(activateCode);
    }
    
    @Override
	public void updatePassword(Customers customers, String newPassowrd) {
//    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	
    	String encodePassword = MD5.encode(newPassowrd);
    	
    	customers.setPassword(encodePassword);
    	customers.setActivateCode(null);
    	
    	customerRepository.save(customers);
    }

	
}
