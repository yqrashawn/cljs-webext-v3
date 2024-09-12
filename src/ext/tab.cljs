(ns ext.tab
  (:require
   ["webextension-polyfill" :as browser]))

(defn on-removed [listener]
  (when (and listener (not (browser/tabs.onRemoved.hasListener listener)))
    (browser/tabs.onRemoved.addListener listener)))

(defn on-created [listener]
  (when (and listener (not (browser/tabs.onCreated.hasListener listener)))
    (browser/tabs.onCreated.addListener listener)))

(defn on-activated [listener]
  (when (and listener (not (browser/tabs.onActivated.hasListener listener)))
    (browser/tabs.onActivated.addListener listener)))

(defn on-updated [listener opts]
  (when (and listener (not (browser/tabs.onUpdated.hasListener listener)))
    (browser/tabs.onUpdated.addListener listener (clj->js opts))))
