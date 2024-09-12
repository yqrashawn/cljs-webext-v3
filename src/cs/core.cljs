(ns cs.core
  (:require
   ext.runtime
   setup-log))

(defn setup-connection-with-bg []
  (let [port (ext.runtime/connect)]
    (js/console.log port)))

(defn init! []
  (js/console.log "content-script")
  (setup-connection-with-bg))
