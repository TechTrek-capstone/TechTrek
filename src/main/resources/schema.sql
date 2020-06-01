-- -----------------------------------------------------
-- Schema techtrek_db
-- -----------------------------------------------------


CREATE DATABASE IF NOT EXISTS `techtrek_db`;
USE `techtrek_db`;

create schema if not exists techtrek_db collate utf8_general_ci;

create table if not exists cohorts
(
    id bigint auto_increment
        primary key,
    graduation_date datetime(6) null,
    location varchar(255) null,
    name varchar(255) not null,
    constraint UK_swqqq9kaugmjhmc7ans7h2kwj
        unique (name)
);

create table if not exists companies
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table if not exists job_categories
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UK_qyol2j6hcspl3raexk33q8067
        unique (name)
);

create table if not exists points_of_contact
(
    id bigint auto_increment
        primary key,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone_number varchar(255) null,
    company_id bigint null,
    constraint UK_j3cm4k7gxkjgtypd6ci7gdxtr
        unique (email),
    constraint FKbomjwn9oeb0wu2l41wu7wkpu9
        foreign key (company_id) references companies (id)
);

create table if not exists roles
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UK_ofx66keruapi6vyqpv6f2or37
        unique (name)
);

create table if not exists users
(
    id bigint auto_increment
        primary key,
    bio_summary varchar(500) null,
    create_date datetime(6) null,
    email varchar(255) not null,
    employment_status int null,
    first_name varchar(255) not null,
    github_username varchar(255) null,
    last_name varchar(255) not null,
    linkedin_username varchar(255) null,
    modify_date datetime(6) null,
    user_password varchar(255) not null,
    phone_number varchar(255) null,
    cohort_id bigint null,
    role_id bigint null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint FK22vef11nher1pinpuv18ms2d3
        foreign key (cohort_id) references cohorts (id),
    constraint FKp56c1712k691lhsyewcssf40f
        foreign key (role_id) references roles (id)
);

create table if not exists event_listings
(
    id bigint auto_increment
        primary key,
    date datetime(6) null,
    description text not null,
    is_archived bit not null,
    listing_date datetime(6) not null,
    location varchar(255) null,
    rsvp_url varchar(2000) null,
    time datetime(6) null,
    title varchar(255) not null,
    user_id bigint null,
    constraint FKg2ktcw4brmlljqxr3ikdo55wy
        foreign key (user_id) references users (id)
);

create table if not exists job_listings
(
    id bigint auto_increment
        primary key,
    apply_url varchar(2000) not null,
    description text not null,
    is_archived bit not null,
    is_remote bit not null,
    listing_date datetime(6) not null,
    location varchar(255) not null,
    preferred_skills varchar(3000) null,
    required_skills varchar(3000) null,
    title varchar(255) not null,
    company_id bigint null,
    user_id bigint null,
    constraint FK7otqccbilxtfg2eyer0gs2fq2
        foreign key (user_id) references users (id),
    constraint FKnsya1yao8u5t25yq9tngy6xv8
        foreign key (company_id) references companies (id)
);

