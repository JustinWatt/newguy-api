(ns newguy-api.route-functions.yard.get-yards
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn yard-serializer [yard]
  (select-keys yard [:id :name :organization_id]))

(defn get-yards [params]
  (let [{:keys [yard_id organization_id]} params
        has_organization_id (not (nil? organization_id))
        has_yard_id         (not (nil? yard_id))
        yards (query/all-yards {:organization_id organization_id
                                :yard_id         yard_id
                                :has_organization_id has_organization_id
                                :has_yard_id     has_yard_id})]
    (if (empty? yards) (respond/not-found "Yard not found")
        (respond/ok (map yard-serializer yards)))))
