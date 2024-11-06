------------------------------------------------------------
-- Author       |   hjkim27
-- Date         |   2024. 09. 26
-- Description  |   clean database query
------------------------------------------------------------
-- 관리자 로그인 정보
create table admin_info
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
insert into admin_info (login_id, login_pw, name)
values ('admin', 'ca56ea33be18cc9ad3701ba63a11e676866e7deefd42a27d579f4284b6e064f0', '관리자');

-- 설정정보
create table setting_info
(
    sid         serial,
    key         character varying unique not null,
    value       character varying,
    type        character varying,
    description character varying
);

-- owner
-- commit.owner.owner
create table gh_owner_info
(
    sid        serial,
    gh_id      bigint unique not null,   -- > id
    name       character varying,        -- > name
    email      character varying,        -- > email
    login      character varying,        -- > login
    html_url   character varying,        -- > htmlUrl.toString
    url        character varying,        -- > url.toString
    created_at timestamp with time zone, -- > createdAt
    updated_at timestamp with time zone  -- > updatedAt
);


-- repository
-- commit.owner
create table gh_repository
(
    sid         serial,
    gh_id       bigint unique not null,   -- > id
    name        character varying,        -- > name
    full_name   character varying,        -- > fullName
    description character varying,        -- > description
    gh_private  boolean,                  -- > private
    language    character varying,        -- > language
    html_url    character varying,        -- > htmlUrl.toString
    ssh_url     character varying,        -- > sshUrl.toString
    url         character varying,        -- > url.toString
    created_at  timestamp with time zone, -- > createdAt
    updated_at  timestamp with time zone, -- > updatedAt
    owner_sid   integer,                  -- > owner.id
    active      boolean default true      -- repository 삭제여부
);

-- issue
-- commit.getOwner().getIssue(GHIssueState.ALL) > for
create table gh_issue
(
    sid            serial,
    gh_id          bigint unique not null,   -- > id
    issue_number   integer,                  -- > number
    title          character varying,        -- > title
    body           character varying,        -- > body
    state          character varying,        -- > state
    pull_request   boolean default false,    -- > pullRequest
    locked         boolean default false,    -- > locked
    html_url       character varying,        -- > htmlUrl.toString
    url            character varying,        -- > url.toString
    created_at     timestamp with time zone, -- > createdAt
    updated_at     timestamp with time zone, -- > updatedAt
    closed_at      timestamp with time zone, -- > closedAt
    repository_sid integer,                  -- > repository.id

    label_ids      character varying         -- commit.owner.issues > labels
);

-- event
-- commit.owner.issues > events
create table gh_event
(
    sid            serial,
    gh_id          bigint unique not null,   -- > id
    gh_actor_login character varying,        -- > actor.login
    event          character varying,        -- > event
    commit_id      character varying,        -- > commitId
    commit_url     character varying,        -- > commitUrl
    url            character varying,        -- > url
    created_at     timestamp with time zone, -- > createdAt
    issue_sid      bigint,                   -- > issue.id
    repository_sid bigint                    -- > repository.id
);


-- comment
-- commit.owner.issues > comments
create table gh_comment
(
    sid            serial,
    gh_id          bigint unique not null,             -- > comments > id
    body           text,                               -- > body
    parent_id      bigint,                             -- > parent.id
    created_at     timestamp with time zone,           -- > createdAt
    updated_at     timestamp with time zone,           -- > updatedAt
    html_url       character varying,                  -- > htmlUrl
    url            character varying,                  -- > url
    owner_sid      integer,                            -- > user.id
    issue_sid      integer,                            -- commit.owner.issues > id
    repository_sid bigint,                             -- > repository.id
    active         boolean       not null default true -- comment 삭제여부
);


--  commit 내역
create table gh_commit
(
    sid                serial,
    sha                character varying,        -- commit.tree.sha
    parent_sha         character varying,        -- commit.parentSHA1
    commit_date        timestamp with time zone, -- commit.commitDate
    comment_count      integer,                  -- commit.commitShortInfo.commentCount
    message            character varying,        -- commit.commitShortInfo.message
    html_url           character varying,        -- commit.htmlUrl
    url                character varying,        -- commit.url
    committer_name     character varying,        -- commit.commitShortInfo.committer.name
    committer_username character varying,        -- commit.commitShortInfo.committer.username
    committer_email    character varying,        -- commit.commitShortInfo.committer.email
    committer_date     timestamp with time zone, -- commit.commitShortInfo.committer.date
    repository_sid     bigint                    -- commit.owner.id
);

-- labels
-- commit.owner.listLabels
create table gh_label
(
    sid            serial,
    gh_id          bigint,                       -- > id
    name           character varying,            -- > name
    description    character varying,            -- > description
    color          character varying,            -- > color
    url            character varying,            -- > url
    repository_sid integer,                      -- commit.owner.id
    active         boolean not null default true -- label 삭제여부
);

