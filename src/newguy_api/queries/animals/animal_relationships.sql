-- name: get-animal-relationship-by-id
SELECT
   animal_relationships.id
  ,animal_relationships.animal_a
  ,animal_relationships.animal_b
  ,animal_relationships.compatibility
FROM animal_relationships
  WHERE :animal_a = animal_relationships.animal_a
  AND   :animal_b = animal_relationships.animal_b
  ORDER BY animal_relationships.id DESC;

-- name: insert-animal-relationship<!
INSERT INTO animal_relationships (
  animal_a
, animal_b
, compatibility)
VALUES (
  :animal_a
, :animal_b
, :compatibility)

-- name: update-animal-relationship-compatibility!
UPDATE animal_relationships
SET
"compatibility" = :compatibility
WHERE animal_a = :animal_a AND animal_b = :animal_b;
