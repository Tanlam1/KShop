Use master;
Go

IF DB_ID('kshop_main') IS NOT NULL
	Drop Database kshop_main;
Go 

CREATE DATABASE kshop_main;
Go

USE kshop_main;
Go

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_permissions (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [name] nvarchar(500) NOT NULL,
  [display_name] nvarchar(500) DEFAULT NULL,
  [guard_name] nvarchar(500) NOT NULL,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO acl_permissions VALUES
(N'backend-view', N'Xem chức năng Quản trị Hệ thống', N'we', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
(N'suppliers-view', N'Xem chức năng Nhà cung cấp', N'we', '2022-09-12 16:29:29', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_roles (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [name] nvarchar(500) NOT NULL,
  [display_name] nvarchar(500) DEFAULT NULL,
  [guard_name] nvarchar(500) NOT NULL,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
)   ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO acl_roles VALUES
('administrator', N'Quản trị Hệ thống', 'we', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
('thukho', N'Thủ kho', 'we', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
('ketoan', N'Kế toán', 'we', '2022-09-16 15:58:13', '2022-09-16 15:58:15'),
('customer', N'Khách hàng', 'we', '2022-09-16 14:09:53', '2022-09-16 14:09:56');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_role_has_permissions (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [role_id] bigint check ([role_id] > 0) NOT NULL,
  [permission_id] bigint check ([permission_id] > 0) NOT NULL,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_acl_role_has_permissions_acl_permissions] FOREIGN KEY ([permission_id]) REFERENCES acl_permissions ([id]),
  CONSTRAINT [FK_acl_role_has_permissions_acl_roles] FOREIGN KEY ([role_id]) REFERENCES acl_roles ([id])
)   ;

CREATE INDEX [FK_acl_role_has_permissions_acl_permissions] ON acl_role_has_permissions ([permission_id]);
CREATE INDEX [FK_acl_role_has_permissions_acl_roles] ON acl_role_has_permissions ([role_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO acl_role_has_permissions VALUES
(1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_users (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [username] nvarchar(191) NOT NULL ,
  [password] nvarchar(500) NOT NULL ,
  [last_name] nvarchar(255) NOT NULL ,
  [first_name] nvarchar(255) NOT NULL ,
  [gender] bit NOT NULL DEFAULT 0 ,
  [email] nvarchar(191) NOT NULL ,
  [birthday] datetime DEFAULT NULL ,
  [avatar] nvarchar(500) DEFAULT NULL ,
  [code] nvarchar(255) DEFAULT NULL ,
  [job_title] nvarchar(255) DEFAULT NULL ,
  [department] nvarchar(255) DEFAULT NULL ,
  [manager_id] bigint check ([manager_id] > 0) DEFAULT NULL ,
  [phone] nvarchar(25) DEFAULT NULL ,
  [address1] nvarchar(500) DEFAULT NULL ,
  [address2] nvarchar(500) DEFAULT NULL ,
  [city] nvarchar(255) DEFAULT NULL ,
  [state] nvarchar(255) DEFAULT NULL ,
  [postal_code] nvarchar(15) DEFAULT NULL ,
  [country] nvarchar(255) DEFAULT NULL ,
  [remember_token] nvarchar(255) DEFAULT NULL ,
  [active_code] nvarchar(255) DEFAULT NULL ,
  [status] bit DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [username] UNIQUE  ([username]),
  CONSTRAINT [email] UNIQUE  ([email])
)   ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO acl_users VALUES
('hhphu09', 'fd1dfea9828fd9825120a66fccb55097', N'Hồ Hoàng', N'Phú', 0, 'hhphups17588@fpt.edu.vn', NULL, 'avatars/null.jpg', NULL, 'Developer', 'IT Software', NULL, '0123-456-789', N'Địa chỉ', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_user_has_permissions (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [user_id] bigint check ([user_id] > 0) NOT NULL,
  [permission_id] bigint check ([permission_id] > 0) NOT NULL,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_acl_model_has_permissions_acl_permissions] FOREIGN KEY ([permission_id]) REFERENCES acl_permissions ([id]),
  CONSTRAINT [FK_acl_user_has_permissions_acl_users] FOREIGN KEY ([user_id]) REFERENCES acl_users ([id])
)   ;

CREATE INDEX [FK_acl_model_has_permissions_acl_permissions] ON acl_user_has_permissions ([permission_id]);
CREATE INDEX [FK_acl_user_has_permissions_acl_users] ON acl_user_has_permissions ([user_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE acl_user_has_roles (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [user_id] bigint check ([user_id] > 0) NOT NULL,
  [role_id] bigint check ([role_id] > 0) NOT NULL,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_acl_model_has_roles_acl_roles] FOREIGN KEY ([role_id]) REFERENCES acl_roles ([id]),
  CONSTRAINT [FK_acl_user_has_roles_acl_users] FOREIGN KEY ([user_id]) REFERENCES acl_users ([id])
)   ;

CREATE INDEX [FK_acl_model_has_roles_acl_roles] ON acl_user_has_roles ([role_id]);
CREATE INDEX [FK_acl_user_has_roles_acl_users] ON acl_user_has_roles ([user_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_categories (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [category_code] nvarchar(50) NOT NULL ,
  [category_name] nvarchar(500) NOT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [image] nvarchar(4000) DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [category_code] UNIQUE  ([category_code])
)   ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_categories VALUES
('CAT1', 'Laptop', 'All laptop products', 'categories/latops.jpg', NULL, NULL),
('CAT2', 'Phone', 'All phones', 'categories/phones.jpg', NULL, NULL),
('CAT3', 'Camera', 'Photograph Camera', 'categories/cameras.jpg', NULL, NULL),
('CAT4', 'Tablet', 'Tablet', 'categories/tablets.jpg', NULL, NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_customers (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [username] nvarchar(191) NOT NULL ,
  [password] nvarchar(500) NOT NULL ,
  [last_name] nvarchar(255) NOT NULL ,
  [first_name] nvarchar(255) NOT NULL ,
  [gender] bit NOT NULL DEFAULT 0 ,
  [email] nvarchar(191) NOT NULL ,
  [birthday] datetime DEFAULT NULL ,
  [avatar] nvarchar(500) DEFAULT NULL ,
  [code] nvarchar(255) NOT NULL ,
  [company] nvarchar(255) DEFAULT NULL ,
  [phone] nvarchar(25) DEFAULT NULL ,
  [billing_address] nvarchar(500) DEFAULT NULL ,
  [shipping_address] nvarchar(500) DEFAULT NULL ,
  [city] nvarchar(255) DEFAULT NULL ,
  [state] nvarchar(255) DEFAULT NULL ,
  [postal_code] nvarchar(15) DEFAULT NULL ,
  [country] nvarchar(255) DEFAULT NULL ,
  [remember_token] nvarchar(255) DEFAULT NULL ,
  [activate_code] nvarchar(255) DEFAULT NULL ,
  [status] bit DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [username_customer] UNIQUE  ([username]),
  CONSTRAINT [email_customer] UNIQUE  ([email])
)   ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_customers VALUES
('kodoku', 'dae9e9c1f4ce3b7289bf4114cde40bdbbd339f03', N'Hồ Hoàng', N'Phú', 0, 'kodoku@gmail.com', '1989-06-11', 'customers/avatars/avt.jpg', '', 'Cong ty', '0123-456-789', N'Địa chỉ 0', N'Địa chỉ', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_vouchers (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [voucher_code] nvarchar(500) NOT NULL ,
  [voucher_name] nvarchar(500) NOT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [uses] int check ([uses] > 0) NOT NULL DEFAULT 0 ,
  [max_uses] int check ([max_uses] > 0) NOT NULL DEFAULT 0 ,
  [max_uses_user] int check ([max_uses_user] > 0) NOT NULL DEFAULT 0 ,
  [type] bit check ([type] > 0) NOT NULL DEFAULT 1 ,
  [discount_amount] float check ([discount_amount] > 0) NOT NULL DEFAULT 0 ,
  [is_fixed] bit NOT NULL DEFAULT '1' ,
  [start_date] datetime NOT NULL DEFAULT GETDATE() ,
  [end_date] datetime NOT NULL DEFAULT GETDATE() ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  [deleted_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [voucher_code] UNIQUE  ([voucher_code])
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_vouchers VALUES
('VOU1', 'VOUCHER1', 'Voucher ', 100, 10, 1, 1, 500000, '1', '2022-09-01 00:00:00', '2022-05-31 23:59:59', '2022-09-16 15:07:31', NULL, NULL),
('COUP1', 'COUPON1', 'Coupon', 100, 20, 1, 2, 5, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59', '2022-09-16 15:10:54', NULL, NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_customer_vouchers (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [customer_id] bigint check ([customer_id] > 0) NOT NULL ,
  [voucher_id] bigint check ([voucher_id] > 0) NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_customer_vouchers_shop_customers] FOREIGN KEY ([customer_id]) REFERENCES shop_customers ([id]),
  CONSTRAINT [FK_shop_customer_vouchers_shop_vouchers] FOREIGN KEY ([voucher_id]) REFERENCES shop_vouchers ([id])
)    ;

CREATE INDEX [FK_shop_customer_vouchers_shop_customers] ON shop_customer_vouchers ([customer_id]);
CREATE INDEX [FK_shop_customer_vouchers_shop_vouchers] ON shop_customer_vouchers ([voucher_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_customer_vouchers VALUES
(1, 2, '2022-09-16 16:08:01', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_stores (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [store_code] nvarchar(50) NOT NULL ,
  [store_name] nvarchar(500) NOT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [image] nvarchar(4000) DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [category_store_code] UNIQUE ([store_code])
)    ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_exports (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [store_id] bigint check ([store_id] > 0) NOT NULL ,
  [employee_id] bigint check ([employee_id] > 0) NOT NULL ,
  [export_date] datetime NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_exports_acl_users] FOREIGN KEY ([employee_id]) REFERENCES acl_users ([id]),
  CONSTRAINT [FK_shop_exports_shop_stores] FOREIGN KEY ([store_id]) REFERENCES shop_stores ([id])
)    ;

CREATE INDEX [FK_shop_exports_acl_users] ON shop_exports ([employee_id]);
CREATE INDEX [FK_shop_exports_shop_stores] ON shop_exports ([store_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_suppliers (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [supplier_code] nvarchar(50) NOT NULL ,
  [supplier_name] nvarchar(500) NOT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [image] nvarchar(4000) DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [category_suppliers_code] UNIQUE  ([supplier_code])
)    ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_suppliers VALUES
('SUP1', 'Apple', 'Apple', 'suppliers/apple.jpg', '2022-09-16 16:08:55', NULL),
('SUP2', 'Dell', 'Dell', 'suppliers/dell.jpg', '2022-09-16 16:08:55', NULL),
('SUP3', 'Microsoft', 'Microsoft', 'suppliers/microsoft.jpg', '2022-09-16 16:08:54', NULL),
('SUP4', 'Canon', 'Canon', 'suppliers/canon.jpg', '2022-09-16 16:08:54', NULL),
('SUP5', 'Nikkon', 'Nikkon', 'suppliers/nikkon.jpg', '2022-09-16 16:08:54', NULL),
('SUP6', 'Google', 'Google', 'suppliers/google.jpg', '2022-09-16 16:08:53', NULL),
('SUP7', 'ThinkPad', 'ThinkPad', 'suppliers/thinkpad.jpg', '2022-09-16 16:08:53', NULL),
('SUP8', 'Samsung', 'Samsung', 'suppliers/samsung.jpg', '2022-09-16 16:08:52', NULL),
('SUP9', 'Moto', 'Moto', 'suppliers/moto.jpg', '2022-09-16 16:08:51', NULL),
('SUP10', 'Nokia', 'Nokia', 'suppliers/nokia.jpg', '2022-09-16 16:08:51', NULL),
('SUP11', 'BlackBerry', 'BlackBerry', 'suppliers/black_berry.jpg', '2022-09-16 16:08:51', NULL),
('SUP12', 'BPhone', 'Điện thoại BPhone', 'suppliers/bphone.jpg', '2022-08-08 12:38:00', NULL),
('SUP13', 'VinPhone', 'Điện thoại VinPhone', 'suppliers/vinphone.jpg', '2022-08-08 12:45:00', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_products (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [product_code] nvarchar(25) NOT NULL ,
  [product_name] nvarchar(50) NOT NULL ,
  [image] nvarchar(4000) DEFAULT NULL ,
  [short_description] nvarchar(250) DEFAULT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [standard_cost] decimal(19,4) NOT NULL DEFAULT 0.0000 ,
  [list_price] decimal(19,4) NOT NULL DEFAULT 0.0000 ,
  [quantity_per_unit] nvarchar(50) DEFAULT NULL ,
  [discontinued] bit NOT NULL DEFAULT 0 ,
  [is_featured] bit NOT NULL DEFAULT '0' ,
  [is_new] bit NOT NULL DEFAULT '0' ,
  [category_id] bigint check ([category_id] > 0) DEFAULT NULL ,
  [supplier_id] bigint check ([supplier_id] > 0) DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_products_categories] FOREIGN KEY ([category_id]) REFERENCES shop_categories ([id]),
  CONSTRAINT [FK_products_suppliers] FOREIGN KEY ([supplier_id]) REFERENCES shop_suppliers ([id])
)   ;

CREATE INDEX [FK_products_categories] ON shop_products ([category_id]);
CREATE INDEX [FK_products_suppliers] ON shop_products ([supplier_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_products VALUES
('P1', 'Nikkon DS90', 'products/nikkon_ds60.jpg', 'Nikkon DS90 desc', 'Nikkon DS90 desc', 6709000.0000, 2339000.0000, '50', 0, '1', '0', 3, 5, NULL, NULL),
('P2', 'Canon T90', 'products/canon_t90.jpg', 'Canon T90 desc', 'Canon T90 desc', 8283000.0000, 9384000.0000, '56', 0, '0', '0', 3, 4, NULL, NULL),
('P3', 'Dell Inspirion', 'products/dell_inspirion.jpg', 'Dell Inspirion desc', 'Dell Inspirion desc', 8283000.0000, 9384000.0000, '56', 0, '0', '0', 1, 2, NULL, NULL),
('P4', 'iPad Air', 'products/ipad_air.jpg', 'iPad Air desc', 'iPad Air desc', 8283000.0000, 9384000.0000, '56', 0, '1', '1', 4, 1, NULL, NULL),
('P5', 'Microsft Surface', 'products/microsoft_surface.jpg', 'Microsft Surface desc', 'Microsft Surface desc', 8283000.0000, 9384000.0000, '56', 0, '1', '1', 4, 3, NULL, NULL),
('P6', 'Nexus 6', 'products/nexus_6.jpg', 'Nexus 6 desc', 'Nexus 6 desc', 3388000.0000, 1170000.0000, '79', 0, '0', '0', 2, 6, NULL, NULL),
('P7', 'ThinkPad T365', 'products/thinkpad_t365.jpg', 'ThinkPad T365 desc', 'ThinkPad T365 desc', 4102000.0000, 898000.0000, '92', 1, '0', '0', 1, 7, NULL, NULL),
('P8', 'Moto Play', 'products/moto_play.jpg', 'Moto Play desc', 'Moto Play desc', 3844000.0000, 6234000.0000, '54', 1, '1', '0', 2, 9, NULL, NULL),
('P9', 'Samsung Note', 'products/samsung_note.jpg', 'Samsung Note desc', 'Samsung Note desc', 4758000.0000, 8183000.0000, '58', 0, '0', '1', 4, 8, NULL, NULL),
('P10', 'MacBook Pro', 'products/macbook_pro.jpg', 'MacBook Pro desc', 'MacBook Pro desc', 2581000.0000, 7661000.0000, '11', 0, '1', '1', 1, 1, NULL, NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_export_details (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [export_id] bigint check ([export_id] > 0) NOT NULL ,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [quantity] decimal(18,4) NOT NULL DEFAULT 0.0000 ,
  [unit_price] decimal(19,4) NOT NULL DEFAULT 0.0000 ,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_export_details_shop_exports] FOREIGN KEY ([export_id]) REFERENCES shop_exports ([id]),
  CONSTRAINT [FK_shop_export_details_shop_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id])
)  ;

CREATE INDEX [FK_shop_export_details_shop_exports] ON shop_export_details ([export_id]);
CREATE INDEX [FK_shop_export_details_shop_products] ON shop_export_details ([product_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_imports (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [store_id] bigint check ([store_id] > 0) NOT NULL ,
  [employee_id] bigint check ([employee_id] > 0) NOT NULL ,
  [import_date] datetime NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_imports_acl_users] FOREIGN KEY ([employee_id]) REFERENCES acl_users ([id]),
  CONSTRAINT [FK_shop_imports_shop_stores] FOREIGN KEY ([store_id]) REFERENCES shop_stores ([id])
)    ;

CREATE INDEX [FK_shop_imports_acl_users] ON shop_imports ([employee_id]);
CREATE INDEX [FK_shop_imports_shop_stores] ON shop_imports ([store_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_import_details (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [import_id] bigint check ([import_id] > 0) NOT NULL ,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [quantity] decimal(18,4) NOT NULL DEFAULT 0.0000 ,
  [unit_price] decimal(19,4) NOT NULL DEFAULT 0.0000 ,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_import_details_shop_imports] FOREIGN KEY ([import_id]) REFERENCES shop_imports ([id]),
  CONSTRAINT [FK_shop_import_details_shop_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id])
)  ;

CREATE INDEX [FK_shop_import_details_shop_imports] ON shop_import_details ([import_id]);
CREATE INDEX [FK_shop_import_details_shop_products] ON shop_import_details ([product_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_payment_types (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [payment_code] nvarchar(50) NOT NULL ,
  [payment_name] nvarchar(500) NOT NULL ,
  [description] nvarchar(4000) DEFAULT NULL ,
  [image] nvarchar(4000) DEFAULT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [category_payment_code] UNIQUE  ([payment_code])
)    ;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_payment_types VALUES
('CK', N'Chuyển khoản', NULL, NULL, '2022-09-24 00:33:13', '2022-09-24 00:33:13'),
('Paypal', N'Thanh toán Paypal', NULL, NULL, '2022-09-24 00:33:37', NULL),
('NHTT', N'Thanh toán sau khi nhận hàng', NULL, NULL, '2022-09-24 00:33:58', NULL),
('TM', N'Tiền mặt', NULL, NULL, '2022-09-24 00:37:05', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_orders (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [employee_id] bigint check ([employee_id] > 0) NOT NULL ,
  [customer_id] bigint check ([customer_id] > 0) NOT NULL ,
  [order_date] datetime NOT NULL ,
  [shipped_date] datetime DEFAULT NULL ,
  [ship_name] nvarchar(50) NOT NULL ,
  [ship_address1] nvarchar(500) NOT NULL ,
  [ship_address2] nvarchar(500) DEFAULT NULL ,
  [ship_city] nvarchar(255) NOT NULL ,
  [ship_state] nvarchar(255) DEFAULT NULL ,
  [ship_postal_code] nvarchar(50) DEFAULT NULL ,
  [ship_country] nvarchar(255) NOT NULL ,
  [shipping_fee] decimal(19,4) NOT NULL DEFAULT 0.0000 ,
  [payment_type_id] bigint check ([payment_type_id] > 0) NOT NULL DEFAULT 0 ,
  [paid_date] datetime DEFAULT NULL ,
  [order_status] nvarchar(50) NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_orders_customers] FOREIGN KEY ([customer_id]) REFERENCES shop_customers ([id]),
  CONSTRAINT [FK_orders_employees] FOREIGN KEY ([employee_id]) REFERENCES acl_users ([id]),
  CONSTRAINT [FK_shop_orders_shop_payment_types] FOREIGN KEY ([payment_type_id]) REFERENCES shop_payment_types ([id])
)   ;

CREATE INDEX [FK_orders_employees] ON shop_orders ([employee_id]);
CREATE INDEX [FK_orders_customers] ON shop_orders ([customer_id]);
CREATE INDEX [FK_shop_orders_shop_payment_types] ON shop_orders ([payment_type_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_orders VALUES
(1, 1, '2016-04-05 00:00:00', '2016-11-06 00:00:00', 'Jean Fuller', '93 Spohn Place', NULL, 'Manggekompo', NULL, NULL, 'Indonesia', 8.1400, 1, '2016-10-12 00:00:00', 'On Hold', NULL, NULL),
(1, 1, '2017-01-29 00:00:00', '2016-05-28 00:00:00', 'Diane Holmes', '46 Eliot Trail', NULL, 'Virginia Beach', 'Virginia', '23459', 'United States', 1.5500, 3, '2016-06-27 00:00:00', 'Shipped', NULL, NULL),
(1, 1, '2016-08-19 00:00:00', '2016-12-08 00:00:00', 'Jerry Frazier', '23 Sundown Junction', NULL, 'Obodivka', NULL, NULL, 'Ukraine', 2.2900, 4, '2016-09-27 00:00:00', 'On Hold', NULL, NULL),
(1, 1, '2016-09-25 00:00:00', '2016-12-24 00:00:00', 'Denise Freeman', '4909 Beilfuss Hill', NULL, 'Nova Venécia', NULL, '29830-000', 'Brazil', 4.7700, 3, '2016-07-04 00:00:00', 'New', NULL, NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_order_details (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [order_id] bigint check ([order_id] > 0) NOT NULL,
  [product_id] bigint check ([product_id] > 0) NOT NULL,
  [quantity] decimal(18,4) NOT NULL DEFAULT 0.0000,
  [unit_price] decimal(19,4) NOT NULL DEFAULT 0.0000,
  [discount_percentage] float NOT NULL DEFAULT 0,
  [discount_amout] float NOT NULL DEFAULT 0,
  [order_detail_status] nvarchar(50) DEFAULT NULL,
  [date_allocated] datetime DEFAULT NULL,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_order_details_shop_orders] FOREIGN KEY ([order_id]) REFERENCES shop_orders ([id]),
  CONSTRAINT [FK_shop_order_details_shop_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id])
)   ;

CREATE INDEX [FK_shop_order_details_shop_orders] ON shop_order_details ([order_id]);
CREATE INDEX [FK_shop_order_details_shop_products] ON shop_order_details ([product_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_order_details VALUES
(1, 5, 1.0000, 6234000.0000, 8.73, 0, 'Allocated', '2017-01-15 00:00:00'),
(1, 6, 2.0000, 8183000.0000, 4.36, 0, 'No Stock', '2016-09-21 00:00:00'),
(2, 3, 3.0000, 9384000.0000, 2.86, 0, 'On Order', '2016-12-15 00:00:00'),
(2, 7, 4.0000, 7661000.0000, 8.65, 0, 'Allocated', '2016-10-12 00:00:00'),
(3, 2, 7.0000, 9384000.0000, 3.54, 0, 'No Stock', '2017-02-09 00:00:00'),
(3, 6, 8.0000, 8183000.0000, 4.01, 0, 'No Stock', '2016-06-15 00:00:00'),
(4, 1, 9.0000, 2339000.0000, 4.48, 0, 'Allocated', '2016-10-18 00:00:00'),
(4, 4, 6.0000, 9384000.0000, 5.68, 0, 'No Stock', '2017-02-13 00:00:00'),
(4, 7, 6.0000, 7661000.0000, 5, 0, 'No Stock', '2016-09-16 00:00:00');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_product_discounts (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [discount_name] nvarchar(500) NOT NULL ,
  [discount_amount] float NOT NULL DEFAULT 0 ,
  [is_fixed] bit NOT NULL DEFAULT '0' ,
  [start_date] datetime NOT NULL DEFAULT GETDATE(),
  [end_date] datetime NOT NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id]),
  CONSTRAINT [shop_product_discounts_ibfk_1] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id])
);

CREATE INDEX [FK_product_images_products] ON shop_product_discounts ([product_id]); 

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_product_discounts VALUES
(1, N'Giảm giá dịp lễ Vua Hùng năm 2022', 10, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59'),
(2, N'Giảm giá dịp lễ 08/03 năm 2022', 15, '0', '2022-03-01 00:00:00', '2022-03-08 23:59:59');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_product_images (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [image] nvarchar(500) NOT NULL ,
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_product_images_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id])
)   ;

CREATE INDEX [FK_product_images_products] ON shop_product_images ([product_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_product_images VALUES
(1, 'products/images_product_1_1.jpg'),
(1, 'products/images_product_1_2.jpg'),
(1, 'products/images_product_1_3.jpg'),
(2, 'products/images_product_2_1.jpg');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_product_reviews (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [customer_id] bigint check ([customer_id] > 0) NOT NULL DEFAULT 0 ,
  [rating] float NOT NULL ,
  [comment] nvarchar(4000) NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_product_reviews_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id]),
  CONSTRAINT [FK_shop_product_reviews_shop_customers] FOREIGN KEY ([customer_id]) REFERENCES shop_customers ([id])
)   ;

CREATE INDEX [FK_product_reviews_products] ON shop_product_reviews ([product_id]);
CREATE INDEX [FK_shop_product_reviews_shop_customers] ON shop_product_reviews ([customer_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_product_reviews VALUES
(1, 1, 3, N'Chụp ảnh tốt, hình vi diệu... Có điều giá hơi chát, 4s ;P', '2022-09-16 16:03:05', NULL),
(1, 1, 5, N'Sản phẩm mua mới cách đây 2 tháng, chưa thấy lỗi gì. Đóng gói khá cẩn thận, tốt.', '2022-09-16 16:05:46', NULL),
(4, 1, 1, N'Mới mua về, đang khui hộp lỡ tay làm rớt có 1 cái, hư luôn, tệ hết sức, không bảo hành luôn. Cho 1 sao vì số t xui :(', '2022-09-16 16:06:35', NULL),
(5, 1, 3, N'Có vẻ tốt, đợi thời gian nữa xem sao :V', '2022-09-16 18:26:57', NULL),
(4, 1, 5, N'Tuyệt vời, mình mua được ngay lúc giảm giá còn 20%. Xài thoải mái, xứng đáng giá tiền sau khi giảm giá ;P', '2022-09-16 19:42:06', NULL);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shop_product_vouchers (
  [id] bigint check ([id] > 0) NOT NULL IDENTITY,
  [product_id] bigint check ([product_id] > 0) NOT NULL ,
  [voucher_id] bigint check ([voucher_id] > 0) NOT NULL ,
  [created_at] datetime NULL DEFAULT GETDATE(),
  [updated_at] datetime NULL DEFAULT GETDATE(),
  PRIMARY KEY ([id])
 ,
  CONSTRAINT [FK_shop_product_vouchers_shop_products] FOREIGN KEY ([product_id]) REFERENCES shop_products ([id]),
  CONSTRAINT [FK_shop_product_vouchers_shop_vouchers] FOREIGN KEY ([voucher_id]) REFERENCES shop_vouchers ([id])
)     ;

CREATE INDEX [FK_shop_product_vouchers_shop_products] ON shop_product_vouchers ([product_id]);
CREATE INDEX [FK_shop_product_vouchers_shop_vouchers] ON shop_product_vouchers ([voucher_id]);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO shop_product_vouchers VALUES
(1, 1, '2022-09-16 16:07:38', NULL),
(5, 1, '2022-09-16 16:07:46', NULL);