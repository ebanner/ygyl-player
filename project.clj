(defproject myproject "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.7.0"]
                 [cljs-http "0.1.44"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.15"]]

  :min-lein-version "2.5.0"

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["lucky/static/lucky"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs ["lucky/static/lucky/css"]}

  :cljsbuild {:builds {:app
                       {:source-paths ["cljs/src" "cljs/env/dev/cljs"]
                        :compiler
                        {:main "myproject.dev"
                         :output-to "lucky/static/lucky/js/app.js"
                         :output-dir "lucky/static/lucky/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :optimizations :none
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "myproject.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}
                       :release
                       {:source-paths ["cljs/src" "cljs/env/prod/cljs"]
                        :compiler
                        {:output-to "lucky/static/lucky/js/app.js"
                         :output-dir "lucky/static/lucky/js/release"
                         :asset-path   "js/out"
                         :optimizations :advanced
                         :pretty-print false}}}}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.7"]
                                  [figwheel-sidecar "0.5.15"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]]}})
