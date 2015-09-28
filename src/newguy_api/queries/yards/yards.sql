-- name: all-yards
-- Selects all yards
SELECT  yards.id
      , yards.name
      , yards.created_at
      , yards.updated_at
      , yards.organization_id
FROM yards;

-- name: get-yard-by-id
-- Selects the (id, name, created_at, updated_at, organization_id) for the yard matching the id
SELECT
      yards.id
    , yards.name
    , yards.created_at
    , yards.updated_at
    , yards.organization_id
FROM yards
WHERE yards.id = :id;

-- name: get-yard-by-name
-- Selects the (id, name, created_at, updated_at, yard_id, organization_id) for the yard matching the name
SELECT
    , yards.id
    , yards.name
    , yards.created_at
    , yards.updated_at
    , yards.organization_id
FROM yards
WHERE yards.name = :name;

-- name: insert-yard<!
-- inserts a single yard
INSERT INTO yards (
     yards.name
   , yards.organization_id)
VALUES (
     :name
   , :organization_id);

-- name: update-yard<!
-- update a single yard matching id
UPDATE yards
SET , yards.name           = :name
    , yards.yard           = :yard
    , yards.updated_at     = now()
WHERE yards.id = :id;

-- name: delete-yard!
-- delete a single yard matching id
DELETE FROM yards
WHERE       yards.id = :id;
