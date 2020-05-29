-- -----------------------------------------------------
-- Schema techtrek_db
-- -----------------------------------------------------

CREATE DATABASE IF NOT EXISTS `techtrek_db`;
USE `techtrek_db`;

-- -----------------------------------------------------
-- cohorts table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cohorts`
(
    `id`              BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(255) NOT NULL,
    `graduation_date` DATETIME(6)  NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
);

-- -----------------------------------------------------
-- users table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `users`
(
    `id`                BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `bio_summary`       VARCHAR(500) NULL DEFAULT NULL,
    `create_date`       DATETIME(6)  NULL DEFAULT NULL,
    `email`             VARCHAR(255) NOT NULL,
    `first_name`        VARCHAR(255) NOT NULL,
    `github_username`   VARCHAR(255) NULL DEFAULT NULL,
    `last_name`         VARCHAR(255) NOT NULL,
    `linkedin_username` VARCHAR(255) NULL DEFAULT NULL,
    `modify_date`       DATETIME(6)  NULL DEFAULT NULL,
    `user_password`     VARCHAR(255) NOT NULL,
    `phone_number`      VARCHAR(255) NOT NULL,
    `employment_status` INT(11)      NULL DEFAULT NULL,
    `cohort_id`         BIGINT(20)   NULL DEFAULT NULL,
    `role`              BIGINT(20)   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`email`),
--     FOREIGN KEY (`role`) REFERENCES `roles` (`id`),
    FOREIGN KEY (`cohort_id`) REFERENCES `cohorts` (`id`)
);

-- -----------------------------------------------------
-- roles table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `roles`
(
    `id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL DEFAULT "Unassigned",
    `users_id` BIGINT(20)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
);

-- -----------------------------------------------------
-- companies table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `companies`
(
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- event_listings table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `event_listings`
(
    `id`           BIGINT(20)    NOT NULL AUTO_INCREMENT,
    `date`         DATETIME(6)   NULL DEFAULT NULL,
    `description`  TEXT          NOT NULL,
    `is_archived`  BIT(1)        NOT NULL,
    `listing_date` DATETIME(6)   NOT NULL,
    `location`     VARCHAR(255)  NULL DEFAULT NULL,
    `rsvp_url`     VARCHAR(2000) NULL DEFAULT NULL,
    `time`         DATETIME(6)   NULL DEFAULT NULL,
    `title`        VARCHAR(255)  NOT NULL,
    `user_id`      BIGINT(20)    NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);


-- -----------------------------------------------------
-- job_categories table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `job_categories`
(
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
);

-- -----------------------------------------------------
-- Table job_listings
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `job_listings`
(
    `id`               BIGINT(20)    NOT NULL AUTO_INCREMENT,
    `apply_url`        VARCHAR(2000) NOT NULL,
    `description`      TEXT          NOT NULL,
    `is_archived`      BIT(1)        NOT NULL,
    `is_remote`        BIT(1)        NOT NULL,
    `listing_date`     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `location`         VARCHAR(255)  NOT NULL,
    `preferred_skills` VARCHAR(3000) NULL     DEFAULT NULL,
    `required_skills`  VARCHAR(3000) NULL     DEFAULT NULL,
    `title`            VARCHAR(255)  NOT NULL,
    `user_id`          BIGINT(20)    NULL     DEFAULT NULL,
    `company_id`       BIGINT(20)    NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

-- -----------------------------------------------------
-- points_of_contact table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `points_of_contact`
(
    `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `email`        VARCHAR(255) NOT NULL,
    `first_name`   VARCHAR(255) NOT NULL,
    `last_name`    VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(255) NULL DEFAULT NULL,
    `company_id`   BIGINT(20)   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`email`),
    FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
);

-- -----------------------------------------------------
-- job_listings_job_categories_join table
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `job_listings_job_categories_join`
(
    `job_listings_id`   BIGINT(20) NOT NULL,
    `job_categories_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_listings_id`, `job_categories_id`),
    FOREIGN KEY (`job_listings_id`) REFERENCES `job_listings` (`id`),
    FOREIGN KEY (`job_categories_id`) REFERENCES `job_categories` (`id`)
)
