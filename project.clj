(defproject newguy-api "0.1.0-SNAPSHOT"
  :description "API to solve your dog play group needs"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [metosin/compojure-api "0.23.1"]
                 [metosin/ring-http-response "0.6.5"]
                 [metosin/ring-swagger-ui "2.1.2"]
                 [buddy "0.7.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [postgresql "9.3-1102.jdbc41"]
                 [yesql "0.5.1"]
                 [environ "1.0.1"]
                 [clj-time "0.11.0"]
                 [com.draines/postal "1.11.4"]
                 [cheshire "5.5.0"]
                 [http-kit "2.1.19"]
                 [com.stuartsierra/component "0.3.0"]]

  :plugins [[lein-environ "1.0.1"]]

  :min-lein-version "2.5.0"

  :ring {:handler newguy-api.handler/app
         :auto-refresh? true
         :auto-reload? true}

  :uberjar-name "server.jar"

  :profiles {:dev [:dev-env-vars
                   {:source-paths ["dev" "src"]
                   :dependencies [[javax.servlet/servlet-api "2.5"]
                                  [cheshire "5.5.0"]
                                  [ring-mock "0.1.5"]
                                  [reloaded.repl "0.2.1"]]}]

             :uberjar {:resource-paths ["swagger-ui"]
                        :aot :all}

             :test-local {:plugins [[lein-ring "0.9.6"]]}

             :test-env-vars {}

             :test [:test-env-vars :test-local]}

  :main newguy-api.server)
