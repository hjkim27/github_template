------------------------------------------------------------
-- Author       |   hjkim27
-- Date         |   2024. 09. 26
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

-- owner
create table tb_project_owner_info
(
    sid        serial,
    gh_id      bigint,                   -- commit.owner.owner.id
    name       character varying,        -- commit.owner.owner.name
    email      character varying,        -- commit.owner.owner.email
    login      character varying,        -- commit.owner.owner.login
    html_url   character varying,        -- commit.owner.owner.htmlUrl.toString
    url        character varying,        -- commit.owner.owner.url.toString
    created_at timestamp with time zone, -- commit.owner.owner.createdAt
    updated_at timestamp with time zone  -- commit.owner.owner.updatedAt
);


-- repository
create table tb_project_repository
(
    sid         serial,
    gh_id       bigint,                   -- commit.owner.id
    name        character varying,        -- commit.owner.name
    full_name   character varying,        -- commit.owner.fullName
    description character varying,        -- commit.owner.description
    gh_private  boolean,                  -- commit.owner.private
    language    character varying,        -- commit.owner.language
    html_url    character varying,        -- commit.owner.htmlUrl.toString
    ssh_url     character varying,        -- commit.owner.sshUrl.toString
    url         character varying,        -- commit.owner.url.toString
    created_at  timestamp with time zone, -- commit.owner.createdAt
    updated_at  timestamp with time zone, -- commit.owner.updatedAt
    gh_owner_id bigint,                   -- commit.owner.owner.id

    active      boolean                   -- repository 삭제여부
);

-- issue
-- commit.getOwner().getIssue(GHIssueState.ALL) > for
create table tb_project_issue
(
    sid              serial,
    gh_id            long,                     -- commit.owner.issues > id
    issue_number     integer,                  -- commit.owner.issues > number
    title            character varying,        -- commit.owner.issues > title
    body             character varying,        -- commit.owner.issues > body
    state            character varying,        -- commit.owner.issues > state
    html_url         character varying,        -- commit.owner.issues > htmlUrl.toString
    url              character varying,        -- commit.owner.issues > url.toString
    created_at       timestamp with time zone, -- commit.owner.issues > createdAt
    updated_at       timestamp with time zone, -- commit.owner.issues > updatedAt
    closed_at        timestamp with time zone, -- commit.owner.issues > closedAt
    gh_repository_id bigint,                   -- commit.owner.issues > repository.id

    label_ids        character varying,        -- commit.owner.issues > labels
    active           boolean
);

-- event
create table tb_project_event
(
    sid             serial,
    gh_id           bigint,                  -- commit.owner.issues > events > id
    gh_issue_number integer,                 -- commit.owner.issues > events > issue.number
    gh_actor_login  character varying,       -- commit.owner.issues > events > actor.login
    event           character varying,       -- commit.owner.issues > events > event
    commit_id       character varying,       -- commit.owner.issues > events > commitId
    commit_url      character varying,       -- commit.owner.issues > events > commitUrl
    url             character varying,       -- commit.owner.issues > events > url
    created_at      timestamp with time zone -- commit.owner.issues > events > createdAt
)


-- comment
-- commit.getOwner().getComments() > for
create table tb_project_comment
(
    sid               serial,
    issue_number      Integer not null,         -- tb_project_issue.number
    comment_id        bigint  not null,         -- comment.id
    body              text    not null,         -- comment.body
    parent_comment_id character varying,        -- comment.parent.id
    created_at        timestamp with time zone, -- comment.createdAt
    updated_at        timestamp with time zone, -- comment.updatedAt
    active            boolean not null default true
);




---------------------------------------------------------------

--  commit 내역
create table tb_project_commit
(
    sid                serial,
    gh_repository_id   bigint,
    sha                character varying,
    parent_sha         character varying,
    commit_date        timestamp with time zone,
    message            character varying,
    commit_count       integer,
    committer_name     character varying,
    committer_username character varying,
    committer_email    character varying,
    committer_date     character varying,
    html_url           character varying,
    url                character varying
);

-- labels
create table tb_project_label
(
    sid              serial,
    gh_repository_id bigint,
    gh_label_id      bigint,
    name             character varying,
    description      character varying,
    color            character varying,
    url              character varying
);

