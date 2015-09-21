-- name: create-organizations-table-if-not-exists!
-- create the organizations table if it does not exist

CREATE TABLE IF NOT EXISTS organizations (
      id         serial PRIMARY KEY
    , name       citext UNIQUE NOT NULL
    , created_at timestamp with time zone NOT NULL DEFAULT now()
    , updated_at timestamp with time zone NOT NULL DEFAULT now());

-- name: drop-organizations-table!
-- drop the organizations table
DROP TABLE organizations;
