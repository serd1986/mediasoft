create table working.role
(
    id   int primary key,
    code varchar not null
);

insert into working.role
values
       (1, 'admin'),
       (2, 'user');

create table working.worker_role
(
    id      serial primary key,
    worker_id int not null
        references working.worker (id),
    role_id int not null
        references working.role (id)
);
