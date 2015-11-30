(ns newguy-api.route-functions.animal.create-animal
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn create-new-animal [animal-name breed organization-id]
  (let [new-animal (query/insert-animal<! {:name animal-name
                                           :breed breed
                                           :organization_id organization-id})]
    (respond/created {:name (str (:name new-animal))})))

(defn create-animal-response [animal-name breed organization-id]
  (let [name-query (query/get-animal-by-name {:name animal-name})
        name-exists? (not-empty name-query)]
    (cond
      name-exists? (respond/conflict {:error "Name already exists"})
      :else        (create-new-animal animal-name breed organization-id))))
