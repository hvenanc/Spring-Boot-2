create table usuarios(

    id bigint not null auto_increment,
    login varchar(255) not null,
    senha varchar(32) not null,

    primary key (id)

);