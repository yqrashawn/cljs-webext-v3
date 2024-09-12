(ns ext.runtime
  (:require
   ["webextension-polyfill" :as browser]))

(defn connect [& args]
  (apply browser/runtime.connect args))
