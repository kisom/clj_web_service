(ns web_service.views.welcome
  (:require [web_service.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [cheshire.core]))

