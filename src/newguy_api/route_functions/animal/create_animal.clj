(ns newguy-api.route-functions.animal.create-animal
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))


(defn create-new-animal [name breed organization]
  (let [new-animal (query/insert-animal<! {:name name
                                         :breed breed
                                         :organization organization})]
    (respond/created {:username (str (:name new-animal))})))

(defn create-animal-respoonse [name breed organization]
  (let [name-query (query/get-animal-name {:name name})
        name-exists? (not-empty name-query)]
    (cond
      name-exists? (respond/conflict {:error "Name already exists"})
      :else        (create-new-user name breed organization))))

