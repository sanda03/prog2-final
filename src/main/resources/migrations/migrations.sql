\c postgres

CREATE DATABASE prog2;

\c prog2

CREATE TABLE "author"(
  "id_author" SERIAL PRIMARY KEY,
  "name" VARCHAR(200) NOT NULL,
  "first_name" VARCHAR(200) NOT NULL
);

CREATE TABLE "category"(
  "id_category" SERIAL PRIMARY KEY,
  "name" VARCHAR(200) NOT NULL
);

CREATE TABLE "member"(
  "id_member" SERIAL PRIMARY KEY,
  "name" VARCHAR(200) NOT NULL,
  "first_name" VARCHAR(200) NOT NULL
);

CREATE TABLE "book"(
  "id_book" SERIAL PRIMARY KEY,
  "title" VARCHAR(200) NOT NULL,
  "description" TEXT NOT NULL
);

CREATE TABLE "borrow"(
  "id_borrow" SERIAL PRIMARY KEY,
  "id_book" INT REFERENCES "book"("id_book") NOT NULL,
  "id_member" INT REFERENCES "member"("id_member") NOT NULL,
  "start_date" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  "end_date" TIMESTAMP NOT NULL,
  "is_returned" BOOLEAN DEFAULT FALSE NOT NULL
);