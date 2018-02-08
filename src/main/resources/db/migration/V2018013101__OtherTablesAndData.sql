create table LOCATION (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(25),
  country varchar(25),
  city varchar(25),
  county varchar(25),
  street varchar(25)
);

create table ORDERR (
  id int primary key AUTO_INCREMENT,
  SHIPPED_FROM_ID int, foreign key (SHIPPED_FROM_ID) references LOCATION(id),
  CUSTOMER_ID int, foreign key (CUSTOMER_ID) references CUSTOMER(id)
);

create table SUPPLIER (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(20)
);

create table PRODUCT_CATEGORY (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(20),
  description varchar(20)
);

create table PRODUCT (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(20),
  description varchar(20),
  price BIGINT,
  weight DOUBLE,
  supplier_id int, foreign key (supplier_id) references SUPPLIER(id),
  product_category_id int, foreign key (product_category_id) references PRODUCT_CATEGORY(id)
);

create table ORDER_DETAIL (
  id int primary key AUTO_INCREMENT,
  order_id int, foreign key (order_id) references ORDERR(id),
  product_id int, foreign key (product_id) references PRODUCT(id),
  quantity int
);

create table STOCK (
  id int primary key AUTO_INCREMENT,
  location_id int, foreign key (location_id) references LOCATION(id),
  quantity int,
  product_id int, foreign key (product_id) references PRODUCT(ID)
);