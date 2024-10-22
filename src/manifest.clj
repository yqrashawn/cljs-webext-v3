(ns manifest
  (:require
   [cheshire.core :as json]
   [fswatcher :as fw]))

(defn generate []
  (let [dev? (= (System/getenv "DEV") "true")]
    (cond-> {:manifest_version          3
             :version                   "1.0.0"
             :permissions               [:devtools :activeTab :scripting :storage]
             :host_permissions          []
             :optional_host_permissions ["*://*/*"]
             :web_accessible_resources  []

             :devtools_page "dt/devtools.html"
             :content_scripts [{:matches           ["<all_urls>"]
                                ;; :matches           ["localhost"]
                                :run_at            "document_start"
                                :match_about_blank true
                                :js                ["cs/js/main.js"]}]
             :background      {:service_worker "bg/js/main.js"
                               :scripts ["bg/js/main.js"]
                               :type :module}
             :name            "cljs-webext"
             :description     "cljs web extension"
             :icons           {:48  "images/icon-48.png"
                               :64  "images/icon-64.png"
                               :96  "images/icon-96.png"
                               :128 "images/icon-128.png"
                               :256 "images/icon-256.png"
                               :512 "images/icon-512.png"}
             :action {:default_popup "popup/popup.html"}}

      dev?
      (update :background dissoc :service_worker)

      dev?
      (assoc-in [:background :scripts] ["bg/js/main.js"])
      ;; dev?
      ;; (assoc-in
      ;;  [:content_security_policy :extension_pages]
      ;;  (apply str
      ;;         ["default-src * 'unsafe-inline' 'unsafe-eval';"
      ;;          "script-src 'self' 'unsafe-eval' http://localhost:8063;"
      ;;          "style-src * 'unsafe-inline';"
      ;;          "img-src *;"
      ;;          "connect-src *;"
      ;;          "font-src *;"
      ;;          "object-src *;"
      ;;          "base-uri *;"
      ;;          "form-action *;"
      ;;          "media-src *;"
      ;;          "frame-src *"]
      ;;         ;; ["object-src 'self' 'unsafe-eval' http://localhost:8063;"
      ;;         ;;  "script-src 'self' 'unsafe-eval' http://localhost:8063;"
      ;;         ;;  "connect-src * data: blob: filesystem: ws://localhost:8063;"
      ;;         ;;  "style-src 'self' data: chrome-extension-resource: 'unsafe-inline' http://localhost:8063;"
      ;;         ;;  "frame-src 'self' http://localhost:* data: chrome-extension-resource:;"
      ;;         ;;  "font-src 'self' data: chrome-extension-resource:;"
      ;;         ;;  "media-src * data: blob: filesystem:;"]
      ;;         ))
      )))

(defn write []
  (load-file "src/manifest.clj")
  (println "Update resources/public/manifest.json")
  (let [json-str (json/generate-string (generate) {:pretty true})]
    (spit "resources/public/manifest.json" json-str)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn watch []
  (fw/watch! "src/manifest.clj" write))
