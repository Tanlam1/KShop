create database K5Shop
go
use K5Shop
go

ALTER TABLE Cutomers
ADD district nvarchar(200)
ALTER TABLE [User]
ADD img nvarchar(200)

ALTER TABLE Cutomers
ADD sex nvarchar(10)

ALTER TABLE Cutomers
ALTER Column sex nvarchar(10)

-- Sp
create table GroupProduct(
	ID int primary key identity,
	[Name] nvarchar(500) not null,
	[Content] ntext not null,
	images nvarchar(300) not null,
	[Status] bit not null,
)


-- Chi tiet sp
create table Product(
	Id int primary key,
	[Name] nvarchar(500),
	Detail nvarchar(500),
	Price float,
	Pricenew float,
	[Date] date,
	Amount int,
	[Status] bit,
	GroupID int,
	constraint FK_Product_GroupProduct foreign key(GroupID) references GroupProduct(Id)
)

create table Img(
	ID int primary key identity,
	Imageid int,
	[Image] nvarchar(200),
	constraint FK_Img foreign key(Imageid) references Product(Id)
)

-- Khach hang
create table Cutomers(
	Id int primary key,
	[name] nvarchar(200),
	birthday date,
	procvince nvarchar(200),
	[address] ntext,
	tel varchar(20)
)

--Bảng Đặt hàng
create table Orders(
	Id int primary key,
	Customer_id int,
	Totalmoney float,
	[Date] datetime,
	[Status] bit,
	constraint FK_Orders_Cutomers foreign key(Customer_id) references Cutomers(Id)
)

--Bảng Chi tiết Đặt hàng
create table Orderdetail(
	Id int primary key,
	Order_Id int,
	Product_Id int,
	Quantity int,
	constraint FK_OrderDetail_Orders foreign key(Order_Id) references Orders(Id),
	constraint FK_OrderDetail_Product foreign key(Product_Id) references Product(Id)
)

-- User
create table [User](
	ID int primary key identity,
	Username varchar(25) unique not null,
	[Password] varchar(25) not null,
	Email varchar(50) not null,
	[Status] bit not null,
	Rules int not null,
	Cutomers_id int not null,
	constraint FK_user_cutomers foreign key(Cutomers_id) references Cutomers(Id)
)



