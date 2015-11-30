(ns newguy-api.route-functions.animal.modify-animal
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn- handle-last-play-time
  [id yard-id]
  (let [last-play-time (first (query/animals-last-play-time {:animal_id id}))]
    (cond
      (nil? yard-id) (query/update-exit-time! {:id (:id last-play-time)})
      (nil? (:exit_dts last-play-time)) (do (query/update-exit-time! {:id (:id last-play-time)})
                                            (query/insert-animal-yard<! {:animal_id id :yard_id yard-id}))
      :else (query/insert-animal-yard<! {:animal_id id :yard_id yard-id}))))


(defn modify-animal [current-animal-info new-name new-breed new-yard-id]
  (let [{:keys [id name breed yard_id]} current-animal-info
        new-name  (if (empty? new-name)  name new-name)
        new-breed (if (empty? new-breed) breed new-breed)
        new-yard? (not= new-yard-id yard_id)]
    (query/update-animal<! {:id id
                            :name  new-name
                            :breed new-breed
                            :yard_id new-yard-id})
    (when new-yard? (handle-last-play-time id new-yard-id))
    (respond/ok {:id    id
                 :name  new-name
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

