Сергей Егоров, [07.10.21 00:01]
CREATE TABLE worker (
id INTEGER NOT NULL PRIMARY KEY,
id_position INT NOT NULL,
id_skill INT NOT NULL,
fam VARCHAR(50) NOT NULL,
im VARCHAR(50) NOT NULL,
otch VARCHAR(50) NOT NULL,
FOREIGN KEY(id_position) REFERENCES  position(id),
FOREIGN KEY(id_skill) REFERENCES skill(id));
/**
@table: worker
@description: Работник производства
*/

CREATE TABLE production_order (
id INTEGER NOT NULL PRIMARY KEY,
id_product INTEGER NOT NULL,
dt_order TIMESTAMPTZ NOT NULL,
dt_order_end INTEGER NOT NULL,
FOREIGN KEY(id_product) REFERENCES product(id));
/**
@table: production_order
@description: Заказ на производстве
*/

CREATE TABLE product (
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL);
/**
@table: product
@description: продукт производства
*/

CREATE TABLE stage_work (
id INTEGER NOT NULL PRIMARY KEY,
type INTEGER NOT NULL,
dt_end TIMESTAMPTZ NOT NULL);
/**
@table: stage_work
@description: этап работы
*/

CREATE TABLE product_order_stage_work (
id_production_order INTEGER NOT NULL,
id_stage_work INTEGER NOT NULL,
FOREIGN KEY(id_production_order) REFERENCES production_order(id),
FOREIGN KEY(id_stage_work) REFERENCES stage_work(id));
/**
@table: product_order_stage_work
@description: связь этапа работы с заказом
@columnsDescription:  id_production_order() id_stage_work()
*/

CREATE TABLE worker_stage_work (
id_worker INTEGER NOT NULL,
id_stage_work INTEGER NOT NULL,
FOREIGN KEY(id_worker) REFERENCES worker(id),
FOREIGN KEY(id_stage_work) REFERENCES stage_work(id));
/**
@table: worker_stage_work
@description: связь работника с этапом работы
*/

CREATE TABLE skill (
id INT NOT NULL PRIMARY KEY,
name INT NOT NULL);
/**
@table: skill
@description: навык
*/

CREATE TABLE  position (
id INT NOT NULL PRIMARY KEY,
dt_in DATETIME NOT NULL,
name VARCHAR(45) NOT NULL);
/**
@table:  position
@description: должность
*/