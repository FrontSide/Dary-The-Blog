# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table entry (
  id                        bigint not null,
  title                     varchar(255),
  content                   varchar(255),
  cre_date                  timestamp,
  is_published              boolean,
  constraint pk_entry primary key (id))
;

create sequence entry_seq;




# --- !Downs

drop table if exists entry cascade;

drop sequence if exists entry_seq;

