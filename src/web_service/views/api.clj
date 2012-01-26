(ns web_service.views.api
  (:use [noir.core :only [defpartial defpage]]
        [web_service.views.common]
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
    (json-out books)))

(defpage "/api/books/formats" []
  (let [formats (set
                 (reduce #'concat
                         (map :formats (get-library))))]
    (json-out formats)))

(defpage "/api/books/format/:fmt" {:keys [fmt]}
  (json-out
   (filter-format fmt (get-library))))

(defpage "/api/books/titles" []
  (json-out
   (map :title (get-library))))

(defpage "/api/books/title/:title" {:keys [title]}
  (json-out
   (filter #(= title (:title %)) (get-library))))
