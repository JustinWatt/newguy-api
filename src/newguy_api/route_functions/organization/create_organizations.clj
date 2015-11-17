(ns newguy-api.route-functions.organization.create-organizations
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn create-new-organization [organization-name]
  (let [new-organization (query/insert-organization<! {:name organization-name})]
    (respond/created {:name (str (:name new-organization))})))

(defn create-organization-response [organization-name]
  (let [name-query (query/get-organization-by-name {:name organization-name})
        name-exists? (not-empty name-query)]
    (cond
      name-exists? (respond/conflict {:error "Name already exists"})
      :else        (create-new-organization organization-name))))
