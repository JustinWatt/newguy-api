(ns newguy-api.routes.yard
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :as respond]
            [cheshire.generate :as json]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.route-functions.yard.get-yards :refer [get-yards]]
            [newguy-api.route-functions.yard.create-yard :refer [create-yard-response]]
            [newguy-api.route-functions.yard.delete-yard :refer [delete-yard-response]]
            [newguy-api.route-functions.yard.modify-yard :refer [modify-yard-response]]))

(defroutes* yard-routes
  (context* "/api" []
    (POST* "/yard" {:as request}
           :tags ["Yard"]
           :return      {:yard_name String}
           :body-params [yard_name :- String organization_id :- Long]
           :summary     "Create a yard with given name for specified organization"
           (create-yard-response yard_name organization_id))

    (GET* "/yards" {:as request}
          :tags ["Yard"]
          :return [{:id Long
                    :name String
                    :organization_id Long}]
          :summary "Returns all yards in the system"
          (get-yards {}))

    (GET* "/yard/:id" {:as request}
          :tags ["Yard"]
          :path-params [id :- Long]
          :return [{:id Long
                    :name String
                    :organization_id Long}]
          :summary "Returns yard matching given id"
          (get-yards {:yard_id id}))

    (GET* "/organization/:organization_id/yards" {:as request}
          :tags ["Yard"]
          :path-params [organization_id :- Long]
          :return [{:id Long
                    :name String
                    :organization_id Long}]
          :summary "Returns all yards for the given organization id"
          (get-yards {:organization_id organization_id}))))


