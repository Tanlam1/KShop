package com.kshop.main.controller.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.domain.Products;
import com.kshop.main.repository.ProductsReponsitory;

@Controller
@RequestMapping("/list-product")
public class ShopGridController extends com.kshop.main.controller.Controller {

	@Autowired
	ProductsReponsitory productsReponsitory;

	@Autowired
	HttpServletResponse response;

	@GetMapping("")
	public String layout(Model model) {

		dataLayoutMaster.setView("client/Shop/shop-grid-4cols");
		
		dataLayoutMaster.setMainCss("/css/style.min.css");
		
		dataLayoutMaster.setJsList(Arrays.asList("/js/shop-grid/shop-grid.js", "/js/shop-grid/search-shop-grid.js"));
		
		objsDataLayout.put("key", "value");

		dataLayoutMaster.setObjs(objsDataLayout);

		model.addAttribute(__CONTENT__, dataLayoutMaster);

		return clientLayout;
	}

	@GetMapping("/search")
	public String layoutSearch(Model model, @Param("keyWords") String name) throws IOException {
		
		dataLayoutMaster.setView("client/Shop/shop-grid-4cols");
		
		dataLayoutMaster.setMainCss("/css/style.min.css");
		
		dataLayoutMaster.setJsList(Arrays.asList("/js/shop-grid/search-shop-grid.js"));
		
		objsDataLayout.put("key", "value");
		
		dataLayoutMaster.setObjs(objsDataLayout);

		model.addAttribute(__CONTENT__, dataLayoutMaster);

		return clientLayout;
	}

//	@GetMapping("/{id}")
//	public String loadbycategory(Model model, @PathVariable("id") String id) {
//
//		dataLayoutMaster.setView("client/Shop/shop-grid-4cols");
//		dataLayoutMaster.setMainCss("css/style.min.css");
//		dataLayoutMaster.setJsList(Arrays.asList("/js/shop-grid/shop-grid.js"));
//		objsDataLayout.put("key", "value");
//		objsDataLayout.put("categoryId", id);
//		dataLayoutMaster.setObjs(objsDataLayout);
//
//		model.addAttribute(__CONTENT__, dataLayoutMaster);
//
//		return clientLayout;
//	}
}
