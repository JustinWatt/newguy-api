-- name: all-animals
-- Selects all animals

SELECT id
      , name
      , breed
      , created_at
      , updated_at
      , last_play_time
      , yard_id
      , organization_id
FROM animals;

-- name: get-animal-by-id
-- Selects the (id, name, breed, created_at, updated_at, last_play_time, yard_id, organization_id) for the animal matching the id
SELECT id
    , name
    , breed
    , created_at
    , updated_at
    , last_play_time
    , yard_id
    , organization_id
FROM animals
WHERE id = :id;

-- name: get-animal-by-name
-- Selects the (id, name, breed, created_at, updated_at, last_play_time, yard_id, organization_id) for the animal matching the name

SELECT id
    , name
    , breed
    , created_at
    , updated_at
    , last_play_time
    , yard_id
    , organization_id
FROM animals
WHERE name = :name;

-- name: insert-animal<!
-- inserts a single animal

INSERT INTO animals (
     name
   , breed
   , organization_id)
VALUES (
      :name
    , :breed
    , :organization_id);

-- name: update-animal<!
-- update a single animal matching id
UPDATE animals
SET   id = :id
    , name = :name
    , breed = :breed
    , updated_at = now()

WHERE id = :id;

-- name: delete-animal!
-- delete a single animal matching id
DELETE FROM animals
WHERE       id = :id;
