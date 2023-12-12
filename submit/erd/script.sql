create table if not exists Categories
(
    CategoryID   int auto_increment
        primary key,
    CategoryName varchar(50) null
);

create table if not exists Products
(
    ProductID    int auto_increment
        primary key,
    ModelNumber  varchar(50) charset utf8  null,
    ModelName    varchar(50) charset utf8  null,
    ProductImage varchar(120) charset utf8 null,
    UnitCost     decimal(15)               null,
    Description  text                      null
);

create table if not exists CategoryProduct
(
    CategoryID int not null,
    ProductID  int not null,
    primary key (CategoryID, ProductID),
    constraint fk_CategoryProduct_Categories
        foreign key (CategoryID) references Categories (CategoryID),
    constraint fk_CategoryProduct_Orders
        foreign key (ProductID) references Products (ProductID)
);

create table if not exists users
(
    user_id         varchar(50)  not null comment '아이디'
        primary key,
    user_name       varchar(50)  not null comment '이름',
    user_password   varchar(200) not null comment 'mysql password 사용',
    user_birth      varchar(8)   not null comment '생년월일 : 19840503',
    user_auth       varchar(10)  not null comment '권한: ROLE_ADMIN,ROLE_USER',
    user_point      int          not null comment 'default : 1000000',
    created_at      datetime     not null comment '가입일자',
    latest_login_at datetime     null comment '마지막 로그인 일자'
)
    comment '회원';

create table if not exists Orders
(
    OrderID     int auto_increment
        primary key,
    CustomerID  int          null,
    OrderDate   datetime     null,
    ShipDate    datetime     null,
    UserID      varchar(50)  not null,
    ShipAddress varchar(100) not null,
    constraint PK_ORDERS_USERID
        foreign key (UserID) references users (user_id)
);

create table if not exists OrderDetails
(
    OrderID   int         not null,
    ProductID int         not null,
    Quantity  int         null,
    UnitCost  decimal(15) null,
    primary key (OrderID, ProductID),
    constraint fk_OrderDetails_Orders
        foreign key (OrderID) references Orders (OrderID),
    constraint fk_OrderDetails_Products
        foreign key (ProductID) references Products (ProductID)
);

create index fk_Orders_CustomerID
    on Orders (CustomerID);

create table if not exists PointRecords
(
    user_id    varchar(50) not null,
    Amount     int         not null,
    OrderID    int         null,
    RecordDate datetime    null,
    constraint fk_PointRecordOrders
        foreign key (OrderID) references Orders (OrderID),
    constraint fk_PointRecordUsers
        foreign key (user_id) references users (user_id)
);

create table if not exists Reviews
(
    ReviewID   int auto_increment
        primary key,
    ProductID  int         null,
    CustomerID int         null,
    Rating     int         null,
    Comments   text        null,
    UserID     varchar(50) not null,
    constraint PK_REVIEWS_USERS
        foreign key (UserID) references users (user_id),
    constraint fk_Review_Products
        foreign key (ProductID) references Products (ProductID)
);

create index fk_Review_Customer
    on Reviews (CustomerID);

create table if not exists ShoppingCart
(
    RecordID    int auto_increment
        primary key,
    Quantity    int                                null,
    ProductID   int                                null,
    DateCreated datetime default CURRENT_TIMESTAMP null,
    user_id     varchar(50)                        not null,
    constraint fk_cart_ProductID
        foreign key (ProductID) references Products (ProductID),
    constraint fk_cart_UserID
        foreign key (user_id) references users (user_id)
);

create table if not exists UserAddresses
(
    user_id      varchar(50)  not null,
    user_address varchar(100) not null,
    constraint UserAddresses_ibfk_1
        foreign key (user_id) references users (user_id)
);

create index UserAddresses
    on UserAddresses (user_id);

