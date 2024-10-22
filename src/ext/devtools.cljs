(ns ext.devtools
  (:require
   ["webextension-polyfill" :as browser]))

(defn create-panel []
  (browser/devtools.panels.create
   "Portal"
   "images/icon-48.png"
   "/dt/devtools-panel.html"))
