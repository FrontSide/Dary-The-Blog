# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article_comment (
  id                        bigint not null,
  post_id                   bigint,
  content                   text,
  user_id                   bigint,
  is_deleted                boolean,
  cre_date                  timestamp,
  constraint pk_article_comment primary key (id))
;

create table picture (
  id                        bigint not null,
  user_id                   bigint,
  constraint pk_picture primary key (id))
;

create table post (
  id                        bigint not null,
  title                     varchar(255),
  content                   text,
  user_id                   bigint,
  cre_date                  timestamp,
  is_published              boolean,
  is_archived               boolean,
  is_featured               boolean,
  root_post_id              bigint,
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
  profile_picture_id        bigint,
  constraint pk_blog_user primary key (id))
;

create table user_log (
  uuid                      bigint not null,
  user_id                   bigint,
  login_date                timestamp,
  constraint pk_user_log primary key (uuid))
;

create sequence article_comment_seq;

create sequence picture_seq;

create sequence post_seq;

create sequence blog_user_seq;

create sequence user_log_seq;

alter table article_comment add constraint fk_article_comment_post_1 foreign key (post_id) references post (id);
create index ix_article_comment_post_1 on article_comment (post_id);
alter table article_comment add constraint fk_article_comment_user_2 foreign key (user_id) references blog_user (id);
create index ix_article_comment_user_2 on article_comment (user_id);
alter table picture add constraint fk_picture_user_3 foreign key (user_id) references blog_user (id);
create index ix_picture_user_3 on picture (user_id);
alter table post add constraint fk_post_user_4 foreign key (user_id) references blog_user (id);
create index ix_post_user_4 on post (user_id);
alter table post add constraint fk_post_rootPost_5 foreign key (root_post_id) references post (id);
create index ix_post_rootPost_5 on post (root_post_id);
alter table blog_user add constraint fk_blog_user_profilePicture_6 foreign key (profile_picture_id) references picture (id);
create index ix_blog_user_profilePicture_6 on blog_user (profile_picture_id);
alter table user_log add constraint fk_user_log_user_7 foreign key (user_id) references blog_user (id);
create index ix_user_log_user_7 on user_log (user_id);



# --- !Downs

drop table if exists article_comment cascade;

drop table if exists picture cascade;

drop table if exists post cascade;

drop table if exists blog_user cascade;

drop table if exists user_log cascade;

drop sequence if exists article_comment_seq;

drop sequence if exists picture_seq;

drop sequence if exists post_seq;

drop sequence if exists blog_user_seq;

drop sequence if exists user_log_seq;

