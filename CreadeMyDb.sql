-- Database: booksdb

-- DROP DATABASE IF EXISTS booksdb;

CREATE DATABASE booksdb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

-- Table: public.acts

-- DROP TABLE IF EXISTS public.acts;

CREATE TABLE IF NOT EXISTS public.acts
(
    finish_date timestamp without time zone,
    start_date timestamp without time zone,
    id integer NOT NULL DEFAULT nextval('acts_id_seq'::regclass),
    reader_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    book_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT acts_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.acts
    OWNER to postgres;

-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id integer NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default",
    year integer,
    publisher character varying(255) COLLATE pg_catalog."default",
    genre character varying(255) COLLATE pg_catalog."default",
    count integer,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;

-- Table: public.librarians

-- DROP TABLE IF EXISTS public.librarians;

CREATE TABLE IF NOT EXISTS public.librarians
(
    id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT librarians_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.librarians
    OWNER to postgres;

-- Table: public.readers

-- DROP TABLE IF EXISTS public.readers;

CREATE TABLE IF NOT EXISTS public.readers
(
    id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT readers_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.readers
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- SEQUENCE: public.acts_id_seq

-- DROP SEQUENCE IF EXISTS public.acts_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.acts_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 1234567
    CACHE 1
    OWNED BY acts.id;

ALTER SEQUENCE public.acts_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.books_id_seq

-- DROP SEQUENCE IF EXISTS public.books_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.books_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 123456789
    CACHE 1
    OWNED BY books.id;

ALTER SEQUENCE public.books_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.users_id_seq

-- DROP SEQUENCE IF EXISTS public.users_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 123456789
    CACHE 1;

ALTER SEQUENCE public.users_id_seq
    OWNER TO postgres;