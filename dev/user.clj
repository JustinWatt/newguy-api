(ns user
  (:require [reloaded.repl :refer [system init start go reset stop]]
            [newguy-api.server]))

(reloaded.repl/set-init! #(newguy-api.server/create-system))
