(ns newguy-api.route-functions.yard.delete-yard
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn delete-yard [id]
  (let [deleted-yard (query/delete-yard! {:id id})]
    (if (not= 0 deleted-yard)
      (respond/ok {:message (format "Yard ID %d successfully removed" id)})
      (respond/not-found {:error "Yard ID does not exist"}))))

(defn delete-yard-response [id]
  (delete-yard id))
