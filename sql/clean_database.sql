------------------------------------------------------------
-- Author       |   hjkim27
-- Date         |   2024. 07. 06
-- Description  |   clean database query
------------------------------------------------------------
-- 관리자 로그인 정보
create table tb_admin_info
(
    sid           serial,
    login_id      character varying not null,
    login_pw      character varying not null,
    name          character varying,
    created_at    Timestamp without time zone default now(),
    updated_at    Timestamp without time zone default now(),
    last_login_at timestamp without time zone,
    deleted_at    timestamp without time zone
);

-- admin / privacy
insert into tb_admin_info (login_id, login_pw, name)
values ('admin', 'ca56ea33be18cc9ad3701ba63a11e676866e7deefd42a27d579f4284b6e064f0', '관리자');

-- 설정정보
create table tb_setting_info
(
    sid         serial,
    key         character varying unique not null,
    value       character varying,
    type        character varying,
    description character varying
);

-- repository
create table tb_project_repository
(
    sid         serial,
    name        character varying(100) not null,               -- commit.owner.name
    full_name   character varying(100) not null,               -- commit.owner.fullName
    description character varying,                             -- commit.owner.description
    privacy     boolean                         default false, -- commit.owner.private
    html_url    character varying,                             -- commit.owner.htmlUrl
    ssh_url     character varying,                             -- commit.owner.sshUrl
    active      boolean                not null default true
);

-- owner
create table tb_project_owner_info
(
    sid      serial,
    login    character varying(100) not null, -- commit.owner.owner.login
    name     character varying(100) not null, -- commit.owner.owner.name
    html_url character varying(100) not null  -- commit.owner.owner.htmlUrl
);

--  commit 내역
create table tb_project_commit
(
    sid             serial,
    repository_sid  integer                       not null, -- tb_project_repository.sid
    sha             character varying(100) unique not null, -- commit.tree.sha
    parent_sha      character varying(100) unique not null, -- commit.parentSHA1s(0)
    committer_name  character varying(50)         not null, -- commit.commitShortInfo.committer.name
    committer_email character varying(50)         not null, -- commit.commitShortInfo.committer.email
    committer_date  timestamp with time zone      not null, -- commit.commitShortInfo.committer.date
    message         text                          not null, -- commit.commitShortInfo.message
    html_url        character varying(255) unique not null  -- commit.htmlUrl

);

-- labels
create table tb_project_label
(
    sid         serial,
    label_id    bigint unique                 not null, -- commit.owner.listLabels(n).id
    name        character varying(100) unique not null, -- commit.owner.listLabels(n).name
    description character varying(100)        not null, -- commit.owner.listLabels(n).description
    color       character varying(15)         not null, -- commit.owner.listLabels(n).color
    active      boolean                       not null default true
);

-- issue
-- commit.getOwner().getIssue(GHIssueState.ALL) > for
create table tb_project_issue
(
    sid            serial,
    repository_sid integer              not null,                -- tb_project_repository.sid
    label_ids      character varying,                            -- tb_project_label.label_id.join(',')
    state          character varying(6) not null default 'OPEN', -- issue.state
    issue_number   integer              not null,                -- issue.number
    title          character varying    not null,                -- issue.title
    body           character varying                             -- issue.body
);

-- comment
-- commit.getOwner().getComments() > for
create table tb_project_comment
(
    sid               serial,
    issue_number      Integer           not null, -- tb_project_issue.number
    comment_id        character varying not null, -- comment.id
    body              text              not null, -- comment.body
    parent_comment_id character varying,          -- comment.parent.id
    created_at        timestamp with time zone,   -- comment.createdAt
    updated_at        timestamp with time zone,   -- comment.updatedAt
    active            boolean           not null default true
);