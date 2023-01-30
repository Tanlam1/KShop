CREATE DATABASE IF NOT EXISTS `kshop` ;
USE `kshop`;

CREATE TABLE IF NOT EXISTS `acl_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `display_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `guard_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `acl_permissions` (`id`, `name`, `display_name`, `guard_name`, `created_at`, `updated_at`) VALUES
	(1, 'backend-view', 'Xem chức năng Quản trị Hệ thống', 'web', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
	(2, 'suppliers-view', 'Xem chức năng Nhà cung cấp', 'web', '2022-09-12 16:29:29', NULL);

CREATE TABLE IF NOT EXISTS `acl_roles` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `display_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `guard_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `acl_roles` (`id`, `name`, `display_name`, `guard_name`, `created_at`, `updated_at`) VALUES
	(1, 'administrator', 'Quản trị Hệ thống', 'web', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
	(2, 'thukho', 'Thủ kho', 'web', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
	(3, 'ketoan', 'Kế toán', 'web', '2022-09-16 15:58:13', '2022-09-16 15:58:15'),
	(4, 'customer', 'Khách hàng', 'web', '2022-09-16 14:09:53', '2022-09-16 14:09:56');

CREATE TABLE IF NOT EXISTS `acl_role_has_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_acl_role_has_permissions_acl_permissions` (`permission_id`),
  KEY `FK_acl_role_has_permissions_acl_roles` (`role_id`),
  CONSTRAINT `FK_acl_role_has_permissions_acl_permissions` FOREIGN KEY (`permission_id`) REFERENCES `acl_permissions` (`id`),
  CONSTRAINT `FK_acl_role_has_permissions_acl_roles` FOREIGN KEY (`role_id`) REFERENCES `acl_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `acl_role_has_permissions` (`id`, `role_id`, `permission_id`) VALUES
	(1, 1, 1);

CREATE TABLE IF NOT EXISTS `acl_users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên đăng nhập',
  `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mật khẩu (mặc định: user@123)',
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Họ và tên lót',
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên',
  `gender` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT 'Giới tính: #0: Nam; #1: Nữ',
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Email',
  `birthday` datetime DEFAULT NULL COMMENT 'Ngày sinh',
  `avatar` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Hình đại diện',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mã CMND / CCCD',
  `job_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Chức danh',
  `department` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Phòng',
  `manager_id` bigint(20) unsigned DEFAULT NULL COMMENT 'Người phụ trách Quản lý',
  `phone` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Số điện thoại',
  `address1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Địa chỉ 1',
  `address2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Địa chỉ 2',
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Thành phố',
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Bang',
  `postal_code` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mã bưu chính',
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Quốc gia',
  `remember_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Token ghi nhớ Đăng nhập',
  `active_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mã kích hoạt',
  `status` tinyint(4) DEFAULT NULL COMMENT 'Trạng thái: #0: chưa kích hoạt; #1: đã kích hoạt',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `acl_users` (`id`, `username`, `password`, `last_name`, `first_name`, `gender`, `email`, `birthday`, `avatar`, `code`, `job_title`, `department`, `manager_id`, `phone`, `address1`, `address2`, `city`, `state`, `postal_code`, `country`, `remember_token`, `active_code`, `status`, `created_at`, `updated_at`) VALUES
	(1, 'hhphu09', 'fd1dfea9828fd9825120a66fccb55097', 'Hồ Hoàng', 'Phú', 0, 'hhphups17588@fpt.edu.vn', NULL, 'avatars/null.jpg', NULL, 'Developer', 'IT Software', NULL, '0123-456-789', 'Địa chỉ', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, NULL, '2022-09-16 16:16:00', NULL);

CREATE TABLE IF NOT EXISTS `acl_user_has_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_acl_model_has_permissions_acl_permissions` (`permission_id`),
  KEY `FK_acl_user_has_permissions_acl_users` (`user_id`),
  CONSTRAINT `FK_acl_model_has_permissions_acl_permissions` FOREIGN KEY (`permission_id`) REFERENCES `acl_permissions` (`id`),
  CONSTRAINT `FK_acl_user_has_permissions_acl_users` FOREIGN KEY (`user_id`) REFERENCES `acl_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `acl_user_has_roles` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_acl_model_has_roles_acl_roles` (`role_id`),
  KEY `FK_acl_user_has_roles_acl_users` (`user_id`),
  CONSTRAINT `FK_acl_model_has_roles_acl_roles` FOREIGN KEY (`role_id`) REFERENCES `acl_roles` (`id`),
  CONSTRAINT `FK_acl_user_has_roles_acl_users` FOREIGN KEY (`user_id`) REFERENCES `acl_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `shop_categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã chuyên mục',
  `category_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên chuyên mục',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Diễn giải',
  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_code` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_categories` (`id`, `category_code`, `category_name`, `description`, `image`, `created_at`, `updated_at`) VALUES
	(1, 'CAT1', 'Laptop', 'All laptop products', 'categories/latops.jpg', NULL, NULL),
	(2, 'CAT2', 'Phone', 'All phones', 'categories/phones.jpg', NULL, NULL),
	(3, 'CAT3', 'Camera', 'Photograph Camera', 'categories/cameras.jpg', NULL, NULL),
	(4, 'CAT4', 'Tablet', 'Tablet', 'categories/tablets.jpg', NULL, NULL);

