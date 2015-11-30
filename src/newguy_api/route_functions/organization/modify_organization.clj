(ns newguy-api.route-functions.organization.modify-organization
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))



(defn modify-organization [current-organization-info name]
  (let [current-id (:id current-organization-info)
        new-name  (if (empty? name) (:name current-organization-info) name)
        new-organization-info (query/update-organization<! {:id current-id
                                                            :name new-name})]
    (respond/ok {:id current-id
                 :name new-name})))

(defn modify-organization-response [params]
  (let [{:keys [organization-id name]} params
        current-organization-info (first (query/get-organization-by-id {:id organization-id}))]
    (cond
      (empty? current-organization-info)
       (respond/not-found {:error "Organization ID does not exist"})
      :else
       (modify-organization current-organization-info name))))


