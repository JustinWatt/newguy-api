(ns newguy-api.route-functions.yard.create-yard
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))


(defn create-new-yard [yard-name organization-id]
  (let [new-yard (query/insert-yard<! {:name yard-name
                                       :organization_id organization-id})]
    (respond/created {:name (str (:name new-yard))})))

(defn create-yard-response [yard-name organization-id]
  (let [name-query (query/get-yard-by-name {:name yard-name})
        name-exists? (not-empty name-query)]
    (cond
      name-exists? (respond/conflict {:error "Name already exists"})
      :else        (create-new-yard yard-name organization-id))))

