(ns newguy-api.routes.animal
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :as respond]
            [cheshire.generate :as json]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.route-functions.animal.get-animals   :refer [get-animals]]
            [newguy-api.route-functions.animal.create-animal :refer [create-animal-response]]
            [newguy-api.route-functions.animal.delete-animal :refer [delete-animal-response]]
            [newguy-api.route-functions.animal.modify-animal :refer [modify-animal-response]]))

(defroutes* animal-routes
  (context* "/api" []
    (POST* "/animal"     {:as request}
            :tags        ["Animal"]
            :return      {:animal_name String}
            :body-params [animal_name :- String breed :- String organization_id :- Long]
            :summary     "Create a new animal with provided name, breed and organization"
            (create-animal-response animal_name breed organization_id))

    (GET* "/animals" {:as request}
          :tags ["Animal"]
          :return [{:id Long
                   :name String
                   :breed String
                   :yard_id (s/maybe Long)
                   :organization_id Long}]
          :summary "Returns all animals in the system"
          (get-animals nil nil nil))

    (GET* "/animal/:id" {:as request}
          :tags ["Animal"]
          :path-params [id :- Long]
          :return [{:id Long
                    :name String
                    :breed String
                    :yard_id (s/maybe Long)
                    :organization_id Long}]
          :summary "Returns all animals in the system"
          (get-animals id nil nil))

    (GET* "/organization/:organization_id/animals" {:as request}
          :tags ["Animal"]
          :path-params [organization_id :- Long]
          :return [{:id Long
                    :name String
                    :breed String
                    :yard_id (s/maybe Long)
                    :organization_id Long}]
          :summary "Returns all animals for the given organization id"
          (get-animals nil organization_id nil))

    ))



