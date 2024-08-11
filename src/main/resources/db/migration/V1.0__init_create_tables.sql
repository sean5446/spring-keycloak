CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" varchar not null,
  "sku" varchar,
  "description" varchar,
  "price" numeric,
  "category" varchar
);

CREATE TABLE material (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" varchar not null,
  "type" varchar,
  "price" numeric,
  "category" varchar
);