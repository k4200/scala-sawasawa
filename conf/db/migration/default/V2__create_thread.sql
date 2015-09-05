drop table if exists thread;
create sequence thread_id_seq start with 1;
create table thread (
  id bigint not null default nextval('thread_id_seq') primary key,
  title varchar(40) not null,
  programmer_id bigint,
  created_timestamp timestamp not null,
  deleted_timestamp timestamp
);
