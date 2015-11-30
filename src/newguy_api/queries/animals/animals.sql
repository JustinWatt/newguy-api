-- name: insert-animal-yard<!
-- inserts a single animal
INSERT INTO animal_yard (  animal_id
                         , yard_id)
VALUES (  :animal_id
        , :yard_id);

-- name: update-exit-time!
-- update the play time
UPDATE animal_yard
SET
    "exit_dts" = now()
WHERE id = :id;

-- name: animals-last-play-time
SELECT animal_yard.id
      ,animal_yard.animal_id
      ,animal_yard.yard_id
      ,animal_yard.entry_dts
      ,animal_yard.exit_dts

FROM animal_yard
WHERE :animal_id = animal_yard.animal_id
ORDER BY entry_dts DESC;

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
      AND (:has_yard_id   IS FALSE OR :yard_id = animals.yard_id)
      AND (:has_animal_id IS FALSE OR :animal_id = animals.id);

-- name: get-animal-by-id
-- Selects the (id, name, breed, created_at, updated_at, yard_id, organization_id) for the animal matching the id
SELECT animals.id
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
SET   "name"       = :name
    , "breed"      = :breed
    , "yard_id"    = :yard_id
    , "updated_at" = now()
WHERE id = :id;

-- name: delete-animal!
-- delete a single animal matching id
DELETE FROM animals
WHERE       animals.id = :id;
