Create Database PolyJavaDA2
Go

Use PolyJavaDA2
Go

Go 
    
create table accounts (
   username varchar(32) not null,
    password varchar(60) not null,
    primary key (username)
)
Go 

create table categories (
   category_id bigint identity not null,
    category_name nvarchar (100) not null,
    primary key (category_id)
)
Go 

create table customers (
   customer_id bigint identity not null,
    address nvarchar(500),
    email nvarchar(49) not null,
    name nvarchar(50) not null,
    password varchar(32) not null,
    phone varchar(20) not null,
    registered_date date,
    status smallint not null,
    primary key (customer_id)
)
Go 

create table order_details (
   order_detail_id bigint identity not null,
    quantity int not null,
    unit_price double precision not null,
    order_id bigint,
    product_id bigint,
    primary key (order_detail_id)
)
Go 

create table orders (
   order_id bigint identity not null,
    amount double precision not null,
    order_date date,
    status smallint not null,
    customer_id bigint,
    primary key (order_id)
)
Go 

create table products (
   product_id bigint identity not null,
    description nvarchar(max) not null,
    discount double precision not null,
    enter_date date,
    image varchar(900),
    name nvarchar(900) not null,
    quantity int not null,
    status smallint not null,
    unit_price double precision not null,
    category_id bigint,
    primary key (product_id)
)
Go 

create table cart_items (
   cart_item_id bigint identity not null,
    customer_id bigint not null,
    product_id bigint not null,
    quantity int not null,
    selected bit not null,
    unit_price double precision not null,
    primary key (cart_item_id)
)

Go

alter table order_details 
   add constraint FKjyu2qbqt8gnvno9oe9j2s2ldk 
   foreign key (order_id) 
   references orders
Go 

alter table order_details 
   add constraint FK4q98utpd73imf4yhttm3w0eax 
   foreign key (product_id) 
   references products
Go 

alter table orders 
   add constraint FKpxtb8awmi0dk6smoh2vp1litg 
   foreign key (customer_id) 
   references customers
Go 

alter table products 
   add constraint FKog2rp4qthbtt2lfyhfo32lsw9 
   foreign key (category_id) 
   references categories