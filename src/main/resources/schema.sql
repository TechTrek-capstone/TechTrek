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

create table if not exists roles
(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    unique (name)
);

create table if not exists users
(
    id bigint auto_increment primary key,
    enabled bit NOT NULL DEFAULT 0,
    bio_summary varchar(1000) not null default '',
    create_date datetime(6) null,
    email varchar(255) not null,
    username varchar(255) not null,
    employment_status int not null default 1,
    work_location varchar(255) not null default '',
    userfirstname varchar(255) not null default '',
    github_username varchar(255)  not null default '',
    last_name varchar(255)  not null default '',
    linkedin_username varchar(255)  not null default '',
    modify_date datetime(6) null,
    user_password varchar(255) not null,
    phone_number varchar(255)  not null default '',
    cohort_id bigint null,
    role_id bigint not null default 3,
    user_website varchar(255) not null default '',
    profile_pic varchar(255),
    unique (email),
    foreign key (cohort_id) references cohorts (id),
    foreign key (role_id) references roles (id)
);

CREATE TABLE IF NOT EXISTS skills (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_skills (
    user_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
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
    time varchar(255) null,
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
    is_remote bit not null default false,
    listing_date datetime(6) not null,
    location varchar(255) not null,
    title varchar(255) not null,
    company_id bigint null,
    user_id bigint null,
#     foreign key (user_id) references users (id),
    foreign key (company_id) references companies (id)
);

INSERT INTO techtrek_db.cohorts
(id,
 graduation_date,
 name,
 location)

VALUES (1,
        '2020-06-16',
        'Fortuna',
        'San Antonio, TX'),

       (2,
        '2020-07-15',
        'Apex',
        'Dallas, TX'),

       (3,
        '2020-08-20',
        'Gandymeade',
        'San Antonio, TX'),

       (4,
        '2020-10-22',
        'Hyperion',
        'San Antonio, TX');

INSERT INTO techtrek_db.roles (id, name) VALUES (1, 'USER_ADMIN');
INSERT INTO techtrek_db.roles (id, name) VALUES (2, 'USER_PLACE');
INSERT INTO techtrek_db.roles (id, name) VALUES (3, 'USER_STUDENT');

INSERT INTO techtrek_db.skills (id, name)

VALUES (1, 'HTML5'),
       (2, 'CSS'),
       (3, 'Web design'),
       (4, 'Bootstrap'),
       (5, 'JavaScript'),
       (6, 'jQuery'),
       (7, 'Java SE'),
       (8, 'Java EE'),
       (9, 'MySQL'),
       (10, 'Spring Boot'),
       (11, 'Thymeleaf'),
       (12, 'OOP'),
       (13, 'Git'),
       (14, 'Paired programming'),
       (15, 'TDD'),
       (16, 'React'),
       (17, 'Codeply'),
       (18, 'npm'),
       (19, 'Node.js'),
       (20, 'Jasmine'),
       (21, 'Python'),
       (22, 'C'),
       (23, 'C#'),
       (24, 'C++'),
       (25, 'Docker'),
       (26, 'Kubernetes'),
       (27, '.NET Core'),
       (28, 'Data structures'),
       (29, 'Agile workflow'),
       (30, 'Maven'),
       (31, 'Ant'),
       (32, 'Gradle'),
       (33, 'Tomcat'),
       (34, 'AWS'),
       (35, 'RESTful APIs'),
       (36, 'Angular'),
       (37, 'PostgreSQL'),
       (38, 'Cassandra'),
       (39, 'MongoDB'),
       (40, 'NoSQL'),
       (41, 'GCP'),
       (42, 'Swift'),
       (43, 'Scala'),
       (44, 'Go'),
       (45, 'Elm'),
       (46, 'Ruby'),
       (47, 'Rust'),
       (48, 'PHP'),
       (49, 'MATLAB'),
       (50, 'Groovy'),
       (51, 'Material-UI');

