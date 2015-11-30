-- name: create-registered-users-table-if-not-exists!
-- create the registered users table if it does not exist

CREATE TABLE IF NOT EXISTS registered_users (
      id         serial PRIMARY KEY
    , username   text NOT NULL UNIQUE
    , email      text NOT NULL UNIQUE
    , password   text NOT NULL
    , first_name text
    , last_name  text
    , location   text
    , created_at timestamptz NOT NULL DEFAULT now()
    , updated_at timestamptz NOT NULL DEFAULT now());

-- name: drop-registered-users-table!
-- drop the registered-users table
DROP TABLE IF EXISTS registered_users;
