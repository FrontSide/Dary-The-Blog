# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  title                     varchar(255),
  content                   text,
  user_id                   bigint,
  cre_date                  timestamp,
  is_published              boolean,
  is_archived               boolean,
  constraint pk_post primary key (id))
;

create table blog_user (
  id                        bigint not null,
  blogname                  varchar(255),
  password                  varchar(255),
  firstname                 varchar(255),
  lastname                  varchar(255),
  email                     varchar(255),
  register_date             timestamp,
  constraint pk_blog_user primary key (id))
;

create table user_log (
  uuid                      bigint not null,
  user_id                   bigint,
  login_date                timestamp,
  constraint pk_user_log primary key (uuid))
;

create sequence post_seq;

create sequence blog_user_seq;

create sequence user_log_seq;

alter table post add constraint fk_post_user_1 foreign key (user_id) references blog_user (id);
create index ix_post_user_1 on post (user_id);
alter table user_log add constraint fk_user_log_user_2 foreign key (user_id) references blog_user (id);
create index ix_user_log_user_2 on user_log (user_id);



# --- !Downs

drop table if exists post cascade;

drop table if exists blog_user cascade;

drop table if exists user_log cascade;

drop sequence if exists post_seq;

drop sequence if exists blog_user_seq;

drop sequence if exists user_log_seq;

