-- name: create-animal-relationship-table-if-not-exists!
CREATE TABLE IF NOT EXISTS animal_relationships (
  id              serial PRIMARY KEY
, animal_a        integer REFERENCES animals (id) ON DELETE CASCADE
, animal_b        integer REFERENCES animals (id) ON DELETE CASCADE
, compatibility   integer DEFAULT NOT NULL 0
, organization_id integer REFERENCES organizations (id) ON DELETE CASCADE
, created_at      timestamptz NOT NULL DEFAULT now()
, updated_at      timestamptz NOT NULL DEFAULT now();

-- name: drop-animal-relationships-table!
DROP TABLE IF EXISTS animal_relationships;
