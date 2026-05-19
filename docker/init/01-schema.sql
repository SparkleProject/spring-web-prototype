-- PostgreSQL schema for demo application

CREATE TABLE t_code (
  id SERIAL PRIMARY KEY,
  type VARCHAR(32),
  code VARCHAR(128),
  name VARCHAR(255),
  value VARCHAR(255),
  seq INT,
  enable SMALLINT,
  description VARCHAR(255),
  UNIQUE (type, code)
);

INSERT INTO t_code VALUES (50, '7', 'TEST_CODE_1', '', 'test', 1, 1, '');
SELECT setval('t_code_id_seq', 50);

CREATE TABLE t_code_type (
  id SERIAL PRIMARY KEY,
  code VARCHAR(128) NOT NULL,
  name VARCHAR(128),
  seq INT,
  description VARCHAR(255)
);

INSERT INTO t_code_type VALUES (7, 'TEST_CODE', '', NULL, '');
SELECT setval('t_code_type_id_seq', 7);

CREATE TABLE t_department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  pid INT,
  seq INT DEFAULT 0,
  description VARCHAR(500)
);

INSERT INTO t_department VALUES (5, '', 1, NULL, 'dddddaa');
INSERT INTO t_department VALUES (20, '', 0, 0, '');
SELECT setval('t_department_id_seq', 20);

CREATE TABLE t_function (
  id SERIAL PRIMARY KEY,
  code VARCHAR(60),
  name VARCHAR(60),
  description VARCHAR(200)
);

INSERT INTO t_function VALUES (5, 'admin:staff:add', '', '');
INSERT INTO t_function VALUES (4, 'admin:staff:delete', '', '');
SELECT setval('t_function_id_seq', 10);

CREATE TABLE t_log (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP,
  logger VARCHAR(255),
  priority VARCHAR(255),
  message VARCHAR(255)
);

CREATE TABLE t_menu (
  id SERIAL PRIMARY KEY,
  pid INT NOT NULL,
  name VARCHAR(30) NOT NULL,
  url VARCHAR(100),
  target VARCHAR(20),
  relative SMALLINT,
  icon VARCHAR(100),
  seq INT DEFAULT 0,
  enable SMALLINT,
  visible SMALLINT
);

