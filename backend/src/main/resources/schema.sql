create sequence pessoas_seq start with 1 increment by 50;

    create table pessoas (
       id bigint not null,
        idade integer not null,
        nome varchar(255),
        posicao integer not null,
        primary key (id)
    );
