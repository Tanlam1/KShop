package com.kshop.main.model;

import java.util.Date;


import com.kshop.main.domain.Customers;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopUsersCartDTO {

    private long id;
    private Double quantiy;
    private Products product;
    private Customers customer;
    private Users user;
    private Date created_at;
    private Date updated_at;
}
