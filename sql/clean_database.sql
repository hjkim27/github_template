------------------------------------------------------------
-- Author       |   hjkim27
-- Date         |   2024. 07. 06
-- Description  |   clean database query
------------------------------------------------------------
-- 관리자 로그인 정보
create table admin_info(
    sid serial,
    login_id character varying not null,
    login_pw character varying not null,
    name character varying,
    created_at Timestamp without time zone default now(),
    updated_at Timestamp without time zone default now(),
    last_login_at timestamp without time zone,
    deleted_at timestamp without time zone

);

insert into admin_info (login_id, login_pw, name) values ('admin', 'privacy', '관리자');