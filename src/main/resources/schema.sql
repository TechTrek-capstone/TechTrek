-- -----------------------------------------------------
-- Schema techtrek_db
-- -----------------------------------------------------


CREATE DATABASE IF NOT EXISTS `techtrek_db`;
USE `techtrek_db`;

create schema if not exists techtrek_db collate utf8_general_ci;

create table if not exists cohorts
(
    id bigint auto_increment primary key,
    graduation_date datetime(6) null,
    location varchar(255) null,
    name varchar(255) not null,
    unique (name)
);

create table if not exists companies
(
    id bigint auto_increment primary key,
    name varchar(255) not null
);

create table if not exists job_categories
(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    unique (name)
);



create table if not exists users
(
    id bigint auto_increment primary key,
    enabled bit NOT NULL DEFAULT 0,
    bio_summary varchar(500) not null default '',
    create_date datetime(6) null,
    email varchar(255) not null,
    username varchar(255) not null,
    employment_status int null,
    userfirstname varchar(255) not null default '',
    github_username varchar(255)  not null default '',
    last_name varchar(255)  not null default '',
    linkedin_username varchar(255)  not null default '',
    modify_date datetime(6) null,
    user_password varchar(255) not null,
    phone_number varchar(255)  not null default '',
    cohort_id bigint null,
    role_id bigint null,
    user_website varchar(255) not null default '',
    unique (email),
    foreign key (cohort_id) references cohorts (id),
    foreign key (role_id) references roles (id)
);

create table if not exists event_listings
(
    id bigint auto_increment primary key,
    date datetime(6) null,
    description text not null,
    is_archived bit not null,
    listing_date datetime(6) not null,
    location varchar(255) null,
    rsvp_url varchar(2000) null,
    time datetime(6) null,
    title varchar(255) not null,
    user_id bigint null,
    foreign key (user_id) references users (id)
);

create table if not exists job_listings
(
    id bigint auto_increment primary key,
    apply_url varchar(2000) not null,
    description text not null,
    is_archived bit not null,
    is_remote bit null,
    listing_date datetime(6) not null,
    location varchar(255) not null,
    title varchar(255) not null,
    company_id bigint null,
    user_id bigint null,
#     foreign key (user_id) references users (id),
    foreign key (company_id) references companies (id)
);

create table if not exists user_roles
(
    id bigint auto_increment primary key,
    user_id bigint null,
    role_id int null
);

create table if not exists roles
(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    unique (name)
);
