(ns bg.core
  (:require
   [lambdaisland.glogi :as log]
   setup-log))

(defn init! []
  (log/info :info "background"))
