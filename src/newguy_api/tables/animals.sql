-- name: create-animals-table-if-not-exists!
-- create the animals table if it does not exist

CREATE TABLE IF NOT EXISTS animals (
      id              serial PRIMARY KEY
    , name            citext NOT NULL
    , breed           text NOT NULL
    , created_at      timestamp with time zone NOT NULL DEFAULT now()
    , updated_at      timestamp with time zone NOT NULL DEFAULT now()
    , yard_id         integer REFERENCES yards (id) ON DELETE SET NULL
    , organization_id integer REFERENCES organizations (id) ON DELETE CASCADE);

-- name: drop-animals-table!
-- drop the animals table
DROP TABLE animals;
