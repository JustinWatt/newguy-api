(ns newguy-api.routes.organization
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :as respond]
            [cheshire.generate :as json]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]
            [newguy-api.route-functions.organization.get-organization   :refer [get-organizations]]
            [newguy-api.route-functions.organization.create-organization :refer [create-organization-response]]
            [newguy-api.route-functions.organization.delete-organization :refer [delete-organization-response]]))


(defroutes* organization-routes
  (context* "/api" []
            (POST* "/organization" {:as request}
                   :tags        ["Organization"]
                   :return      {:organization_name String
                                 :organization_id Long}
                   :body-params [organization_name :- String]
                   :summary     "Create a new organization with provided name"
                   (create-organization-response {:organization-name organization_name}))))
