(ns newguy-api.seed-data
  (:require [newguy-api.queries.query-defs :as query]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.queries.query-defs :as query]))

(defn drop-db! []
  (query/drop-animal-yard-table!)
  (query/drop-animals-table!)
  (query/drop-yards-table!)
  (query/drop-organizations-table!))

(defn create-db! []
  (query/create-organizations-table-if-not-exists!)
  (query/create-yards-table-if-not-exists!)
  (query/create-animals-table-if-not-exists!)
  (query/create-animal-yard-table-if-not-exists!))

(defn seed-organization!
  [organization-name]
  (query/insert-organization<! {:name organization-name}))

(defn seed-yard!
  [yard-name organization-id]
  (query/insert-yard<! {:name yard-name
                        :organization_id organization-id}))

(defn seed-animal!
  [animal-name breed yard-id organization-id]
  (query/insert-animal<! {:name animal-name
                          :breed breed
                          :yard_id yard-id
                          :organization_id organization-id}))

(defn seed-data []
  (seed-organization! "JCT")
  (seed-yard! "In Kennel" 0)
  (seed-yard! "Yard 1" 1)
  (seed-yard! "Yard 2" 1)
  (seed-animal! "Fran" "Corgi" 1 1)
  (seed-animal! "New Guy" "Chihuahua" 2 1)
  (seed-animal! "BrawDog" "Wishing Boot" 2 1))

(defn seed-data! []
  (seed-organization! "JCT")
  (seed-yard! "In Kennel" 0)
  (seed-yard! "Yard 1" 1)
  (seed-yard! "Yard 2" 1)
  (seed-animal! "Fran" "Corgi" 1 1)
  (seed-animal! "New Guy" "Chihuahua" 2 1)
  (seed-animal! "BrawDog" "Wishing Boot" 2 1))