INSERT INTO t_menu VALUES (1, 0, '', '', '', NULL, NULL, 49, 0, NULL);
INSERT INTO t_menu VALUES (2, 0, '', '', NULL, NULL, NULL, 50, 1, NULL);
INSERT INTO t_menu VALUES (4, 2, '', '/admin/staff-login', NULL, NULL, NULL, 0, 1, NULL);
INSERT INTO t_menu VALUES (6, 1, '', '/admin/staff', '', NULL, '', 1, 1, NULL);
INSERT INTO t_menu VALUES (7, 1, '', '/admin/department', NULL, NULL, NULL, 2, 1, NULL);
INSERT INTO t_menu VALUES (8, 2, '', '/admin/role', NULL, NULL, NULL, 3, 1, NULL);
INSERT INTO t_menu VALUES (9, 2, '', '/admin/menu', NULL, NULL, NULL, 4, 1, NULL);
INSERT INTO t_menu VALUES (10, 2, '', '/admin/function', NULL, NULL, NULL, 5, 1, NULL);
INSERT INTO t_menu VALUES (11, 2, '', '/admin/code', '', NULL, '', 6, 1, NULL);
INSERT INTO t_menu VALUES (12, 2, '', '/admin/role-staff', NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO t_menu VALUES (16, 2, '', '/admin/task', '_self', NULL, NULL, 14, 1, NULL);
INSERT INTO t_menu VALUES (17, 2, '', '/druid/index.html', '_blank', NULL, '', 90, 1, NULL);
INSERT INTO t_menu VALUES (27, 0, '', '', '_self', NULL, '', 0, 1, NULL);
INSERT INTO t_menu VALUES (48, 27, '', '/admin/user', '_self', NULL, '', 1, 1, NULL);
INSERT INTO t_menu VALUES (31, 32, '', '/user/index', '_self', NULL, 'icon-home4', 0, 1, NULL);
INSERT INTO t_menu VALUES (32, 27, '', '/user/withdraw', '_self', NULL, '', 100, 1, NULL);
INSERT INTO t_menu VALUES (33, 32, '', '/user/password', '_self', NULL, 'icon-pencil3', 9, 1, NULL);
INSERT INTO t_menu VALUES (34, 32, '', '/user/logout', '_self', NULL, 'icon-switch2', 10, 1, NULL);
SELECT setval('t_menu_id_seq', 48);

CREATE TABLE t_role (
  id SERIAL PRIMARY KEY,
  code VARCHAR(60),
  name VARCHAR(60),
  description VARCHAR(150)
);

INSERT INTO t_role VALUES (1, 'ROLE_ADMIN', '', '');
INSERT INTO t_role VALUES (8, 'ROLE_USER', '', '');
SELECT setval('t_role_id_seq', 9);

CREATE TABLE t_role_res (
  id SERIAL PRIMARY KEY,
  role_id INT,
  res_type INT,
  res_id INT
);

INSERT INTO t_role_res VALUES (455, 8, 1, 34);
INSERT INTO t_role_res VALUES (23, 1, 2, 5);
INSERT INTO t_role_res VALUES (452, 1, 1, 17);
INSERT INTO t_role_res VALUES (451, 1, 1, 16);
INSERT INTO t_role_res VALUES (450, 1, 1, 11);
INSERT INTO t_role_res VALUES (449, 1, 1, 10);
INSERT INTO t_role_res VALUES (448, 1, 1, 9);
INSERT INTO t_role_res VALUES (447, 1, 1, 8);
INSERT INTO t_role_res VALUES (446, 1, 1, 12);
INSERT INTO t_role_res VALUES (445, 1, 1, 4);
INSERT INTO t_role_res VALUES (454, 8, 1, 33);
INSERT INTO t_role_res VALUES (444, 1, 1, 2);
INSERT INTO t_role_res VALUES (443, 1, 1, 7);
INSERT INTO t_role_res VALUES (453, 8, 1, 31);
INSERT INTO t_role_res VALUES (442, 1, 1, 6);
INSERT INTO t_role_res VALUES (441, 1, 1, 1);
INSERT INTO t_role_res VALUES (440, 1, 1, 47);
INSERT INTO t_role_res VALUES (439, 1, 1, 48);
SELECT setval('t_role_res_id_seq', 455);

CREATE TABLE t_role_staff (
  id SERIAL PRIMARY KEY,
  role_id INT,
  staff_id INT
);

INSERT INTO t_role_staff VALUES (9, 1, 4);
INSERT INTO t_role_staff VALUES (10, 2, 4);
INSERT INTO t_role_staff VALUES (11, 4, 4);
INSERT INTO t_role_staff VALUES (21, 7, 2);
INSERT INTO t_role_staff VALUES (19, 1, 1);
INSERT INTO t_role_staff VALUES (23, 8, 118);
INSERT INTO t_role_staff VALUES (24, 8, 119);
INSERT INTO t_role_staff VALUES (25, 8, 121);
INSERT INTO t_role_staff VALUES (26, 8, 122);
INSERT INTO t_role_staff VALUES (27, 8, 123);
INSERT INTO t_role_staff VALUES (28, 8, 124);
INSERT INTO t_role_staff VALUES (29, 8, 125);
INSERT INTO t_role_staff VALUES (30, 8, 120);
INSERT INTO t_role_staff VALUES (31, 8, 126);
INSERT INTO t_role_staff VALUES (32, 8, 127);
INSERT INTO t_role_staff VALUES (33, 8, 128);
INSERT INTO t_role_staff VALUES (34, 8, 129);
INSERT INTO t_role_staff VALUES (35, 8, 130);
INSERT INTO t_role_staff VALUES (36, 8, 131);
INSERT INTO t_role_staff VALUES (37, 8, 132);
INSERT INTO t_role_staff VALUES (38, 8, 134);
INSERT INTO t_role_staff VALUES (39, 8, 135);
SELECT setval('t_role_staff_id_seq', 39);

CREATE TABLE t_staff (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  login_name VARCHAR(50) NOT NULL,
  password VARCHAR(64),
  year_entry VARCHAR(4),
  year_separation VARCHAR(4),
  company_id INT,
  dept_id VARCHAR(40),
  position_id VARCHAR(32),
  sex SMALLINT,
  birthday DATE,
  mobile VARCHAR(20),
  education VARCHAR(2),
  nation VARCHAR(20),
  marital SMALLINT,
  household VARCHAR(60),
  profession VARCHAR(40),
  address VARCHAR(80),
  email VARCHAR(40),
  id_card VARCHAR(30),
  resume TEXT,
  evaluation TEXT,
  status SMALLINT,
  create_time TIMESTAMP,
  creator INT,
  modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modificator INT,
  enabled SMALLINT DEFAULT 1,
  locked SMALLINT DEFAULT 0,
  follow VARCHAR(255),
  broker VARCHAR(255),
  gmt INT
);

INSERT INTO t_staff VALUES (1, '', 'admin', 'af1d5ab483ff0b1594fe2cac49dbcf95', '', NULL, 3, '20', NULL, 1, '2012-04-24', '13800000000', '', '', 0, '', '', '', 'admin@126.com', '35042519800000000', '', '', NULL, NULL, NULL, '2018-07-21 17:33:02', NULL, 1, 0, NULL, NULL, NULL);
SELECT setval('t_staff_id_seq', 136);

CREATE TABLE t_staff_login (
  id SERIAL PRIMARY KEY,
  staff_id INT,
  login_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  login_ip VARCHAR(40)
);

CREATE INDEX idx_staff_lg_staff_id ON t_staff_login(staff_id);

CREATE TABLE t_task (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32),
  period SMALLINT,
  target_object VARCHAR(255),
  begin_date TIMESTAMP,
  end_date TIMESTAMP,
  cron_expression VARCHAR(64),
  description VARCHAR(255),
  state SMALLINT DEFAULT 0,
  create_date TIMESTAMP,
  creator VARCHAR(32),
  modify_date TIMESTAMP,
  modificator VARCHAR(32)
);

