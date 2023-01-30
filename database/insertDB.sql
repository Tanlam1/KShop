use kshop_main

insert into [acl_permissions] values
(N'Full Control', N'Toàn quyền kiểm soát', 'we', '2022-04-29', '2022-04-30'),
(N'Write', N'Chỉnh sửa, tạo mới dữ liệu', 'we', '2022-04-29', '2022-05-01'),
(N'List folder contents', N'Liệt kê nội dung folder', 'we', '2022-04-29', '2022-05-01'),
(N'Read and Execute', N'Đọc nội dung một số file và cho phép thực thi nếu đó là file chương trình', 'we', '2022-05-01', '2022-05-03'),
(N'Modify', N'Chỉnh sửa, tạo mới dữ liệu, Liệt kê nội dung folder, đọc và thực thi file chương trình ', 'we', '2022-05-01', '2022-05-06')

INSERT INTO acl_roles VALUES
('Customer advisor', N'Nhân viên tư vấn khách hàng', 'we', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
('Customer service', N'Nhân viên chăm sóc khách hàng', 'we', '2019-12-09 03:10:11', '2019-12-09 03:10:11'),
('Sale logistics', N'Nhân viên hậu cần', 'we', '2022-09-16 15:58:13', '2022-09-16 15:58:15'),
('Assistant Manager', N'Trợ lý quản lý', 'we', '2022-09-16 14:09:53', '2022-09-16 14:09:56'),
('Close a sale', N'Nhân viên chốt đơn', 'we', '2022-09-16 14:09:53', '2022-09-16 14:09:56')

INSERT INTO acl_users VALUES
('staff1', '123', N'Tui là Nhân', N'Viên', 0, 'staff1@fpt.edu.vn', NULL, 'staff1.jpg', NULL, 'Developer', 'IT Software', NULL, '0123-456-789', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL),
('khadet', '123', N'Đặng Ngọc Minh', N'Thư', 0, 'thudnmps15642@fpt.edu.vn', NULL, 'thu.jpg', NULL, 'Developer', 'IT Software', NULL, '0915300200', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL),
('staff3', '123', N'Nguyễn Thị', N'Tèo', 0, 'teont@fpt.edu.vn', NULL, 'teont.jpg', NULL, 'Housewife', 'My Home', NULL, '0915333222', N'Tô ký, An Phú Đông, Quận 12', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL),
('staff4', '123', N'Nguyễn Như', N'Huỳnh', 0, 'huynhnn@fpt.edu.vn', NULL, 'huynhnn.jpg', NULL, 'Housewife', 'My Home', NULL, '0945832910', N'Quốc lộ 1A, Tân Thới Hiệp, Quận 12', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL),
('staff2', '123', N'Đàm Vĩnh', N'Biệt', 0, 'bietdv@fpt.edu.vn', NULL, 'bietdv.jpg', NULL, 'Tester', 'IT Software', NULL, '0945833910', N'Quốc lộ 1A, Tân Thới Hiệp, Quận 12', NULL, 'Tp HCM', NULL, '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 16:16:00', NULL)

INSERT INTO shop_categories VALUES
('CAT5', 'Headphone', N'All Headphone', 'headphone.jpg', NULL, NULL),
('CAT6', 'Smart Watch', N'All Smartwatch', 'smartwatch.jpg', NULL, NULL),
('CAT7', 'Keyboard', N'All Keyboard', 'keyboard.jpg', NULL, NULL),
('CAT8', 'Computer Mouse', N'All Computer Mouse', 'computermouse.jpg', NULL, NULL),
('CAT9', 'Shirt', N'All Shirt', 'BOMBER_MISSOUT.jpg', NULL, NULL);

INSERT INTO shop_customers VALUES
('customer1', '123', N'Lê Thị', N'Riêng', 0, 'rienglt@gmail.com', '2000-06-11', 'rienglt.jpg', '', 'FPT Software', '0123456789', N'Tô ký, An Phú Đông, Quận 12', N'Tô ký, An Phú Đông, Quận 12', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30'),
('customer2', '123', N'Dương Thị', N'Mười', 0, 'muoidt@gmail.com', '1999-09-11', 'muoidt.jpg', '', 'FPT Software', '0987654321', N'Tô ký, An Phú Đông, Quận 12', N'Tô ký, An Phú Đông, Quận 12', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30'),
('customer3', '123', N'Hà Huy', N'Giáp', 0, 'giaphh@gmail.com', '1998-06-21', 'giaphh.jpg', '', 'FPT Software', '0987321654', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30'),
('customer4', '123', N'Nguyễn', N'Oanh', 0, 'oanhn@gmail.com', '2003-03-24', 'oanhn.jpg', '', 'FPT Software', '0654321987', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30'),
('customer5', '123', N'Trường', N'Chinh', 0, 'chinht@gmail.com', '1989-05-11', 'chinht.jpg', '', 'FPT Software', '0531246987', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', N'Thạnh Lộc 17, Thạnh Lộc, Quận 12', 'Tp HCM', '', '99999', 'Vietnam', NULL, NULL, 1, '2022-09-16 12:27:30', '2022-09-16 12:27:30')

INSERT INTO shop_vouchers VALUES
('VOU2', 'VOUCHER2', N'Tưng bừng nhập học', 100, 10, 1, 1, 50000, '1', '2022-09-01 00:00:00', '2022-09-30 23:59:59', '2022-08-25 15:07:31', NULL, NULL),
('VOU3', 'VOUCHER3', N'Đón chào sinh nhật KShop', 100, 20, 1, 2, 100000, '0', '2022-10-10 00:00:00', '2022-10-13 23:59:59', '2022-09-01 15:10:54', NULL, NULL),
('COUP2', 'COUPON2', N'Tri ân khách hàng', 100, 20, 1, 2, 5, '0', '2022-10-01 00:00:00', '2022-11-02 23:59:59', '2022-09-26 15:10:54', NULL, NULL),
('COUP3', 'COUPON3', N'Tri ân khách hàng', 100, 10, 1, 1, 10, '1', '2022-10-01 00:00:00', '2022-11-02 23:59:59', '2022-09-26 15:07:31', NULL, NULL),
('COUP4', 'COUPON4', N'Tri ân khách hàng', 100, 10, 1, 1, 10, '1', '2022-10-01 00:00:00', '2022-11-02 23:59:59', '2022-09-26 15:07:31', NULL, NULL)

INSERT INTO shop_customer_vouchers VALUES
(2, 8, '2022-09-16 16:08:01', NULL),
(3, 9, '2022-09-16 16:08:03', NULL),
(4, 5, '2022-09-16 16:08:05', NULL),
(5, 6, '2022-09-16 16:08:07', NULL),
(6, 7, '2022-09-16 16:08:09', NULL)

INSERT INTO shop_suppliers VALUES
('SUP14', 'HOCO', 'HOCO', 'logoHOCO.jpg', '2022-09-16 16:08:55', NULL),
('SUP15', 'XSmart', 'XSmart', 'logoXSmart.jpg', '2022-09-16 16:08:55', NULL),
('SUP16', 'Logitech', 'Logitech', 'logoLogitech.jpg', '2022-09-16 16:08:54', NULL),
('SUP17', 'MAYLANSTORE', 'MAYLANSTORE', 'logoMAYLANSTORE.jpg', '2022-09-16 16:08:54', NULL)

INSERT INTO shop_products VALUES
('P11', 'Crack K2 PRO Led RGB 10', 'Crack_K2_PRO_Led_RGB_10.jpg', N'Bàn Phím Cơ Máy Tính Crack K2 PRO Led RGB 10 Chế Độ Khác Nhau, Chơi Game Dùng Văn Phòng Cực Đã', 'Crack K2 PRO Led RGB 10', 6709000.0000, 2339000.0000, '50', 0, '1', '0', 12, 15, '2022-09-13 00:00:00.000', NULL),
('P12', 'Hoco W25', 'headphoneHoco.jpg', 'Tai nghe chụp tai bluetooth Hoco W25 chính hãng', 'tai bluetooth Hoco W25', 8283000.0000, 9384000.0000, '50', 0, '0', '0', 10, 14, '2022-09-12 00:00:00.000', NULL),
('P13', 'HOCO Y3', 'smartwatchHoco.jpg', 'Đồng hồ đeo tay HOCO Y3 thông minh theo dõi sức khỏe kháng nước IP68', 'HOCO Y3', 8283000.0000, 9384000.0000, '50', 0, '0', '0', 11, 14, '2022-09-11 00:00:00.000', NULL),
('P14', 'Logitech G403 Hero', 'computer_mouse.jpg', 'Chuột Chơi Game Logitech G403 Hero Chuột Máy Tính Gaming Bảo Hành 24 Tháng', 'Logitech G403 Hero', 8283000.0000, 9384000.0000, '50', 0, '1', '1', 13, 16, '2022-09-10 00:00:00.000', NULL),
('P15', 'BOMBER MISSOUT', 'BOMBER_MISSOUT.jpg', 'Áo Khoác Bomber form Oversize mẫu Missout - vải nhung gân dày dặn 2 lớp', 'BOMBER MISSOUT', 8283000.0000, 9384000.0000, '100', 0, '1', '1', 14, 17, '2022-09-09 00:00:00.000', NULL)

INSERT INTO shop_orders VALUES
(7, 2, '2016-04-07 00:00:00', '2016-11-02 00:00:00', N'Quan Trung', N'22 Tô ký', NULL, 'TP.HCM', NULL, NULL, N'Việt Nam', 8.1400, 1, '2016-10-12 00:00:00', 'On Hold', NULL, NULL),
(8, 4, '2017-01-29 00:00:00', '2016-05-28 00:00:00', N'Nguyễn Huệ', N'66 Đồng Tháp', NULL, N'TP.Cao Lãnh', NULL, NULL, N'Việt Nam', 1.5500, 3, '2016-06-27 00:00:00', 'Shipped', NULL, NULL),
(9, 3, '2016-08-19 00:00:00', '2016-12-08 00:00:00', N'Khánh Trinh', N'59 Âu Cơ', NULL, 'TP.HCM', NULL, NULL, N'Việt Nam', 2.2900, 4, '2016-09-27 00:00:00', 'On Hold', NULL, NULL),
(10, 5, '2016-09-25 00:00:00', '2016-12-24 00:00:00', N'Vân Anh', N'29 Lạc Long Quân', NULL, 'TP.HCM', NULL, NULL, N'Việt Nam', 4.7700, 3, '2016-07-04 00:00:00', 'New', NULL, NULL),
(11, 6, '2016-09-25 00:00:00', '2016-12-24 00:00:00', N'Bảo Trâm', N'219 Trường Chinh', NULL, 'TP.HCM', NULL, NULL, N'Việt Nam', 4.7700, 3, '2016-07-04 00:00:00', 'New', NULL, NULL)

INSERT INTO shop_order_details VALUES
(5, 11, 1.0000, 6234000.0000, 8.73, 0, 'Allocated', '2017-01-15 00:00:00'),
(6, 12, 2.0000, 8183000.0000, 4.36, 0, 'No Stock', '2016-09-21 00:00:00'),
(7, 13, 3.0000, 9384000.0000, 2.86, 0, 'On Order', '2016-12-15 00:00:00'),
(8, 14, 4.0000, 7661000.0000, 8.65, 0, 'Allocated', '2016-10-12 00:00:00'),
(9, 15, 7.0000, 9384000.0000, 3.54, 0, 'No Stock', '2017-02-09 00:00:00')

INSERT INTO shop_product_discounts VALUES
(11, N'Giảm giá dịp tựu trường', 10, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59'),
(12, N'Giảm giá dịp sinh nhật K5Shop', 15, '0', '2022-03-01 00:00:00', '2022-03-08 23:59:59'),
(13, N'Tri Ân khách hàng', 10, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59'),
(14, N'Tri Ân khách hàng', 10, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59'),
(15, N'Tri Ân khách hàng', 10, '0', '2022-09-01 00:00:00', '2022-03-31 23:59:59')

INSERT INTO shop_product_images VALUES
(11, 'Crack_K2_PRO_Led_RGB_10.jpg'),
(12, 'headphoneHoco.jpg'),
(13, 'smartwatchHoco.jpg'),
(14, 'computer_mouse.jpg'),
(15, 'BOMBER_MISSOUT.jpg')

INSERT INTO shop_product_reviews VALUES
(11, 2, 5, N'Phím gõ nghe rất êm tai, đén led lấp lánh', '2022-09-16 16:03:05', NULL), --product_id , customer_id
(12, 3, 5, N'Nghe rất êm tai, âm thanh chân thật', '2022-09-16 16:05:46', NULL),
(13, 4, 2, N'Đồng hồ không chống nước, có hiện tượng giật lag sau 2 tuần sử dụng', '2022-09-16 16:06:35', NULL),
(14, 5, 4, N'Có vẻ tốt, phản hồi khi click khá ổn', '2022-09-16 18:26:57', NULL),
(15, 6, 5, N'Áo ấm lắm ạ, chất liệu vải mềm mại, e thích lắm', '2022-09-16 19:42:06', NULL);

INSERT INTO shop_product_vouchers VALUES
(11, 5, '2022-09-16 16:07:38', NULL),
(12, 6, '2022-09-16 16:07:38', NULL),
(13, 7, '2022-09-16 16:07:38', NULL),
(14, 7, '2022-09-16 16:07:38', NULL),
(15, 9, '2022-09-16 16:07:46', NULL);

select top(10) id, product_code, product_name, short_description, created_at, updated_at
from shop_products
ORDER BY created_at DESC

select *
from shop_products
ORDER BY product_name

select *
from shop_products
WHERE  product_name like 'c%'

select shop_categories.category_name, shop_products.product_name, shop_products.short_description
from shop_categories inner join shop_products
on shop_categories.id = shop_products.category_id
WHERE product_name like 'c%'

UPDATE shop_products
SET short_description = N'Tai nghe chụp tai bluetooth Hoco W25 chính hãng'
WHERE id = 12;