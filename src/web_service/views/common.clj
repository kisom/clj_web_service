(ns web_service.views.common
  (:use [noir.core :only [defpartial defpage]]
        [cheshire.core]
        [using_set_theory.sample_library])
  (:require [clojure.string]))

(def version "1.0.0")

(defn multiline
  "Easy way to put together a bunch of strings."
  [& args]
  (if (nil? args) "" (clojure.string/join " " args)))
  
(defpage "/" []
  (multiline
   "This is an illustrative web API for the book library. I wrote this for "
   "a blog post on using set theory:"
   "http://kisom.github.com/blog/2012/01/??/using-set-theory/"))

(defpage "/api" []
  (generate-string { :version version }))

(defpage "/help" []
  (slurp "resources/public/help"))