CREATE TABLE t_test (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

INSERT INTO t_test VALUES (1, 'Kevin');
INSERT INTO t_test VALUES (2, 'Jonathan');

CREATE TABLE t_user (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  login_name VARCHAR(50) NOT NULL,
  password VARCHAR(64),
  company_id INT,
  sex SMALLINT,
  birthday DATE,
  mobile VARCHAR(20),
  address VARCHAR(80),
  email VARCHAR(40),
  id_card VARCHAR(30),
  create_time TIMESTAMP,
  creator INT,
  modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modificator INT,
  enabled SMALLINT DEFAULT 1,
  locked SMALLINT DEFAULT 0
);

INSERT INTO t_user VALUES (141, 'lin', 'kevin', NULL, NULL, 1, '2018-07-12', '13559118729', 'xxx', 'kevinlin@126.com', '35042519800000000', NULL, NULL, '2018-07-21 17:45:59', NULL, 1, 0);
SELECT setval('t_user_id_seq', 141);

CREATE TABLE t_user_login (
  id SERIAL PRIMARY KEY,
  user_id VARCHAR(64),
  user_name VARCHAR(64),
  session_id VARCHAR(128),
  state INT,
  ip VARCHAR(64),
  country VARCHAR(64),
  area VARCHAR(64),
  region VARCHAR(64),
  city VARCHAR(64),
  isp VARCHAR(64),
  addr VARCHAR(128),
  login_time TIMESTAMP
);

CREATE TABLE t_company (
  id SERIAL PRIMARY KEY,
  code VARCHAR(60),
  name VARCHAR(100),
  domain VARCHAR(100),
  website VARCHAR(200),
  summary VARCHAR(500),
  logo VARCHAR(200),
  icon VARCHAR(200),
  favicon VARCHAR(200),
  keywords VARCHAR(255),
  description VARCHAR(500),
  province VARCHAR(60),
  city VARCHAR(60),
  address VARCHAR(200),
  coordinate VARCHAR(100),
  email VARCHAR(60),
  tel VARCHAR(30),
  seq INT
);

CREATE TABLE t_product (
  id SERIAL PRIMARY KEY,
  company_code VARCHAR(60),
  title VARCHAR(200),
  name VARCHAR(100),
  profit VARCHAR(100),
  "require" VARCHAR(255),
  content TEXT,
  insert_time TIMESTAMP,
  update_time TIMESTAMP,
  seq INT
);