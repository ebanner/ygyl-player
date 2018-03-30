(ns myproject.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [clojure.string :as str]))

;; -------------------------
;; Constants

(def webm-basename
  "The basename of the webm url on the server."
  "ygyl.webm")

(def server-endpoint
  "The basename of the webm url on the server."
  "http://localhost/lucky/webm")

;; -------------------------
;; Application State

(defonce webm-source (r/atom nil))

;; -------------------------
;; Helper Functions

(defn fetch-webm
  "Download a webm.
  When theh download finishes update the application state so that the webm
  component will be re-rendered immediately and the new webm will show up.
  Currently use a cache-buster so the browser picks up on the change (FIXME do
  better)."
  []
  (go (let [response (<! (http/get server-endpoint
                                    {:with-credentials? false}))]
        (reset! webm-source (:url (:body response))))))

;; -------------------------
;; Componenets

(defn video-player
  "A single html video element containing the webm.
  Auto-play the webm and fetch the next webm when on end."
  [source]
  [:video {:controls true
           :id "webm"
           :on-ended (fn []
                       (reset! webm-source nil) ; trigger the loading message
                       (fetch-webm))
           :src source
           :auto-play true}])

(defn caption
  "Text under the webm.
  Displays the source per
  https://github.com/4chan/4chan-API#api-terms-of-service."
  [source]
  [:div
   [:a {:href source} "source"]])

(defn webm
  "Parent component for webm and caption.
  Because webm and caption need to be synchronized we push the state up into its
  parent and send the state down on a render. The first time this component is
  rendered we will not have downloaded a webm yet. In that case display a
  loading message."
  []
  (if @webm-source
    [:div
     [video-player @webm-source]
     [caption @webm-source]]
    [:p "Fetching something groovy..."]))

(defn app
  "Top-level entry component."
  []
  [:div
   [:h1 "YGYL Player"]
   [webm]])

;; -------------------------
;; Initialize app

(defn init!
  "Make sure you fetch a webm and then render the component when you are done."
  []
  (r/render [app] (js/document.getElementById "app"))
  (fetch-webm))