CREATE TABLE IF NOT EXISTS `shop_customers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên đăng nhập',
  `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mật khẩu (mặc định: customer@123)',
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Họ và tên lót',
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'Giới tính: #0: Nam; #1: Nữ',
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Email',
  `birthday` datetime DEFAULT NULL COMMENT 'Ngày sinh',
  `avatar` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã CMND / CCCD',
  `company` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Công ty',
  `phone` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Số điện thoại',
  `billing_address` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Địa chỉ Thanh toán',
  `shipping_address` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Địa chỉ Giao hàng',
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Thành phố',
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Bang',
  `postal_code` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mã bưu chính',
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Quốc gia',
  `remember_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Token ghi nhớ đăng nhập',
  `activate_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mã kích hoạt',
  `status` tinyint(4) DEFAULT NULL COMMENT 'Trạng thái: #0: chưa kích hoạt; #1: đã kích hoạt',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_customers` (`id`, `username`, `password`, `last_name`, `first_name`, `gender`, `email`, `birthday`, `avatar`, `code`, `company`, `phone`, `billing_address`, `shipping_address`, `city`, `state`, `postal_code`, `country`, `remember_token`, `activate_code`, `status`, `created_at`, `updated_at`) VALUES
	(1, 'kodoku', 'dae9e9c1f4ce3b7289bf4114cde40bdbbd339f03', 'Hồ Hoàng', 'Phú', 0, 'kodoku@gmail.com', '1989-06-11', 'customers/avatars/avt.jpg', '', 'Cong ty', '0123-456-789', 'Địa chỉ 0', 'Địa chỉ', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30');

CREATE TABLE IF NOT EXISTS `shop_vouchers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `voucher_code` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã voucher',
  `voucher_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên voucher',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mô tả voucher',
  `uses` int(10) unsigned NOT NULL DEFAULT 0 COMMENT 'Số lượng voucher đã sử dụng',
  `max_uses` int(10) unsigned NOT NULL DEFAULT 0 COMMENT 'Số lượng tối đa voucher có thể sử dụng. Nếu #0 là không xác định',
  `max_uses_user` int(10) unsigned NOT NULL DEFAULT 0 COMMENT 'Người dùng có thể sử dụng voucher này bao nhiêu lần? Nếu #0 là không xác định',
  `type` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT 'Loại voucher: #1: voucher, #2: coupon',
  `discount_amount` double unsigned NOT NULL DEFAULT 0 COMMENT '% giảm giá hoặc số tiền giảm giá cụ thể',
  `is_fixed` bit(1) NOT NULL DEFAULT b'1' COMMENT '#True: giảm giá theo số tiền cụ thể; #False: giảm giá theo %',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày bắt đầu voucher',
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày kết thúc voucher',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `voucher_code` (`voucher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='Table lưu thông tin Danh sách các phiếu Khuyến mãi, Giảm giá. Thông thường:\r\n1. Voucher (phiếu khuyến mãi): giảm theo số tiền cụ thể\r\n2. Coupon (phiếu giảm giá %): thường giảm theo % giá trị đơn hàng\r\n';

