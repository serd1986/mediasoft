CREATE TABLE working.worker (
id INTEGER NOT NULL PRIMARY KEY,
fam VARCHAR(50) NOT NULL,
im VARCHAR(50) NOT NULL,
otch VARCHAR(50) NOT NULL,
email VARCHAR(20) NOT NULL);

CREATE TABLE working.product (
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL);

CREATE TABLE working.production_order (
id INTEGER NOT NULL PRIMARY KEY,
id_product INTEGER NOT NULL,
dt_order TIMESTAMPTZ NOT NULL,
dt_order_end TIMESTAMPTZ NOT NULL,
FOREIGN KEY(id_product) REFERENCES product(id));

CREATE TABLE working.stage_work (
id INTEGER NOT NULL PRIMARY KEY,
type VARCHAR(45) NOT NULL,
dt_end TIMESTAMPTZ NOT NULL);

CREATE TABLE working.product_order_stage_work (
id_production_order INTEGER NOT NULL,
id_stage_work INTEGER NOT NULL,
FOREIGN KEY(id_production_order) REFERENCES production_order(id),
FOREIGN KEY(id_stage_work) REFERENCES stage_work(id));

CREATE TABLE working.worker_stage_work (
id_worker INTEGER NOT NULL,
id_stage_work INTEGER NOT NULL,
FOREIGN KEY(id_worker) REFERENCES worker(id),
FOREIGN KEY(id_stage_work) REFERENCES stage_work(id));

CREATE TABLE working.skill (
id INT NOT NULL PRIMARY KEY,
id_worker INTEGER NOT NULL,
name VARCHAR(45) NOT NULL,
FOREIGN KEY(id_worker) REFERENCES worker(id));

CREATE TABLE  working.position (
id INT NOT NULL PRIMARY KEY,
id_worker INTEGER NOT NULL,
dt_in TIMESTAMPTZ NOT NULL,
name VARCHAR(45) NOT NULL,
FOREIGN KEY(id_worker) REFERENCES worker(id));

CREATE SEQUENCE working.position_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.position_id_seq
    OWNER TO postgres;

CREATE SEQUENCE working.product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.product_id_seq
    OWNER TO postgres;

CREATE SEQUENCE working.production_order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.production_order_id_seq
    OWNER TO postgres;

CREATE SEQUENCE working.skill_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.skill_id_seq
    OWNER TO postgres;

CREATE SEQUENCE working.stage_work_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.stage_work_id_seq
    OWNER TO postgres;

CREATE SEQUENCE working.worker_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE working.worker_id_seq
    OWNER TO postgres;

insert into working.worker
values (1, 'Иванов', 'Петр', 'Сергеевич', 'pivanov@mail.ru'),
       (2, 'Сидоров', 'Сергей', 'Васильевич', 'ssidorov@mail.ru');
