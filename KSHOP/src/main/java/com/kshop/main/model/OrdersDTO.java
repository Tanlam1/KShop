package com.kshop.main.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

	@NotEmpty(message = "Vui lòng nhập tên của bạn")
	private String ship_name;
	
	@NotEmpty(message = "Vui lòng nhập địa chỉ của bạn")
	private String ship_address1;
	
	@NotEmpty(message = "Vui lòng nhập địa chỉ của bạn")
	private String ship_address2;
	
	@NotEmpty(message = "Vui lòng chọn thành phố của bạn")
	private String ship_city;
	
	@NotEmpty(message = "Vui lòng chọn state của bạn")
	private String ship_state;
	
	@NotEmpty(message = "Vui lòng nhập mã bưu chính của bạn")
	private String ship_postal_code;
	
	@NotEmpty(message = "Vui lòng chọn country của bạn")
	private String ship_country;
}
