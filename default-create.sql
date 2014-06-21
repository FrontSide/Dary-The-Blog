create table entry (
  id                        bigint not null,
  title                     varchar(255),
  content                   varchar(255),
  cre_date                  timestamp,
  is_published              boolean,
  constraint pk_entry primary key (id))
;

create sequence entry_seq;



