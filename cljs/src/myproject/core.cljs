(ns myproject.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; -------------------------
;; Application State

(defonce webm (r/atom "a.webm"))

;; -------------------------
;; Helper Functions

(defn next-webm-name
  "Get the next webm name.
  There are only two webm names. It's either \"a.webm\" or \"b.webm\". These two
  cycle back and forth. It does not take any arguments, rather it looks at the
  global atom which contains the current webm name."
  []
  (if (= @webm "a.webm")
    "b.webm"
    "a.webm"))

(defn swap-and-prefetch-webm
  "Swap the next webm in the DOM and download the next one.
  This function is called when a webm ends. Swapping the webm in the DOM
  triggers another webm to start. We immediately prefetch another webm so that
  when this one ends the next one starts immediately."
  []
  (swap! webm #(next-webm-name))
  (go (let [response (<! (http/post "http://localhost:8000/lucky/"
                                    {:with-credentials? false
                                     :json-params {:filename (next-webm-name)}}))]
        (js/console.log response))))

;; -------------------------
;; Componenets

(defn home-page
  "It's the home page component.
  It's a single video webm element. Make sure to swap out the current webm with
  the next one when it finishes."
  []
  [:video {:controls true
           :id "webm"
           :on-ended #(swap-and-prefetch-webm)
           :src @webm
           :auto-play true}])

;; -------------------------
;; Initialize app

(defn init! []
  (r/render [home-page] (.getElementById js/document "app")))
