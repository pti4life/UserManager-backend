create sequence hibernate_sequence start with 6 increment by 1;
create table address (id bigint not null,city varchar(255),house_number integer,postal_code integer,street varchar(255),user_id bigint,primary key (id));
create table user (id bigint not null, email varchar(255), name varchar(255), password varchar(255), primary key (id));

alter table address add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user;
