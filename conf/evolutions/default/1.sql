
# --- !Ups

create table entry (
  id                        bigint not null,
  post_title                varchar(255),
  post_content              text,
  cre_date                  timestamp,
  is_published              boolean,
  constraint pk_entry primary key (id))
;

create sequence entry_seq;




# --- !Downs


