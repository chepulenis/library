drop table if exists users_roles;
drop table if exists books;
drop table if exists categories;
drop table if exists users;
drop table if exists roles;

create table categories
(
    id   serial       not null primary key,
    name varchar(255) not null
);

create table books
(
    id          serial              not null primary key,
    title       varchar(255)        not null,
    author      varchar(255)        not null,
    description varchar(255)        not null,
    ISBN        varchar(255)        unique not null,
    category_id integer  references categories(id) on delete  cascade
);

create table roles
(
    id   serial       not null primary key,
    name varchar(255) not null
);

create table users
(
    id       serial       not null primary key,
    email    varchar(255) not null unique,
    name     varchar(255) not null,
    password varchar(255) not null
);

create table users_roles
(
    constraint id primary key (user_id, role_id),
    user_id integer references users (id) on delete cascade,
    role_id integer references roles (id) on delete cascade
);


insert into categories (id, name) values
(1, 'Fantasy'),
(2, 'Sci-Fi'),
(3, 'Mystery'),
(4, 'Thriller'),
(5, 'Romance');

insert into books (id, title, author, description, ISBN, category_id) values
(1, 'Romeo and Juliet', 'William Shakespeare', 'some description', '978-0393090185', 5),
(2, 'Shining', 'Stephen King', 'some description', '978-0393090186', 4),
(3, 'Don Quixote', 'Miguel de Cervantes', 'some description', '978-0393090187', 5),
(4, 'Ulysses', 'James Joyce', 'some description', '978-0393090188', 3);

commit;
