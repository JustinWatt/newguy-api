(ns newguy-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [newguy-api.routes.animal :refer :all]
            [schema.core :as s]
            [newguy-api.queries.query-defs :as query]))

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Newguy-API"
            :description "API for managing interactions among animals in a shelter environment"
            :version "0.0.1"}
     :tags [{:name "Animals", :description "Create, Update, Delete Animal Details"}]})
  animal-routes)
