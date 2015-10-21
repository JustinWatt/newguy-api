-- name: create-animals-table-if-not-exists!
-- create the animals table if it does not exist

CREATE TABLE IF NOT EXISTS animals (
      id              serial PRIMARY KEY
    , name            text NOT NULL
    , breed           text NOT NULL
    , created_at      timestamptz NOT NULL DEFAULT now()
    , updated_at      timestamptz NOT NULL DEFAULT now()
    , yard_id         integer REFERENCES yards (id) ON DELETE SET NULL
    , organization_id integer REFERENCES organizations (id) ON DELETE CASCADE);

-- name: drop-animals-table!
-- drop the animals table
DROP TABLE IF EXISTS animals;
