(ns newguy-api.route-functions.organization.delete-organizations
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn delete-organization [id]
  (let [deleted-organization (query/delete-organization! {:id id})]
    (if (not= 0 deleted-organization)
      (respond/ok        {:message (format "Organization ID %d successfully removed" id)})
      (respond/not-found {:error "Organization ID does not exist"}))))

;; To be added - authorization
(defn delete-organization-response [id]
  (delete-organization id))
