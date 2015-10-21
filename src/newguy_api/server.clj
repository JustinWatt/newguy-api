(ns newguy-api.server
  (:require [org.httpkit.server :as httpkit]
            [newguy-api.handler :refer [app]]
            [newguy-api.seed-data :refer [drop-db! create-db! seed-data!]]))

(defn -main [port]
  (httpkit/run-server app {:port (Integer/parseInt port) :join false})
  (println "server started on port:" port))
