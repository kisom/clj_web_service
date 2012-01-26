(ns web_service.views.api
  (:use [noir.core :only [defpartial defpage]]
        [using_set_theory.library :only [book-str in? ]]
        [using_set_theory.sample_library])
  (:require [clojure.string]
            [cheshire.core :as json])
  (:import [using_set_theory.library.Book]))

(defn filter-format
  "Filter books to a given format."
  [fmt books]
  (filter #(in?
            (:formats %)
            (keyword fmt))
          books))

(defpage "/api/books" []
  (let [books (get-library)]
    (json/generate-string books)))


(defpage "/api/books/format/:fmt" {:keys [fmt]}
  (json/generate-string
   (filter-format fmt (get-library))))

