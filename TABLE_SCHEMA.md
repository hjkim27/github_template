## public.tb_admin_info

| column name   | type                        | Collation | nullable | default                                    |
|---------------|-----------------------------|-----------|----------|--------------------------------------------|
| sid           | integer                     |           | not null | nextval('tb_admin_info_sid_seq'::regclass) |
| login_id      | character varying           |           | not null |                                            |
| login_pw      | character varying           |           | not null |                                            |
| name          | character varying           |           |          |                                            |
| created_at    | timestamp without time zone |           |          | now()                                      |
| updated_at    | timestamp without time zone |           |          | now()                                      |
| last_login_at | timestamp without time zone |           |          |                                            |
| deleted_at    | timestamp without time zone |           |          |                                            |

## public.tb_setting_info

| column name | type              | Collation | nullable | default                                      |
|-------------|-------------------|-----------|----------|----------------------------------------------|
| sid         | integer           |           | not null | nextval('tb_setting_info_sid_seq'::regclass) |
| key         | character varying |           | not null |                                              |
| value       | character varying |           |          |                                              |
| type        | character varying |           |          |                                              |
| description | character varying |           |          |                                              |
| admin_sid   | integer           |           | not null |                                              |

- Indexs
    - "tb_setting_info_key_key" UNIQUE CONSTRAINT, btree (key)

## public.tb_repository_branch

| column name    | type                   | Collation | nullable | default                                           |
|----------------|------------------------|-----------|----------|---------------------------------------------------|
| sid            | integer                |           | not null | nextval('tb_repository_branch_sid_seq'::regclass) |
| branch_name    | character varying(100) |           | not null |                                                   |
| description    | character varying      |           |          |                                                   |
| repository_sid | integer                |           | not null |                                                   |

## public.tb_repository_issue

| column name    | type                     | Collation | nullable | default                                          |
|----------------|--------------------------|-----------|----------|--------------------------------------------------|
| sid            | integer                  |           | not null | nextval('tb_repository_issue_sid_seq'::regclass) |
| labels         | character varying        |           |          |                                                  |
| subject        | character varying        |           |          |                                                  |
| content        | character varying(10000) |           |          |                                                  |
| repository_sid | integer                  |           | not null |                                                  |

## public.tb_repository_label

| column name    | type                  | Collation | nullable | default                                          |
|----------------|-----------------------|-----------|----------|--------------------------------------------------|
| sid            | integer               |           | not null | nextval('tb_repository_label_sid_seq'::regclass) |
| label_name     | character varying(20) |           | not null |                                                  |
| description    | character varying     |           |          |                                                  |
| label_color    | character varying(20) |           | not null |                                                  |
| label_bg_color | character varying(20) |           | not null |                                                  |

- Indexs:
    - "tb_repository_label_label_name_key" UNIQUE CONSTRAINT, btree (label_name)
