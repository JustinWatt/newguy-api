(ns newguy-api.routes.animal
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :as respond]
            [cheshire.generate :as json]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.route-functions.animal-relationship.create-relationship :refer [create-relationship-response]]
            [newguy-api.route-functions.animal-relationship.get-relationship :refer [get-relationships]]
            [newguy-api.route-functions.animal.get-animals   :refer [get-animals]]
            [newguy-api.route-functions.animal.create-animal :refer [create-animal-response]]
            [newguy-api.route-functions.animal.delete-animal :refer [delete-animal-response]]
            [newguy-api.route-functions.animal.modify-animal :refer [modify-animal-response]]))

(defroutes* animal-routes
  (context* "/api" []
    (POST* "/relationship" {:as request}
            :tags        ["Relationship"]
            :return      {:animal_a Long :animal_b Long :id Long :compatibility Long}
            :body-params [id_a :- Long id_b :- Long compatibility :- Long]
            :summary     "Create a relationship (and it's inverse) for the given ids"
            (create-relationship-response {:id-a id_a :id-b id_b :compatibility compatibility}))

    (POST* "/animal"     {:as request}
            :tags        ["Animal"]
            :return      {:animal_name String}
            :body-params [animal_name :- String breed :- String organization_id :- Long]
            :summary     "Create a new animal with provided name, breed and organization"
            (create-animal-response animal_name breed organization_id))

    (PATCH*  "/animal/:id"  {:as request}
             :tags          ["Animal"]
             :path-params   [id :- Long]
             :body-params   [{name :- String ""} {breed :- String ""} {yard_id :- (s/maybe Long) nil}]
             :return        {:id Long :name String :breed String :yard_id (s/maybe Long)}
             :summary       "Update some or all fields of a specified animal."
             (modify-animal-response {:animal_id id :name name :breed breed :yard_id yard_id}))

    (GET* "/animals" {:as request}
          :tags ["Animal"]
          :return [{:id Long :name String :breed String :yard_id (s/maybe Long) :organization_id Long}]
          :summary "Returns all animals in the system"
          (get-animals {}))

    (GET* "/animal/:id" {:as request}
          :tags ["Animal"]
          :path-params [id :- Long]
          :return [{:id Long
                    :name String
                    :breed String
                    :yard_id (s/maybe Long)
                    :organization_id Long}]
          :summary "Returns animal matching given id"
          (get-animals {:animal_id id} ))

    (GET* "/relationships/:id-a/:id-b" {:as request}
          :tags        ["Relationship"]
          :path-params [id-a :- Long id-b :- Long]
          :return      [{:id Long
                         :animal_a Long
                         :animal_b Long
                         :compatibility Long}]
          :summary     "Get all relationships between two animals or the most recent"
          (get-relationships {:id-a id-a :id-b id-b :as-list? true}))

    (GET* "/organization/:organization_id/animals" {:as request}
          :tags ["Animal"]
          :path-params [organization_id :- Long]
          :return [{:id Long
                    :name String
                    :breed String
                    :yard_id (s/maybe Long)
                    :organization_id Long}]
          :summary "Returns all animals for the given organization id"
          (get-animals {:organization_id organization_id}))

    (GET* "/yard/:yard_id/animals" {:as request}
          :tags ["Animal"]
          :path-params [yard_id :- Long]
          :return [{:id Long
                    :name String
                    :breed String
                    :yard_id (s/maybe Long)
                    :organization_id Long}]
          :summary "Returns all animals for the given yard id"
          (get-animals {:yard_id yard_id}))))
