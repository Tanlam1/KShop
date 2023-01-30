package com.kshop.main.utils;

import java.util.List;


import com.kshop.main.common.Constant;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;

public interface Roles {
    public static boolean UserHasRoles(List<Long> listCheck, SessionService sessionService, UserHasRolesService userHasRolesService) {
        
        Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogin.getId());

        boolean isAdmin = false;
        
        for(Long item: listCheck) {
            isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == item);
            
            if (isAdmin) {
                return isAdmin;
            }
        }
        
        return isAdmin;
    }
    public static boolean UserArgHasRoles(List<Long> listCheck, SessionService sessionService, UserHasRolesService userHasRolesService, Users users) {
        
        Users userLogin = users;
        
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogin.getId());

        boolean isAdmin = false;
        
        for(Long item: listCheck) {
            isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == item);
            
            if (isAdmin) {
                return isAdmin;
            }
        }
        
        return isAdmin;
    }
}
