(ns web_service.views.api
  (:use [noir.core :only [defpartial defpage]]
        [web_service.views.common]
        [using_set_theory.library :only [book-str in? ]]
        [using_set_theory.sample_library]))

(defn filter-format
  "Filter books to a given format."
  [fmt books]
  (filter #(in?
            (:formats %)
            (keyword fmt))
          books))

(defn format-select
  "Get a list of books that have conform to the desired set operation."
  [f format-list]
   (let [books (get-library)]
     (apply f
            (for [fmt format-list]
              (set (filter-format (keyword fmt) books))))))

(defn format-intersect
  "Get a list of books that are in all the listed formats."
  [format-list]
  (format-select #'clojure.set/intersection format-list))

(defn format-union
  "Get a list of books that are in any of the listed formats."
  [format-list]
  (format-select #'clojure.set/union format-list))

(defn format-difference
  "Get a list of books that are in the first format but not in the second."
  [format-list]
  (if (not= (count format-list) 2) "wrong number of formats (need 2)"
      (format-select #'clojure.set/difference format-list)))

(defn get-all-formats
  "Returns a vector of all formats in the library."
  [books]
  (set
   (reduce #'concat
           (map :formats books))))

(defn select-mode
  "Returns an appropriate select mode for building subsets."
  [select]
  (let [valid-select-modes ["all" "any" "not"]]
    (if (in? valid-select-modes select)
      select
      "all")))
        
(defpage "/api/books" []
  (let [books (get-library)]
    (json-out books)))

(defpage "/api/books/formats" {:keys [formats select]}
  (json-out
   (if (nil? formats) (get-all-formats (get-library))
       (let [format-list (clojure.string/split formats #",")
             select (select-mode select)]
         (cond (= select "all") (format-intersect format-list)
               (= select "any") (format-union format-list)
               (= select "not") (format-difference format-list)
               true {})))))

(defpage "/api/books/format/:fmt" {:keys [fmt]}
  (json-out
   (filter-format fmt (get-library))))

(defpage "/api/books/titles" []
  (json-out
   (map :title (get-library))))

(defpage "/api/books/title/:title" {:keys [title]}
  (json-out
   (filter #(= title (:title %)) (get-library))))

(defpage "/api/authors" []
  (json-out
   (set
    (reduce #'concat
            (map :authors (get-library))))))

(defpage "/api/author/:author" {:keys [author]}
  (json-out
   (filter #(in? (:authors %) author) (get-library))))