INSERT INTO `shop_vouchers` (`id`, `voucher_code`, `voucher_name`, `description`, `uses`, `max_uses`, `max_uses_user`, `type`, `discount_amount`, `is_fixed`, `start_date`, `end_date`, `created_at`, `updated_at`, `deleted_at`) VALUES
  (1, 'VOU1', 'VOUCHER1', 'Voucher ', 0, 10, 1, 1, 500000, b'1', '2022-09-01 00:00:00', '2022-05-31 23:59:59', '2022-09-16 15:07:31', NULL, NULL),
  (2, 'COUP1', 'COUPON1', 'Coupon', 0, 20, 1, 2, 5, b'0', '2022-09-01 00:00:00', '2022-03-31 23:59:59', '2022-09-16 15:10:54', NULL, NULL);

CREATE TABLE IF NOT EXISTS `shop_customer_vouchers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) unsigned NOT NULL COMMENT 'Khách hàng nào?',
  `voucher_id` bigint(20) unsigned NOT NULL COMMENT 'Voucher nào áp dụng?',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_shop_customer_vouchers_shop_customers` (`customer_id`),
  KEY `FK_shop_customer_vouchers_shop_vouchers` (`voucher_id`),
  CONSTRAINT `FK_shop_customer_vouchers_shop_customers` FOREIGN KEY (`customer_id`) REFERENCES `shop_customers` (`id`),
  CONSTRAINT `FK_shop_customer_vouchers_shop_vouchers` FOREIGN KEY (`voucher_id`) REFERENCES `shop_vouchers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table lưu thông tin Khách hàng nào được áp dụng Voucher nào?\r\nCó thể lập danh sách Khách hàng bằng cách:\r\n- Tạo chức năng tự động tạo Voucher cho Khách hàng thân thiết (mua nhiều hàng hóa trong năm, ...) khi đến sinh nhật của họ.\r\n- Người thân của Giám đốc ;)';

INSERT INTO `shop_customer_vouchers` (`id`, `customer_id`, `voucher_id`, `created_at`, `updated_at`) VALUES
	(1, 1, 2, '2022-09-16 16:08:01', NULL);

CREATE TABLE IF NOT EXISTS `shop_stores` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `store_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã kho',
  `store_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên kho',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Diễn giải',
  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `category_code` (`store_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS `shop_exports` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) unsigned NOT NULL COMMENT 'Xuất từ kho hàng nào?',
  `employee_id` bigint(20) unsigned NOT NULL COMMENT 'Nhân viên nào lập phiếu xuất?',
  `export_date` datetime NOT NULL COMMENT 'Ngày Xuất kho',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_shop_exports_acl_users` (`employee_id`) USING BTREE,
  KEY `FK_shop_exports_shop_stores` (`store_id`) USING BTREE,
  CONSTRAINT `FK_shop_exports_acl_users` FOREIGN KEY (`employee_id`) REFERENCES `acl_users` (`id`),
  CONSTRAINT `FK_shop_exports_shop_stores` FOREIGN KEY (`store_id`) REFERENCES `shop_stores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `shop_suppliers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `supplier_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã nhà cung cấp',
  `supplier_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên nhà cung cấp',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Diễn giải',
  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_code` (`supplier_code`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

INSERT INTO `shop_suppliers` (`id`, `supplier_code`, `supplier_name`, `description`, `image`, `created_at`, `updated_at`) VALUES
  (1, 'SUP1', 'Apple', 'Apple', 'suppliers/apple.jpg', '2022-09-16 16:08:55', NULL),
  (2, 'SUP2', 'Dell', 'Dell', 'suppliers/dell.jpg', '2022-09-16 16:08:55', NULL),
  (3, 'SUP3', 'Microsoft', 'Microsoft', 'suppliers/microsoft.jpg', '2022-09-16 16:08:54', NULL),
  (4, 'SUP4', 'Canon', 'Canon', 'suppliers/canon.jpg', '2022-09-16 16:08:54', NULL),
  (5, 'SUP5', 'Nikkon', 'Nikkon', 'suppliers/nikkon.jpg', '2022-09-16 16:08:54', NULL),
  (6, 'SUP6', 'Google', 'Google', 'suppliers/google.jpg', '2022-09-16 16:08:53', NULL),
  (7, 'SUP7', 'ThinkPad', 'ThinkPad', 'suppliers/thinkpad.jpg', '2022-09-16 16:08:53', NULL),
  (8, 'SUP8', 'Samsung', 'Samsung', 'suppliers/samsung.jpg', '2022-09-16 16:08:52', NULL),
  (9, 'SUP9', 'Moto', 'Moto', 'suppliers/moto.jpg', '2022-09-16 16:08:51', NULL),
  (10, 'SUP10', 'Nokia', 'Nokia', 'suppliers/nokia.jpg', '2022-09-16 16:08:51', NULL),
  (11, 'SUP11', 'BlackBerry', 'BlackBerry', 'suppliers/black_berry.jpg', '2022-09-16 16:08:51', NULL),
  (12, 'SUP12', 'BPhone', 'Điện thoại BPhone', 'suppliers/bphone.jpg', '2022-08-08 12:38:00', NULL),
  (13, 'SUP13', 'VinPhone', 'Điện thoại VinPhone', 'suppliers/vinphone.jpg', '2022-08-08 12:45:00', NULL);

CREATE TABLE IF NOT EXISTS `shop_products` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_code` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã sản phẩm',
  `product_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên sản phẩm',
  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `short_description` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mô tả ngắn',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Mô tả',
  `standard_cost` decimal(19,4) NOT NULL DEFAULT 0.0000 COMMENT 'Giá nhập',
  `list_price` decimal(19,4) NOT NULL DEFAULT 0.0000 COMMENT 'Giá niêm yết',
  `quantity_per_unit` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Số lượng trên mỗi đơn vị',
  `discontinued` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'Ngưng bán?',
  `is_featured` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Có phải là Sản phẩm nổi bật không?',
  `is_new` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Có phải là Sản phẩm mới nhất không?',
  `category_id` bigint(20) unsigned DEFAULT NULL COMMENT 'Thuộc chuyên mục',
  `supplier_id` bigint(20) unsigned DEFAULT NULL COMMENT 'Thuộc nhà cung cấp',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_products_categories` (`category_id`),
  KEY `FK_products_suppliers` (`supplier_id`),
  CONSTRAINT `FK_products_categories` FOREIGN KEY (`category_id`) REFERENCES `shop_categories` (`id`),
  CONSTRAINT `FK_products_suppliers` FOREIGN KEY (`supplier_id`) REFERENCES `shop_suppliers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_products` (`id`, `product_code`, `product_name`, `image`, `short_description`, `description`, `standard_cost`, `list_price`, `quantity_per_unit`, `discontinued`, `is_featured`, `is_new`, `category_id`, `supplier_id`, `created_at`, `updated_at`) VALUES
  (1, 'P1', 'Nikkon DS90', 'products/nikkon_ds60.jpg', 'Nikkon DS90 desc', 'Nikkon DS90 desc', 6709000.0000, 2339000.0000, '50', 0, b'1', b'0', 3, 5, NULL, NULL),
  (2, 'P2', 'Canon T90', 'products/canon_t90.jpg', 'Canon T90 desc', 'Canon T90 desc', 8283000.0000, 9384000.0000, '56', 0, b'0', b'0', 3, 4, NULL, NULL),
  (3, 'P3', 'Dell Inspirion', 'products/dell_inspirion.jpg', 'Dell Inspirion desc', 'Dell Inspirion desc', 8283000.0000, 9384000.0000, '56', 0, b'0', b'0', 1, 2, NULL, NULL),
  (4, 'P4', 'iPad Air', 'products/ipad_air.jpg', 'iPad Air desc', 'iPad Air desc', 8283000.0000, 9384000.0000, '56', 0, b'1', b'1', 4, 1, NULL, NULL),
  (5, 'P5', 'Microsft Surface', 'products/microsoft_surface.jpg', 'Microsft Surface desc', 'Microsft Surface desc', 8283000.0000, 9384000.0000, '56', 0, b'1', b'1', 4, 3, NULL, NULL),
  (6, 'P6', 'Nexus 6', 'products/nexus_6.jpg', 'Nexus 6 desc', 'Nexus 6 desc', 3388000.0000, 1170000.0000, '79', 0, b'0', b'0', 2, 6, NULL, NULL),
  (7, 'P7', 'ThinkPad T365', 'products/thinkpad_t365.jpg', 'ThinkPad T365 desc', 'ThinkPad T365 desc', 4102000.0000, 898000.0000, '92', 1, b'0', b'0', 1, 7, NULL, NULL),
  (8, 'P8', 'Moto Play', 'products/moto_play.jpg', 'Moto Play desc', 'Moto Play desc', 3844000.0000, 6234000.0000, '54', 1, b'1', b'0', 2, 9, NULL, NULL),
  (9, 'P9', 'Samsung Note', 'products/samsung_note.jpg', 'Samsung Note desc', 'Samsung Note desc', 4758000.0000, 8183000.0000, '58', 0, b'0', b'1', 4, 8, NULL, NULL),
  (10, 'P10', 'MacBook Pro', 'products/macbook_pro.jpg', 'MacBook Pro desc', 'MacBook Pro desc', 2581000.0000, 7661000.0000, '11', 0, b'1', b'1', 1, 1, NULL, NULL);

CREATE TABLE IF NOT EXISTS `shop_export_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `export_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc phiếu Xuất nào?',
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Sản phẩm nào xuất đi?',
  `quantity` decimal(18,4) NOT NULL DEFAULT 0.0000 COMMENT 'Số lượng xuất',
  `unit_price` decimal(19,4) NOT NULL DEFAULT 0.0000 COMMENT 'Đơn giá xuất',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_shop_export_details_shop_exports` (`export_id`) USING BTREE,
  KEY `FK_shop_export_details_shop_products` (`product_id`) USING BTREE,
  CONSTRAINT `FK_shop_export_details_shop_exports` FOREIGN KEY (`export_id`) REFERENCES `shop_exports` (`id`),
  CONSTRAINT `FK_shop_export_details_shop_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `shop_imports` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc kho hàng nào?',
  `employee_id` bigint(20) unsigned NOT NULL COMMENT 'Nhân viên nào lập phiếu nhập?',
  `import_date` datetime NOT NULL COMMENT 'Ngày Nhập kho',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_shop_imports_acl_users` (`employee_id`),
  KEY `FK_shop_imports_shop_stores` (`store_id`),
  CONSTRAINT `FK_shop_imports_acl_users` FOREIGN KEY (`employee_id`) REFERENCES `acl_users` (`id`),
  CONSTRAINT `FK_shop_imports_shop_stores` FOREIGN KEY (`store_id`) REFERENCES `shop_stores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `shop_import_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `import_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc phiếu Nhập nào?',
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Sản phẩm nào nhập vào?',
  `quantity` decimal(18,4) NOT NULL DEFAULT 0.0000 COMMENT 'Số lượng nhập',
  `unit_price` decimal(19,4) NOT NULL DEFAULT 0.0000 COMMENT 'Đơn giá nhập',
  PRIMARY KEY (`id`),
  KEY `FK_shop_import_details_shop_imports` (`import_id`),
  KEY `FK_shop_import_details_shop_products` (`product_id`),
  CONSTRAINT `FK_shop_import_details_shop_imports` FOREIGN KEY (`import_id`) REFERENCES `shop_imports` (`id`),
  CONSTRAINT `FK_shop_import_details_shop_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `shop_payment_types` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payment_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Mã hình thức thanh toán',
  `payment_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên hình thức thanh toán',
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Diễn giải',
  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ảnh đại diện',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_code` (`payment_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

INSERT INTO `shop_payment_types` (`id`, `payment_code`, `payment_name`, `description`, `image`, `created_at`, `updated_at`) VALUES
  (1, 'CK', 'Chuyển khoản', NULL, NULL, '2022-09-24 00:33:13', '2022-09-24 00:33:13'),
  (2, 'Paypal', 'Thanh toán Paypal', NULL, NULL, '2022-09-24 00:33:37', NULL),
  (3, 'NHTT', 'Thanh toán sau khi nhận hàng', NULL, NULL, '2022-09-24 00:33:58', NULL),
  (4, 'TM', 'Tiền mặt', NULL, NULL, '2022-09-24 00:37:05', NULL);

CREATE TABLE IF NOT EXISTS `shop_orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc nhân viên nào xử lý',
  `customer_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc khách hàng',
  `order_date` datetime NOT NULL COMMENT 'Ngày tạo Đơn hàng',
  `shipped_date` datetime DEFAULT NULL COMMENT 'Ngày giao hàng',
  `ship_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Họ tên Người giao hàng',
  `ship_address1` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Giao hàng đến Địa chỉ 1',
  `ship_address2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Giao hàng đến Địa chỉ 2',
  `ship_city` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Giao hàng đến Thành phố',
  `ship_state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Giao hàng đến Bang',
  `ship_postal_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Giao hàng đến Mã bưu chính',
  `ship_country` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Giao hàng đến Quốc gia',
  `shipping_fee` decimal(19,4) NOT NULL DEFAULT 0.0000 COMMENT 'Phí giao hàng',
  `payment_type_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'Phương thức Thanh toán',
  `paid_date` datetime DEFAULT NULL COMMENT 'Ngày thanh toán',
  `order_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Trạng thái: #New: mới tạo Đơn hàng -> #On Hold: đang xử lý -> #Shipped: đã giao hàng -> #Complete: đã hoàn tất đơn hàng (khách đã thanh toán và nhận hàng xong)',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_employees` (`employee_id`),
  KEY `FK_orders_customers` (`customer_id`),
  KEY `FK_shop_orders_shop_payment_types` (`payment_type_id`),
  CONSTRAINT `FK_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `shop_customers` (`id`),
  CONSTRAINT `FK_orders_employees` FOREIGN KEY (`employee_id`) REFERENCES `acl_users` (`id`),
  CONSTRAINT `FK_shop_orders_shop_payment_types` FOREIGN KEY (`payment_type_id`) REFERENCES `shop_payment_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_orders` (`id`, `employee_id`, `customer_id`, `order_date`, `shipped_date`, `ship_name`, `ship_address1`, `ship_address2`, `ship_city`, `ship_state`, `ship_postal_code`, `ship_country`, `shipping_fee`, `payment_type_id`, `paid_date`, `order_status`, `created_at`, `updated_at`) VALUES
	(1, 1, 1, '2016-04-05 00:00:00', '2016-11-06 00:00:00', 'Jean Fuller', '93 Spohn Place', NULL, 'Manggekompo', NULL, NULL, 'Indonesia', 8.1400, 1, '2016-10-12 00:00:00', 'On Hold', NULL, NULL),
	(2, 1, 1, '2017-01-29 00:00:00', '2016-05-28 00:00:00', 'Diane Holmes', '46 Eliot Trail', NULL, 'Virginia Beach', 'Virginia', '23459', 'United States', 1.5500, 3, '2016-06-27 00:00:00', 'Shipped', NULL, NULL),
	(3, 1, 1, '2016-08-19 00:00:00', '2016-12-08 00:00:00', 'Jerry Frazier', '23 Sundown Junction', NULL, 'Obodivka', NULL, NULL, 'Ukraine', 2.2900, 4, '2016-09-27 00:00:00', 'On Hold', NULL, NULL),
	(4, 1, 1, '2016-09-25 00:00:00', '2016-12-24 00:00:00', 'Denise Freeman', '4909 Beilfuss Hill', NULL, 'Nova Venécia', NULL, '29830-000', 'Brazil', 4.7700, 3, '2016-07-04 00:00:00', 'New', NULL, NULL);

CREATE TABLE IF NOT EXISTS `shop_order_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `quantity` decimal(18,4) NOT NULL DEFAULT 0.0000,
  `unit_price` decimal(19,4) NOT NULL DEFAULT 0.0000,
  `discount_percentage` float NOT NULL DEFAULT 0,
  `discount_amout` double NOT NULL DEFAULT 0,
  `order_detail_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_allocated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_shop_order_details_shop_orders` (`order_id`),
  KEY `FK_shop_order_details_shop_products` (`product_id`),
  CONSTRAINT `FK_shop_order_details_shop_orders` FOREIGN KEY (`order_id`) REFERENCES `shop_orders` (`id`),
  CONSTRAINT `FK_shop_order_details_shop_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_order_details` (`id`, `order_id`, `product_id`, `quantity`, `unit_price`, `discount_percentage`, `discount_amout`, `order_detail_status`, `date_allocated`) VALUES
	(1, 1, 5, 1.0000, 6234000.0000, 8.73, 0, 'Allocated', '2017-01-15 00:00:00'),
	(2, 1, 6, 2.0000, 8183000.0000, 4.36, 0, 'No Stock', '2016-09-21 00:00:00'),
	(3, 2, 3, 3.0000, 9384000.0000, 2.86, 0, 'On Order', '2016-12-15 00:00:00'),
	(4, 2, 7, 4.0000, 7661000.0000, 8.65, 0, 'Allocated', '2016-10-12 00:00:00'),
	(5, 3, 2, 7.0000, 9384000.0000, 3.54, 0, 'No Stock', '2017-02-09 00:00:00'),
	(6, 3, 6, 8.0000, 8183000.0000, 4.01, 0, 'No Stock', '2016-06-15 00:00:00'),
	(8, 4, 1, 9.0000, 2339000.0000, 4.48, 0, 'Allocated', '2016-10-18 00:00:00'),
	(9, 4, 4, 6.0000, 9384000.0000, 5.68, 0, 'No Stock', '2017-02-13 00:00:00'),
	(10, 4, 7, 6.0000, 7661000.0000, 5, 0, 'No Stock', '2016-09-16 00:00:00');

CREATE TABLE IF NOT EXISTS `shop_product_discounts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc sản phẩm',
  `discount_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tên Sự kiện Giảm giá',
  `discount_amount` double NOT NULL DEFAULT 0 COMMENT '% giảm giá hoặc số tiền giảm giá cụ thể',
  `is_fixed` bit(1) NOT NULL DEFAULT b'0' COMMENT '#True 1: giảm giá theo số tiền cụ thể; #False 0: giảm giá theo %',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_product_images_products` (`product_id`),
  CONSTRAINT `shop_product_discounts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='Table chứa thông tin các sản phẩm đang được khuyến mãi trong khoảng thời gian (từ ngày - đến ngày hết hạn).\r\nThường dùng cho các chức năng:\r\n- Hiển thị danh sách Sản phẩm với Giá tiền cũ và Giá tiền mới.\r\n- Hiển thị danh sách Sản phẩm với % được giảm giá...';

INSERT INTO `shop_product_discounts` (`id`, `product_id`, `discount_name`, `discount_amount`, `is_fixed`, `start_date`, `end_date`) VALUES
	(1, 1, 'Giảm giá dịp lễ Vua Hùng năm 2022', 10, b'0', '2022-09-01 00:00:00', '2022-03-31 23:59:59'),
	(2, 2, 'Giảm giá dịp lễ 08/03 năm 2022', 15, b'0', '2022-03-01 00:00:00', '2022-03-08 23:59:59');

CREATE TABLE IF NOT EXISTS `shop_product_images` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Thuộc sản phẩm',
  `image` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Ảnh',
  PRIMARY KEY (`id`),
  KEY `FK_product_images_products` (`product_id`),
  CONSTRAINT `FK_product_images_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_product_images` (`id`, `product_id`, `image`) VALUES
	(1, 1, 'products/images_product_1_1.jpg'),
	(2, 1, 'products/images_product_1_2.jpg'),
	(3, 1, 'products/images_product_1_3.jpg'),
	(4, 2, 'products/images_product_2_1.jpg');

CREATE TABLE IF NOT EXISTS `shop_product_reviews` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Sản phẩm Đánh giá',
  `customer_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'Khách đánh giá',
  `rating` float NOT NULL COMMENT 'Số điểm đánh giá: từ 0 -> 5 sao',
  `comment` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nội dung Đánh giá',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_reviews_products` (`product_id`),
  KEY `FK_shop_product_reviews_shop_customers` (`customer_id`),
  CONSTRAINT `FK_product_reviews_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`),
  CONSTRAINT `FK_shop_product_reviews_shop_customers` FOREIGN KEY (`customer_id`) REFERENCES `shop_customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `shop_product_reviews` (`id`, `product_id`, `customer_id`, `rating`, `comment`, `created_at`, `updated_at`) VALUES
	(1, 1, 1, 3, 'Chụp ảnh tốt, hình vi diệu... Có điều giá hơi chát, 4s ;P', '2022-09-16 16:03:05', NULL),
	(2, 1, 1, 5, 'Sản phẩm mua mới cách đây 2 tháng, chưa thấy lỗi gì. Đóng gói khá cẩn thận, tốt.', '2022-09-16 16:05:46', NULL),
	(3, 4, 1, 1, 'Mới mua về, đang khui hộp lỡ tay làm rớt có 1 cái, hư luôn, tệ hết sức, không bảo hành luôn. Cho 1 sao vì số t xui :(', '2022-09-16 16:06:35', NULL),
	(4, 5, 1, 3, 'Có vẻ tốt, đợi thời gian nữa xem sao :V', '2022-09-16 18:26:57', NULL),
	(5, 4, 1, 5, 'Tuyệt vời, mình mua được ngay lúc giảm giá còn 20%. Xài thoải mái, xứng đáng giá tiền sau khi giảm giá ;P', '2022-09-16 19:42:06', NULL);

CREATE TABLE IF NOT EXISTS `shop_product_vouchers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) unsigned NOT NULL COMMENT 'Sản phẩm nào?',
  `voucher_id` bigint(20) unsigned NOT NULL COMMENT 'Voucher nào áp dụng?',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_shop_product_vouchers_shop_products` (`product_id`),
  KEY `FK_shop_product_vouchers_shop_vouchers` (`voucher_id`),
  CONSTRAINT `FK_shop_product_vouchers_shop_products` FOREIGN KEY (`product_id`) REFERENCES `shop_products` (`id`),
  CONSTRAINT `FK_shop_product_vouchers_shop_vouchers` FOREIGN KEY (`voucher_id`) REFERENCES `shop_vouchers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='Table lưu thông tin Sản phẩm nào được áp dụng Voucher nào?';

INSERT INTO `shop_product_vouchers` (`id`, `product_id`, `voucher_id`, `created_at`, `updated_at`) VALUES
	(1, 1, 1, '2022-09-16 16:07:38', NULL),
	(2, 5, 1, '2022-09-16 16:07:46', NULL);