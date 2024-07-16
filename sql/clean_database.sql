------------------------------------------------------------
-- Author       |   hjkim27
-- Date         |   2024. 07. 06
-- Description  |   clean database query
------------------------------------------------------------
-- 관리자 로그인 정보
create table tb_admin_info(
    sid serial,
    login_id character varying not null,
    login_pw character varying not null,
    name character varying,
    created_at Timestamp without time zone default now(),
    updated_at Timestamp without time zone default now(),
    last_login_at timestamp without time zone,
    deleted_at timestamp without time zone

);

insert into tb_admin_info (login_id, login_pw, name) values ('admin', 'privacy', '관리자');

-- 설정정보
create table tb_setting_info {
    sid serial,
    key character varying unique not null,
    value character varying,
    type character varying,
    description character varying,
    admin_sid integer not null
};

-- repository
create table tb_project_repository {
    sid serial,
    repo_name character varying(100) not null
    description character varying,
    repo_type character varying(10) default 'public',
    admin_sid integer not null
};

-- branch
create table tb_repository_branch {
    sid serial,
    branch_name character varying(100) not null,
    description chracter varying,
    repository_sid int not null
};

-- issue
create table tb_repository_issue {
    sid serial,
    labels character varying,
    subject character varying,
    content character varying(10000),
    repository_sid int not null
};

-- label
create table tb_repository_label {
    sid serial,
    label_name character varying(20) unique not null,
    description character varying,
    label_color character varying(20) not null,
    label_bg_color character varying(20) not null
};

