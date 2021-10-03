CREATE TABLE working.worker (
id INTEGER NOT NULL PRIMARY KEY,
fam VARCHAR(50) NOT NULL,
im VARCHAR(50) NOT NULL,
otch VARCHAR(50) NOT NULL,
make_work INTEGER NOT NULL,
position INTEGER NOT NULL);
/**
@table: worker
@description: Работник производства
*/

CREATE TABLE working.product (
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL);
/**
@table: product
@description: продукт производства
*/

CREATE TABLE working.production_order (
id INTEGER NOT NULL PRIMARY KEY,
id_product INTEGER NOT NULL,
dt_order TIMESTAMPTZ NOT NULL,
dt_order_end INTEGER NOT NULL,
FOREIGN KEY(id_product) REFERENCES working.product(id));
/**
@table: production_order
@description: Заказ на производстве
*/

CREATE TABLE working.product_order_worker (
id_produkt_order INTEGER NOT NULL,
id_worker INTEGER NOT NULL,
FOREIGN KEY(id_produkt_order) REFERENCES working.production_order(id),
FOREIGN KEY(id_worker) REFERENCES working.worker(id));

CREATE TABLE working.stage_work (
id INTEGER NOT NULL PRIMARY KEY,
type INTEGER NOT NULL,
dt_end TIMESTAMPTZ NOT NULL);

CREATE TABLE working.product_order_stage_work (
id_stage_work INTEGER NOT NULL,
id_production_order INTEGER NOT NULL,
FOREIGN KEY(id_production_order) REFERENCES working.production_order(id),
FOREIGN KEY(id_stage_work) REFERENCES working.stage_work(id));
/**
@table: product_order_stage_work
@columnsDescription:  id_stage_work() id_production_order()
*/

CREATE TABLE working.worker_stage_work (
id_worker INTEGER NOT NULL,
id_stage_work INTEGER NOT NULL,
FOREIGN KEY(id_worker) REFERENCES working.worker(id),
FOREIGN KEY(id_stage_work) REFERENCES working.stage_work(id));
/**
@table: worker_stage_work
@description: связь работника с этапом работы
*/