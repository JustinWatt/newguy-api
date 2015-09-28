-- name: all-animals
-- Selects all animals
SELECT  animals.id
      , animals.name
      , animals.breed
      , animals.created_at
      , animals.updated_at
      , animals.yard_id
      , animals.organization_id
FROM animals

-- name: get-animal-by-id
-- Selects the (id, name, breed, created_at, updated_at, yard_id, organization_id) for the animal matching the id
SELECT
      animals.id
    , animals.name
    , animals.breed
    , animals.created_at
    , animals.updated_at
    , animals.yard_id
    , animals.organization_id
FROM animals
WHERE animals.id = :id;

-- name: get-animal-by-name
-- Selects the (id, name, breed, created_at, updated_at, yard_id, organization_id) for the animal matching the name
SELECT
    , animals.id
    , animals.name
    , animals.breed
    , animals.created_at
    , animals.updated_at
    , animals.yard_id
    , animals.organization_id
FROM animals
WHERE animals.name = :name;

-- name: insert-animal<!
-- inserts a single animal
INSERT INTO animals (
     animals.name
   , animals.breed
   , animals.organization_id)
VALUES (
      :name
    , :breed
    , :organization_id);

-- name: update-animal<!
-- update a single animal matching id
UPDATE animals
SET , animals.name           = :name
    , animals.breed          = :breed
    , animals.yard           = :yard
    , animals.updated_at     = now()
WHERE animals.id = :id;

-- name: delete-animal!
-- delete a single animal matching id
DELETE FROM animals
WHERE       animals.id = :id;
