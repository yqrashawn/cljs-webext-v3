(ns ext.devtools
  (:require
   ["webextension-polyfill" :as browser]
   [oops.core :as oops]
   [promesa.core :as p]
   [taoensso.encore :as enc]))

(defn create-panel []
  (browser/devtools.panels.create
   "Portal"
   "images/icon-48.png"
   "dt/devtools.html"))
