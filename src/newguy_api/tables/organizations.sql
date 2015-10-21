-- name: create-organizations-table-if-not-exists!
-- create the organizations table if it does not exist

CREATE TABLE IF NOT EXISTS organizations (
      id         serial PRIMARY KEY
    , name       text UNIQUE NOT NULL
    , created_at timestamptz NOT NULL DEFAULT now()
    , updated_at timestamptz NOT NULL DEFAULT now());

-- name: drop-organizations-table!
-- drop the organizations table
DROP TABLE IF EXISTS organizations;
