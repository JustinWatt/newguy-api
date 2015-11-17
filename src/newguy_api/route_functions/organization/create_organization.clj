(ns newguy-api.route-functions.organization.create-organization
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn create-new-organization [organization-name]
  (let [new-organization (query/insert-organization<! {:name organization-name})]
    (respond/created {:organization_name (str (:name new-organization))})))

(defn create-organization-response [params]
  (let [{:keys [organization-name]} params
        name-query (query/get-organization-by-name {:name organization-name})
        name-exists? (not-empty name-query)]
    (cond
      name-exists? (respond/conflict {:error "Name already exists"})
      :else        (create-new-organization organization-name))))
