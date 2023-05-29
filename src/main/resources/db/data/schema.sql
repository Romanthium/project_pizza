DROP SCHEMA IF EXISTS pizza_cafe_db CASCADE;

CREATE SCHEMA IF NOT EXISTS pizza_cafe_db
    AUTHORIZATION postgres;

-- Table: pizza_cafe_db.userinfo
DROP TABLE IF EXISTS pizza_cafe_db.userinfo;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.userinfo
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    login character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    user_role character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_userinfo_id PRIMARY KEY (id),
    CONSTRAINT userinfo_login_login1_key UNIQUE (login)
        INCLUDE(login)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.userinfo
    OWNER to postgres;

-- Table: pizza_cafe_db.cafe
DROP TABLE IF EXISTS pizza_cafe_db.cafe;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.cafe
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default" NOT NULL,
    phone character varying COLLATE pg_catalog."default" NOT NULL,
    manager_id integer,
    CONSTRAINT pk_cafe_id PRIMARY KEY (id),
    CONSTRAINT cafe_name_key UNIQUE (name),
    CONSTRAINT cafe_manager_id_fkey FOREIGN KEY (manager_id)
        REFERENCES pizza_cafe_db.userinfo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.cafe
    OWNER to postgres;

-- Table: pizza_cafe_db.unit
DROP TABLE IF EXISTS pizza_cafe_db.unit;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.unit
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT pk_unit_id PRIMARY KEY (id),
    CONSTRAINT unit_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.unit
    OWNER to postgres;

-- Table: pizza_cafe_db.dish_type
DROP TABLE IF EXISTS pizza_cafe_db.dish_type;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.dish_type
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_dish_type_id PRIMARY KEY (id),
    CONSTRAINT dish_type_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.dish_type
    OWNER to postgres;

-- Table: pizza_cafe_db.ingredient
DROP TABLE IF EXISTS pizza_cafe_db.ingredient;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.ingredient
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_ingredient_id PRIMARY KEY (id),
    CONSTRAINT ingredient_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.ingredient
    OWNER to postgres;

-- Table: pizza_cafe_db.dish
DROP TABLE IF EXISTS pizza_cafe_db.dish;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.dish
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    size integer NOT NULL,
    unit integer NOT NULL,
    price numeric(6,2) NOT NULL,
    type_id integer NOT NULL,
    CONSTRAINT pk_dish_id PRIMARY KEY (id),
    CONSTRAINT dish_name_key UNIQUE (name),
    CONSTRAINT fk_dish_dish_type FOREIGN KEY (type_id)
        REFERENCES pizza_cafe_db.dish_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_dish_unit FOREIGN KEY (unit)
        REFERENCES pizza_cafe_db.unit (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.dish
    OWNER to postgres;

-- Table: pizza_cafe_db.cafe_dish
DROP TABLE IF EXISTS pizza_cafe_db.cafe_dish;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.cafe_dish
(
    id_cafe integer NOT NULL,
    id_dish integer NOT NULL,
    CONSTRAINT pk_cafe_dish PRIMARY KEY (id_dish, id_cafe),
    CONSTRAINT fk_cafe_dish_cafe FOREIGN KEY (id_cafe)
        REFERENCES pizza_cafe_db.cafe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_cafe_dish_dish FOREIGN KEY (id_dish)
        REFERENCES pizza_cafe_db.dish (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.cafe_dish
    OWNER to postgres;

-- Table: pizza_cafe_db.dish_ingredient
DROP TABLE IF EXISTS pizza_cafe_db.dish_ingredient;

CREATE TABLE IF NOT EXISTS pizza_cafe_db.dish_ingredient
(
    id_dish integer NOT NULL,
    id_ingredient integer NOT NULL,
    CONSTRAINT pk_dish_ingredient PRIMARY KEY (id_dish, id_ingredient),
    CONSTRAINT fk_dish_ingredient_dish FOREIGN KEY (id_dish)
        REFERENCES pizza_cafe_db.dish (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_dish_ingredient_ingredient FOREIGN KEY (id_ingredient)
        REFERENCES pizza_cafe_db.ingredient (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS pizza_cafe_db.dish_ingredient
    OWNER to postgres;