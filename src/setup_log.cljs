(ns setup-log
  (:require
   [lambdaisland.glogi :as log]
   [lambdaisland.glogi.console :as glogi-console]))

(glogi-console/install!)

(when (and (> (.indexOf js/window.navigator.userAgent "Mozilla") -1)
           (lambdaisland.glogi.console/devtools-installed?))
  (log/remove-handler (glogi-console/select-handler))
  (log/add-handler-once #'lambdaisland.glogi.console/console-log-css))

(log/set-levels {:glogi/root (if goog.DEBUG :all :info)})
