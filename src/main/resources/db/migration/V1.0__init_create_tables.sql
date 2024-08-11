CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" varchar not null,
  "service_rendered" varchar,
  "address" varchar
);
