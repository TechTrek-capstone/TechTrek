#  SEEDER INFO

# Roles seeder

INSERT INTO techtrek_db.roles (id, name)

VALUES (1, 'Admin'),
       (2, 'Placement'),
       (3, 'Student');


# Cohorts seeder

INSERT INTO techtrek_db.cohorts
(id,
 graduation_date,
 name,
 location)

VALUES (1,
        '2020-06-16 00:00:00',
        'Fortuna',
        'San Antonio'),

       (2,
        '2020-07-15 00:00:00',
        'Apex',
        'Dallas'),

       (3,
        '2020-08-20 00:00:00',
        'Gandymeade',
        'San Antonio'),

       (4,
        '2020-10-22 00:00:00',
        'Hyperion',
        'San Antonio');


# Users seeder

INSERT INTO techtrek_db.users
(id,
 bio_summary,
 create_date,
 email,
 employment_status,
 first_name,
 github_username,
 last_name,
 linkedin_username,
 modify_date,
 user_password,
 phone_number,
 cohort_id,
 role_id)

VALUES (1,
        'Hello it me',
        '2020-05-21 00:00:00.000000',
        'danielle.tobler210@gmail.com',
        true,
        'Danielle',
        'dmtobler',
        'Tobler',
        'danielle-tobler',
        null,
        'test',
        '2102322232',
        1,
        3);


#  Companies seeder

INSERT INTO techtrek_db.companies (id, name)

VALUES ('1', 'MokTek'),
       ('2', 'Software \'R Us'),
       ('3', 'Acme Corporation'),
       ('4', 'Globex Corporation'),
       ('5', 'Soylent Corp'),
       ('6', 'Initech'),
       ('7', 'Umbrella Corporation'),
       ('8', 'Hooli');


# Job Categories seeder

INSERT INTO techtrek_db.job_categories (id, name)

VALUES (1, 'Front end'),
       (2, 'Back end'),
       (3, 'Full stack'),
       (4, 'DevOps'),
       (5, 'Cybersecurity'),
       (6, 'Web development'),
       (7, 'Mobile development'),
       (8, 'Cloud computing'),
       (9, 'Data science'),
       (10, 'Embedded systems');


# Event listings seeder

INSERT INTO techtrek_db.event_listings
(id,
 date,
 description,
 is_archived,
 listing_date,
 location,
 rsvp_url,
 time,
 title,
 user_id)

VALUES (1,
        '2020-05-21 00:00:00.000000',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        false,
        '2020-05-21 00:00:00.000000',
        'San Antonio Coffee House',
        'www.google.com',
        '2020-05-21 00:00:00.000000',
        'The Best Event Ever',
        1),

       (2,
        '2020-05-21 00:00:00.000000',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        false,
        '2020-05-21 00:00:00.000000',
        'San Antonio Coffee Haus',
        'www.google.com',
        '2020-05-21 00:00:00.000000',
        'The Second Best Event Ever',
        1);


# Job listings seeder

INSERT INTO techtrek_db.job_listings
(id,
 apply_url,
 description,
 is_archived,
 is_remote,
 listing_date,
 location,
 preferred_skills,
 required_skills,
 title,
 user_id,
 company_id)

VALUES (1,
        'https://www.codeup.com',
        'The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER! The greatest job...EVER!',
        false,
        true,
        '2020-05-21 00:00:00.000000',
        'San Antonio, TX',
        null,
        null,
        'Test Job',
        1,
        1),


       (2,
        'https://www.apple.com',
        'This number 2. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        false,
        false,
        '2020-05-21 00:00:00.000000',
        'Houston, TX',
        null,
        null,
        'Test Job 2',
        1,
        2),

       (3,
        'https://www.ibm.com',
        'This number 3. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
        false,
        false,
        '2020-05-21 00:00:00.000000',
        'Atlantic City, NJ',
        null,
        null,
        'Test Job 3 with a much longer title than the other jobs',
        1,
        1),

       (4,
        'https://apple.com',
        'This number 4. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        false,
        false,
        '2020-05-21 00:00:00.000000',
        'Houston, TX',
        null,
        null,
        'Test Job Numero Quatro',
        1,
        3),

       (5,
        'https://google.com',
        'This number 5. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        false,
        false,
        '2020-05-21 00:00:00.000000',
        'Bernie, TX',
        null,
        null,
        'Test Job 5',
        1,
        1);
