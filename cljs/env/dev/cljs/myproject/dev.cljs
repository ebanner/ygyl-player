(ns ^:figwheel-no-load myproject.dev
  (:require
    [myproject.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
