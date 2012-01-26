(ns web_service.views.root
  (:use [noir.core :only [defpartial defpage]]
        [web_service.views.common]
        [using_set_theory.sample_library])
  (:require [clojure.string]
            [noir.response]
            [noir.statuses]))

(defpage "/" []
  (text-out
   (multiline
    "This is an illustrative web API for the book library. I wrote this for "
    "a blog post on using set theory:\n"
    "\thttp://kisom.github.com/blog/2012/01/??/using-set-theory/\n"
    "\n\nthe source is on github: "
    "\n\tgit clone git://github.com/kisom/clj_web_service.git\n"
    "\nGET /help for usage")))

(noir.statuses/set-page! 404 "not found")

(defpage "/api" []
  (json-out { :version version }))

(defpage "/help" []
  (text-out (slurp "resources/public/help")))