-- name: all-animals
-- Selects all animals with optional filters for organization_id and yard_id
SELECT  animals.id
      , animals.name
      , animals.breed
      , animals.created_at
      , animals.updated_at
      , animals.yard_id
      , animals.organization_id
      , (SELECT MAX(animal_yard.entry_dts)
                FROM animal_yard
                WHERE animal_yard.animal_id = animals.id) AS last_playtime

FROM animals
WHERE (:has_organization_id IS FALSE OR :organization_id = animals.organization_id)
      AND (:has_yard_id IS FALSE OR :yard_id = animals.yard_id)
      AND (:has_animal_id IS FALSE OR :animal_id = animals.id);

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
      animals.id
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
SET , animals.name           = :name
    , animals.breed          = :breed
    , animals.yard           = :yard
    , animals.updated_at     = now()
WHERE animals.id = :id;

-- name: delete-animal!
-- delete a single animal matching id
DELETE FROM animals
WHERE       animals.id = :id;
