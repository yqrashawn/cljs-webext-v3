(ns dt.core
  (:require
   app.dt
   ext.devtools
   [lambdaisland.glogi :as log]
   setup-log))

(defn init! []
  (log/info :info "devtool")
  (app.dt/init!))
