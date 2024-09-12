(ns app.dt
  (:require
   ext.devtools
   ext.scripting
   [oops.core :as oops]))

(defn detect-portal-web []
  (js/console.log (oops/oget js/window "?portal")))

(defn init! []
  (ext.devtools/create-panel))

(comment
  (ext.scripting/exec {:func detect-portal-web}))
