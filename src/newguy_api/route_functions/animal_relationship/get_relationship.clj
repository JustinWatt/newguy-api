(ns newguy-api.route-functions.animal-relationship.get-relationship
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn relationship-serializer [relationship]
  (select-keys relationship [:id :animal_a :animal_b :compatibility]))

(defn get-relationships [params]
  (let [{:keys [id-a id-b as-list?]} params
        relationships (query/get-animal-relationship-by-id {:animal_a id-a
                                                            :animal_b id-b})]
    (cond
      (empty? relationships) (respond/not-found "Relationship Not Found")
      as-list? (respond/ok (map relationship-serializer relationships))
      :else    (respond/ok (relationship-serializer (first relationships))))))
