(ns newguy-api.route-functions.animal-relationship.create-relationship
  (:require [newguy-api.queries.query-defs :as query]
            [ring.util.http-response :as respond]))

(defn- serialize-relationship
  [relationship]
  (select-keys relationship [:id :animal_a :animal_b :compatibility]))

(defn create-new-relationship [id-a id-b compatibility]
  (let [relationship-a (query/insert-animal-relationship<! {:animal_a id-a :animal_b id-b :compatibility compatibility})
        relationship-b (query/insert-animal-relationship<! {:animal_a id-b :animal_b id-a :compatibility compatibility})]
    (respond/created (serialize-relationship relationship-a))))

(defn create-relationship-response [params]
  (let [{:keys [id-a id-b compatibility]} params
        compatibility' (if (nil? compatibility) 1 compatibility)
        animal-a-exists? (query/get-animal-by-id {:id id-a})
        animal-b-exists? (query/get-animal-by-id {:id id-b})]
    (cond
      (or (empty? animal-a-exists?)
          (empty? animal-b-exists?)) (respond/not-found {:error "Animal ID not Found"})
      :else (create-new-relationship id-a id-b compatibility'))))
