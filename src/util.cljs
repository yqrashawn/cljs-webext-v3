(ns util
  (:require
   [clojure.core.async :as a]))

(defn wait-till
  ([pred cb] (wait-till pred cb nil))
  ([pred cb {:keys [timeout-ms interval-ms]}]
   (let [timeout-ms  (or timeout-ms 5000)
         interval-ms (or interval-ms 200)]
     (a/go-loop [timeout timeout-ms]
       (when (pos? timeout)
         (if-let [rst (pred)]
           (cb rst)
           (do (a/<! (a/timeout interval-ms))
               (recur (- timeout interval-ms)))))))))
