(ns newguy-api.routes.organization
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :as respond]
            [cheshire.generate :as json]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.route-functions.organization.modify-organization :refer [modify-organization-response]]
            [newguy-api.route-functions.organization.get-organization    :refer [get-organizations]]
            [newguy-api.route-functions.organization.create-organization :refer [create-organization-response]]
            [newguy-api.route-functions.organization.delete-organization :refer [delete-organization-response]]))


(defroutes* organization-routes
  (context* "/api" []
            (GET* "/organizations" {:as request}
                  :tags ["Organization"]
                  :return [{:id Long
                            :name String}]
                  :summary "Returns all Organizations in the system"
                  (get-organizations {}))
            (GET* "/organization/:id" {:as request}
                  :tags ["Organization"]
                  :path-params [id :- Long]
                  :return [{:id Long
                            :name String}]
                  :summary "Returns all Organization matching given id"
                  (get-organizations {:organization_id id}))
            (PATCH*  "/organization/:id"  {:as request}
                     :tags          ["Organization"]
                     :path-params   [id :- Long]
                     :body-params   [{name :- String ""}]
                     :return        {:id Long :name String}
                     :summary       "Update some or all fields of a specified organization."
                     (modify-organization-response {:organization-id id :name name}))
            (POST* "/organization" {:as request}
                   :tags        ["Organization"]
                   :return      {:organization_name String
                                 :organization_id Long}
                   :body-params [organization_name :- String]
                   :summary     "Create a new organization with provided name"
                   (create-organization-response {:organization-name organization_name}))))
