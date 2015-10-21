(ns newguy-api.route-functions.animal.modify-animal
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))


(defn modify-animal [current-animal-info name breed yard]
  (let [new-name  (if (empty? name)  (str (:name current-animal-info)) name)
        new-breed (if (empty? breed) (str (:breed current-animal-info)) breed)
        new-yard  (if (empty? yard)  (str (:yard current-animal-info)) yard)
        new-animal-info (query/update-animal<! {:id (:id current-animal-info)
                                                :name new-name
                                                :breed new-breed})]
    (respond/ok {:id (:id current-animal-info) :name new-name :breed new-breed})))

(defn modify-animal-response [id name breed yard]
  (let [current-animal-info (first (query/get-animal-by-id {:id id}))]
    (cond
      (empty? current-animal-info) (respond/not-found {:error "Animal ID does not exist"}))))
