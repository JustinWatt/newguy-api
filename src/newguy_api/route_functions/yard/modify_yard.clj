(ns newguy-api.route-functions.yard.modify-yard
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn modify-yard [current-yard-info name]
  (let [new-name  (if (empty? name)  (str (:name current-yard-info)) name)
        new-yard-info (query/update-yard<! {:id (:id current-yard-info)
                                            :name new-name})]
    (respond/ok {:id (:id current-yard-info) :name new-name})))

(defn modify-yard-response [params]
  (let [{:keys [id name]} params
        current-yard-info (first (query/get-yard-by-id {:id id}))]
    (if (empty? current-yard-info)
      (respond/not-found {:error "Yard ID does not exist"})
      (modify-yard current-yard-info name))))
