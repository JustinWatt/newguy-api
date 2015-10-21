-- name: create-yards-table-if-not-exists!
-- create the yards table if it does not exist

CREATE TABLE IF NOT EXISTS yards (
      id              serial PRIMARY KEY
    , name            text   NOT NULL UNIQUE
    , created_at      timestamptz NOT NULL DEFAULT now()
    , updated_at      timestamptz NOT NULL DEFAULT now()
    , organization_id integer REFERENCES organizations (id) ON DELETE CASCADE);

-- name: drop-yards-table!
-- drop the yards table
DROP TABLE IF EXISTS yards;
