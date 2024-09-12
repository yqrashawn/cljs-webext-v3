(ns ext.storage
  (:refer-clojure :exclude [get set])
  (:require
   ["webextension-polyfill" :as browser]))

(defn get [keys]
  (browser/storage.local.get (clj->js keys)))

(defn set [keys]
  (browser/storage.local.set (clj->js keys)))
