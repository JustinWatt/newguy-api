(ns newguy-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Message {:message String})

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Newguy-api"
            :description "Compojure Api example"
            :version "0.0.1"}
     :tags [{:name "Animals", :description "Create, Update, Delete User Details"}]})

  (context* "/hello" []
    :tags ["hello"]
    (GET* "/" []
      :return Message
      :query-params [name :- String]
      :summary "say hello"
      (ok {:message (str "Terve, " name)}))))
