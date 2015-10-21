(ns newguy-api.route-functions.animal.get-animals
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))


(defn animal-serializer [animal]
  (select-keys animal [:id :name :breed :yard_id :organization_id]))

(defn get-animals [animal_id organization_id yard_id]
  (let [has_organization_id (not (nil? organization_id))
        has_yard_id         (not (nil? yard_id))
        has_animal_id       (not (nil? animal_id))
        animals (query/all-animals {:organization_id organization_id
                                    :yard_id         yard_id
                                    :animal_id       animal_id
                                    :has_organization_id has_organization_id
                                    :has_yard_id     has_yard_id
                                    :has_animal_id   has_animal_id})]
    (respond/ok (map animal-serializer animals))))
