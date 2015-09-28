(ns newguy-api.route-functions.animal.delete-animal
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn delete-animal [id]
  (let [deleted-animal (query/delete-animal! {:id id})]
    (if (not= 0 deleted-animal)
      (respond/ok        {:message (format "Animal id %d successfully removed" id)})
      (respond/not-found {:error "Animal ID does not exist"}))))

;; To be added - authorization
(defn delete-animal-response [id]
  (delete-animal id))
