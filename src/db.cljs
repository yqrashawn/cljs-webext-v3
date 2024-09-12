(ns db
  (:require
   [datascript.core :as d]
   ext.storage
   [lambdaisland.glogi :as log]
   [oops.core :as oops]
   [promesa.core :as p]))

(declare set-db)

(def ^:dynamic conn nil)

(defn conn-watcher [x y]
  (set-db (d/db y)))

(defn get-conn [& args]
  (p/let [storage (ext.storage/get [:__datascript_db__])
          db-data (oops/oget storage :?__datascript_db__)
          db (if db-data
               (d/from-serializable (js->clj db-data))
               (apply d/empty-db args))]

    #_{:clj-kondo/ignore [:inline-def]}
    (def conn (d/conn-from-db db))
    (add-watch conn :watcher conn-watcher)
    conn))

(defn set-db [db]
  (ext.storage/set {:__datascript_db__ (d/serializable db)}))

(defn q [query & args]
  (apply d/q query (d/db conn) args))

(defn p [pattern eid]
  (d/pull (d/db conn) pattern eid))

(defn pm [pattern eids]
  (d/pull (d/db conn) pattern eids))

(defn t! [txs]
  (let [rst (d/transact! conn (log/spy txs))]
    rst))

(comment
  ;; discover db
  (db/q
   '[:find (pull ?x [*])
     :where
     [?x]])
  ;;
  )
