(ns newguy-api.route-functions.animal.modify-animal
  (:require [clj-time.core :as time]
            [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))


(defn modify-animal [current-animal-info name breed yard last-play-time]
  (let [new-name  (if (empty? name)  (str (:name current-animal-info)) name)
        new-breed (if (empty? breed) (str (:breed current-animal-info)) breed)
        new-yard  (if (empty? yard)  (str (:yard current-animal-info)) yard)
        new-last-play-time (if (empty? last-play-time) (str (:breed current-animal-info)) breed)
        new-animal-info (query/update-animal<! {:id (:id current-animal-info)
                                                :name new-name
                                                :breed })]))
