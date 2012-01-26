(ns web_service.views.common
  (:use [noir.core :only [defpartial defpage]]
        [cheshire.core])
  (:require [clojure.string]
            [noir.response]))

(def version "1.0.0")

(defn text-out
  "Output a string as text/plain."
  [body & args]
  (noir.response/content-type "text/plain" body))

(defn json-out
  "Output a string as json plaintext."
  [body & args]
  (noir.response/json body))
;  (text-out (cheshire.core/generate-string body)))

(defn multiline
  "Easy way to put together a bunch of strings."
  [& args]
  (if (nil? args) "" (clojure.string/join " " args)))
