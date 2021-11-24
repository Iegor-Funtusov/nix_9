drop table authors if exists;
drop table books if exists;
drop table publishers if exists;
drop table author_book if exists;
drop table publisher_book if exists;

create table authors
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    visible    bit null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);

create table books
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    visible          bit          null,
    book_name        varchar(255) not null,
    description      text         null,
    image_url        varchar(255) not null,
    page_size        int          not null,
    publication_date int          not null
);

create table publishers
(
    id      bigint auto_increment
        primary key,
    created datetime(6)  null,
    updated datetime(6)  null,
    visible bit          null,
    country varchar(255) not null,
    name    varchar(255) not null
);

create table author_book
(
    author_id bigint not null,
    book_id   bigint not null,
    primary key (author_id, book_id),
    foreign key (author_id) references authors (id),
    foreign key (book_id) references books (id)
);

create table publisher_book
(
    publisher_id bigint not null,
    book_id      bigint not null,
    primary key (publisher_id, book_id),
    foreign key (book_id) references books (id),
    foreign key (publisher_id) references publishers (id)
);