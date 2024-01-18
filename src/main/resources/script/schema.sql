create table if not exists Categories
(
    category_id   int         not null
        primary key,
    category_name varchar(50) not null
);

create table if not exists Products
(
    product_id    int auto_increment
        primary key,
    product_name  varchar(50)  not null,
    product_number     varchar(50)  not null,
    product_image varchar(120) null,
    unit_cost     decimal(15)  not null,
    description   text         null
);

create table if not exists CategoryProduct
(
    product_id  int not null,
    category_id int not null,
    primary key (product_id, category_id),
    constraint FK_Categories_TO_CategoryProduct_1
        foreign key (category_id) references Categories (category_id),
    constraint FK_Products_TO_CategoryProduct_1
        foreign key (product_id) references Products (product_id)
);

create table if not exists Users
(
    user_id         varchar(50)                        not null
        primary key,
    user_name       varchar(50)                        not null,
    user_password   varchar(200)                       not null,
    user_birth      varchar(8)                         not null,
    user_auth       varchar(10)                        not null,
    user_point      int      default 1000000           not null,
    created_at      datetime default CURRENT_TIMESTAMP not null,
    latest_login_at datetime                           null
);

create table if not exists Orders
(
    order_id     int auto_increment
        primary key,
    order_date   datetime     null,
    ship_date    datetime     null,
    ship_address varchar(100) not null,
    user_id      varchar(50)  not null,
    constraint FK_users_TO_Orders_1
        foreign key (user_id) references Users (user_id)
);

create table if not exists OrderDetails
(
    order_id   int         not null,
    product_id int         not null,
    quantity   int         not null,
    unit_cost  decimal(15) not null,
    primary key (order_id, product_id),
    constraint FK_Orders_TO_OrderDetails_1
        foreign key (order_id) references Orders (order_id),
    constraint FK_Products_TO_OrderDetails_1
        foreign key (product_id) references Products (product_id)
);

create table if not exists PointRecords
(
    record_id   int auto_increment
        primary key,
    amount      int         not null,
    record_date datetime    not null,
    order_id    int         null,
    user_id     varchar(50) not null,
    constraint FK_Orders_TO_PointRecords_1
        foreign key (order_id) references Orders (order_id),
    constraint FK_users_TO_PointRecords_1
        foreign key (user_id) references Users (user_id)
);

create table if not exists Reviews
(
    review_id  int auto_increment
        primary key,
    rating     int         not null,
    comment    text        null,
    product_id int         not null,
    user_id    varchar(50) null,
    constraint FK_Products_TO_Reviews_1
        foreign key (product_id) references Products (product_id),
    constraint FK_users_TO_Reviews_1
        foreign key (user_id) references Users (user_id)
);

create table if not exists ShoppingCarts
(
    cart_id         int auto_increment
        primary key,
    cart_created_at datetime default CURRENT_TIMESTAMP not null,
    user_id         varchar(50)                        not null,
    constraint FK_Users_TO_ShoppingCart_1
        foreign key (user_id) references Users (user_id)
);

create table if not exists CartProduct
(
    cart_id    int not null,
    product_id int not null,
    quantity   int not null,
    primary key (cart_id, product_id),
    constraint FK_Products_TO_CartProduct_1
        foreign key (product_id) references Products (product_id),
    constraint FK_ShoppingCart_TO_CartProduct_1
        foreign key (cart_id) references ShoppingCarts (cart_id)
);

create table if not exists UserAddresses
(
    address_id   int auto_increment
        primary key,
    user_address varchar(100) not null,
    user_id      varchar(50)  not null,
    constraint FK_Users_TO_UserAddresses_1
        foreign key (user_id) references Users (user_id)
);

