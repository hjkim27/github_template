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
    last_login_at timestamp without time zone
);

-- 실시간 로그 기록
create table classify_log (
    sid serial,
    app_type character varying,
    status integer,
    result boolean,
    score integer,
    threshold integer,
    created_at timestamp without time zone default now(),
    token character varying
);

-- 지난 로그 통계용 기록
create table classify_log_cache (
    sid serial,
    app_type character varying,
    status integer,
    result boolean,
    created_at timestamp without time zone default now()
);

-- 설정
create table setting_info (
    sid serial,
    key character varying unique not null,
    value character varying,
    description character varying
);