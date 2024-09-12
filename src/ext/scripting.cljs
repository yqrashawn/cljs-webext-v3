(ns ext.scripting)

(defn exec [opts]
  (js/chrome.scripting.executeScript (clj->js opts)))
