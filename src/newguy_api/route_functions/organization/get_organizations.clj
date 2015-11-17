(ns newguy-api.route-functions.organization.get-organizations
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn organization-serializer [organization]
  (select-keys organization [:id :name]))

(defn get-organizations [params]
  (let [{:keys [organization_id]} params
        has_organization_id (not (nil? organization_id))
        organizations (query/all-organizations {:has_organization_id has_organization_id
                                                :organization_id organization_id})]
    (if (empty? organizations) (respond/not-found "Organization not found")
        (respond/ok (map organization-serializer organizations)))))
