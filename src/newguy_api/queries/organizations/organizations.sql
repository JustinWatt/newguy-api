
-- name: all-organizations
-- Selects all organizations
SELECT  organizations.id
      , organizations.name
      , organizations.created_at
      , organizations.updated_at
FROM organizations
WHERE (:has_organization_id IS FALSE OR :organization_id = organizations.id);

-- name: get-organization-by-id
-- Selects the (id, name, created_at, updated_at) for the organization matching the id
SELECT
      organizations.id
    , organizations.name
    , organizations.created_at
    , organizations.updated_at
FROM organizations
WHERE organizations.id = :id;

-- name: get-organization-by-name
-- Selects the (id, name, created_at, updated_at) for the yard matching the name
SELECT
      organizations.id
    , organizations.name
    , organizations.created_at
    , organizations.updated_at
FROM organizations
WHERE organizations.name = :name;

-- name: insert-organization<!
-- inserts a single organization
INSERT INTO organizations (
     name)
VALUES (
     :name);

-- name: update-organization<!
-- update a single organization matching id
UPDATE organizations
SET   "name"       = :name
    , "updated_at" = now()
WHERE organizations.id = :id;

-- name: delete-organization!
-- delete a single organization matching id
DELETE FROM organizations
WHERE       organizations.id = :id;
