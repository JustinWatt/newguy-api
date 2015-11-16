(ns newguy-api.server
  (:require [newguy-api.handler :refer [app]]
            [newguy-api.seed-data :refer [drop-db! create-db! seed-data!]]
            [org.httpkit.server :refer [run-server]]
            [com.stuartsierra.component :as component]))

(defn- start-server [handler port]
  (let [server (run-server handler {:port  port})]
    (println (str "Started server on localhost: " port))
    server))

(defn- stop-server [server]
  (when server
    (server))) ;; run-server returns a fn that stops itself

(defrecord NewGuyAPI []
  component/Lifecycle
  (start [this]
    (assoc this :server (start-server #'app 7000)))
  (stop [this]
    (stop-server (:server this))
    (dissoc this :server)))

(defn create-system []
  (NewGuyAPI.))

(defn -main [& args]
  (.start (create-system)))
