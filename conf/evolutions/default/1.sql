# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  title                     varchar(255),
  content                   text,
  cre_date                  timestamp,
  is_published              boolean,
  constraint pk_post primary key (id))
;

create table blog_user (
  id                        bigint not null,
  blogname                  varchar(255),
  firstname                 varchar(255),
  lastname                  varchar(255),
  email                     varchar(255),
  register_date             timestamp,
  constraint pk_blog_user primary key (id))
;

create sequence post_seq;

create sequence blog_user_seq;




# --- !Downs

drop table if exists post cascade;

drop table if exists blog_user cascade;

drop sequence if exists post_seq;

drop sequence if exists blog_user_seq;

