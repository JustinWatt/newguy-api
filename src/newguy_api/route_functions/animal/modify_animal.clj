(ns newguy-api.route-functions.animal.modify-animal
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn modify-animal [current-animal-info name breed yard_id]
  (let [new-name  (if (empty? name)  (:name current-animal-info) name)
        new-breed (if (empty? breed) (:breed current-animal-info) breed)
        new-animal-info (query/update-animal<! {:id (:id current-animal-info)
                                                :name new-name
                                                :breed new-breed
                                                :yard_id yard_id})]
    (respond/ok {:id (:id current-animal-info)
                 :name new-name
                 :breed new-breed
                 :yard_id yard_id})))



(defn- yard-nil-or-missing?
  [yard_id]
  (if (nil? yard_id)
    true
    (first (query/get-yard-by-id {:id yard_id}))))

(defn modify-animal-response [params]
  (let [{:keys [animal_id name breed yard_id]} params
        current-animal-info (first (query/get-animal-by-id {:id animal_id}))
        yard_exists?        (yard-nil-or-missing? yard_id)]
    (cond
      (empty? current-animal-info) (respond/not-found {:error "Animal ID does not exist"})
      (nil? yard_exists?)          (respond/not-found {:error "Yard ID does not exist"})
      :else                        (modify-animal current-animal-info name breed yard_id))))
