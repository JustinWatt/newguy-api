(ns newguy-api.queries.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

(def db-connection {:connection (env :database-url)})

(defqueries  "newguy_api/tables/animals.sql"                      db-connection)
(defqueries  "newguy_api/tables/yards.sql"                        db-connection)
(defqueries  "newguy_api/tables/organizations.sql"                db-connection)
(defqueries  "newguy_api/tables/animal_yard.sql"                  db-connection)
(defqueries  "newguy_api/tables/users/registered_users.sql"       db-connection)
(defqueries  "newguy_api/queries/animals/animals.sql"             db-connection)
(defqueries  "newguy_api/queries/yards/yards.sql"                 db-connection)
(defqueries  "newguy_api/queries/organizations/organizations.sql" db-connection)
