(ns newguy-api.server
  (:require [org.httpkit.server :as httpkit]
            [newguy-api.handler :refer [app]]))



(defn -main [port]
  (httpkit/run-server app {:port (Integer/parseInt port) :join false})
  (println "server started on port:" port))
