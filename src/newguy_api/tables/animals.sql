-- name: create-animals-table-if-not-exists!
-- create the animals table if it does not exist

CREATE TABLE IF NOT EXISTS animals (
      id              serial PRIMARY KEY
    , name            citext NOT NULL
    , breed           text NOT NULL
    , created_at      timestamp with time zone NOT NULL DEFAULT now()
    , updated_at      timestamp with time zone NOT NULL DEFAULT now()
    , last_play_time  timestamp with time zone NOT NULL DEFAULT now()
    , yard_id         integer REFERENCES yards (id) ON DELETE SET NULL
    , organization_id integer REFERENCES organizations (id) ON DELETE CASCADE);


-- name: create-last-play-update-function!
-- creates a trigger to update last play time when the yard_id is changed
CREATE OR REPLACE FUNCTION update_last_play_time()
RETURNS TRIGGER AS $$
       BEGIN
           NEW.last_play_time = now();
           RETURN NEW;
       END;
$$ language 'plpgsql'

-- name: create-last-play-trigger!
-- creates a trigger to update last play time when the yard_id is changed
CREATE TRIGGER update_last_play_time_trigger BEFORE UPDATE ON animals
    FOR EACH ROW
    WHEN (NEW.yard_id IS NOT NULL)
    EXECUTE PROCEDURE update_last_play_time();


-- name: drop-animals-table!
-- drop the animals table
DROP TABLE animals;
