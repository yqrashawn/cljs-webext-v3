(ns app.bg
  (:require
   db
   ext.tab
   [oops.core :as oops]
   [promesa.core :as p]))

(defn valid-tab? [t]
  (and (oops/oget t "?url")
       (= (oops/oget t "?status") "complete")))

(defn clear-old-tabs-data []
  (->> (db/q '[:find [?tab ...]
               :in $ ?now
               :where
               [?tab :tab/updated-at ?updated-at]
               [(- ?now ?updated-at) ?d]
               ;; > 7 days
               [(> ?d 604800000)]]
             (.getTime (js/Date.)))

       (mapv #(vector :db/retractEntity %))
       db/t!))

(defn new-completed-tab [t]
  (let [url (oops/oget t "url")
        id  (oops/oget t "id")]
    (db/t! [{:tab/id  id
             :tab/updated-at (.getTime (js/Date.))
             :tab/url url}])))

(defn on-tab-updated [_id _change-info t]
  (when (valid-tab? t) (new-completed-tab t)))

(defn on-tab-removed [id]
  (db/t! [[:db/retractEntity [:tab/id id]]]))

(defn init! []
  (p/do
    (db/get-conn {:tab/id {:db/unique :db.unique/identity}})
    (clear-old-tabs-data)
    (ext.tab/on-updated on-tab-updated {})
    (ext.tab/on-removed on-tab-removed)))
