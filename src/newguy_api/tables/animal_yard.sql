-- name: create-animal-yard-table-if-not-exists!
-- create the animal_yard junction table if it does not exist

CREATE TABLE IF NOT EXISTS animal_yard (
      id        serial PRIMARY KEY
    , animal_id integer NOT NULL REFERENCES animals (id) ON DELETE CASCADE
    , yard_id   integer NOT NULL REFERENCES yards   (id) ON DELETE CASCADE
    , entry_dts timestamptz DEFAULT now()
    , exit_dts  timestamptz);

-- name: drop-animal-yard-table!
-- drop the animal_yard table
DROP TABLE IF EXISTS animal_yard;


