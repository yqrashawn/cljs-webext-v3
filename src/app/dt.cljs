(ns app.dt
  (:require
   ext.devtools
   ext.scripting
   [oops.core :as oops]
   ;; portal.runtime
   ;; portal.ui.core
   setup-log
   util))

(defn render-portal []
  ;; (util/wait-till
  ;;  #(js/document.getElementById "root")
  ;;  portal.ui.core/main!
  ;;  {:interval-ms 100
  ;;   :timeout-ms 10000})
  )

(defn init! []
  (if (= js/document.title "DevtoolsPanel")
    (render-portal)
    (ext.devtools/create-panel)))

(comment)
