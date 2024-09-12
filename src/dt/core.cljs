(ns dt.core
  (:require
   ext.devtools
   [lambdaisland.glogi :as log]
   setup-log))

(defn init! []
  (log/info :info "devtool")
  (ext.devtools/create-panel))
