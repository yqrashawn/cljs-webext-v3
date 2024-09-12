(ns fswatcher
  (:require
   [babashka.pods :as pods]))

(pods/load-pod 'org.babashka/fswatcher "0.0.5")
(require '[pod.babashka.fswatcher :as fw])

(def watch fw/watch)

(defn watch! [path write-fn]
  (println (str "fswatch - " path))
  (fw/watch
   path
   (fn [event]
     (when (#{:write :write|chmod} (:type event))
       (println (str "fswatch [changes] - " path))
       (write-fn))))
  (deref (promise)))

(def unwatch fw/unwatch)
