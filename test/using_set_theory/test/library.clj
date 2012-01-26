(ns using_set_theory.test.library
  (:use [using_set_theory.library])
  (:import [using_set_theory.library Book])
  (:use [clojure.test]))

(def invalid-book (Book. "Invalid Format" [ "NULL" ]
                         "This should throw an error."
                         [:foo]))

(def valid-epub (Book. "Valid ePub" [ "" ]
                       "Mobi format."
                       [:epub]))

(def valid-mobi (Book. "Valid Mobi" [ "" ]
                       "Mobi format."
                       [:mobi]))

(def valid-pdf  (Book. "Valid PDF" [""]
                       "PDF format."
                       [:pdf]))

(def valid-christmas-tree
  (Book. "Christmas Tree" [""]
         "All the formats!"
         [:epub :mobi :pdf]))

(def valid-formats [valid-epub valid-mobi valid-pdf valid-christmas-tree])

(deftest collection-integrity
  "Ensure that an illegal state exception is thrown if an invalid format is used."
  (is (= 4 (count (filter #'valid-format? valid-formats)))))