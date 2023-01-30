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
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.model.LoginDTO;
import com.kshop.main.model.RegisterDTO;
import com.kshop.main.repository.UsersRepository;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.MD5;

@Service
public class UsersServiceImpl implements UsersService {
    
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;

    @Override
    public APIResponse regiter(RegisterDTO registerDTO) {
        
        APIResponse response = new APIResponse();
        
        Users userOptional = usersRepository.findOneByEmail(registerDTO.getEmail());
        if(userOptional != null) {
            
            response.setError("Email đã tồn tại");
            response.setStatus(401);            
        } else {
            
            Users users = new Users();
            String[] emails = registerDTO.getEmail().split("@");        
            
            users.setUsername(emails[0]);
            users.setEmail(registerDTO.getEmail());
            users.setLast_name(registerDTO.getLastName());
            users.setFirst_name(registerDTO.getFirstName());
            users.setPassword(MD5.encode(registerDTO.getPassword()));
            users.setCreated_at(new Date());
            users.setStatus(true);
            
            usersRepository.save(users);
            response.setData(usersRepository.findFirstByOrderByIdDesc());
            response.setStatus(200);            
        }               
        
        return response;
    }
    
    
	@Override
    public APIResponse login(LoginDTO loginDTO) {
	    
	    String password = loginDTO.getPassword();
        String[] emails = loginDTO.getEmail().split("@"); 
	    
	    Users user = usersRepository.findByEmailOrUsername(loginDTO.getEmail(), emails[0]);
	    
	    APIResponse response = new APIResponse();
	    
	    if(user != null && MD5.encode(password).equals(user.getPassword())) {
	        Users usersRp = user;
	                    
            List<UserHasRoles> list = userHasRolesService.selectsByUserId(usersRp.getId());
            
            boolean isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == 1 || 
                    e.getRoles().getId() == 4 || 
                    e.getRoles().getId() == 7);
            
            if(!isAdmin) {
                response.setData("Faild to access admin");
                
                response.setStatus(400);
                
                response.setError("Bạn không có quyền truy cập");
            } else {
                usersRp.setPassword("");
                
                response.setData(user);
                response.setStatus(200);
                
                sessionService.set("admin_kodoku_KShop", user);
            }
	        
	    } else {
	        
	        response.setData("Login error");
	        
	        response.setStatus(400);
	        
	        response.setError("Email hoặc mật khẩu chưa đúng. Vui lòng thử lại.");
	    }
	    
	    return response;
	    
	}

	@Override
	public <S extends Users> S save(S entity) {
		return usersRepository.save(entity);
	}

	@Override
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		return usersRepository.findOne(example);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	@Override
	public List<Users> findAll(Sort sort) {
		return usersRepository.findAll(sort);
	}

	@Override
	public List<Users> findAllById(Iterable<Long> ids) {
		return usersRepository.findAllById(ids);
	}

	@Override
	public Optional<Users> findById(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public <S extends Users> List<S> saveAll(Iterable<S> entities) {
		return usersRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		usersRepository.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return usersRepository.existsById(id);
	}

	@Override
	public <S extends Users> S saveAndFlush(S entity) {
		return usersRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
		return usersRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		return usersRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Users> entities) {
		usersRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Users> long count(Example<S> example) {
		return usersRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Users> entities) {
		usersRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return usersRepository.count();
	}

	@Override
	public <S extends Users> boolean exists(Example<S> example) {
		return usersRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		usersRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		usersRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Users entity) {
		usersRepository.delete(entity);
	}

	@Override
	public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return usersRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		usersRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		usersRepository.deleteAllInBatch();
	}

	@Override
	public Users getOne(Long id) {
		return usersRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Users> entities) {
		usersRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		usersRepository.deleteAll();
	}

	@Override
	public Users getById(Long id) {
		return usersRepository.getById(id);
	}

	@Override
	public Users getReferenceById(Long id) {
		return usersRepository.getReferenceById(id);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example) {
		return usersRepository.findAll(example);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		return usersRepository.findAll(example, sort);
	}


	@Override
	public Optional<Users> selectsByStoreId(Long id) {
		// TODO Auto-generated method stub
		return usersRepository.selectsByStoreId(id);
	}


    @Override
    public Users findByEmailOrUsername(String email, String username) {
        // TODO Auto-generated method stub
        return usersRepository.findByEmailOrUsername(email, username);
    }


}
