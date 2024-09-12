(ns bg.core
  (:require
   app.bg
   [lambdaisland.glogi :as log]
   setup-log))

(defn init! []
  (log/info :info "background")
  (app.bg/init!))
