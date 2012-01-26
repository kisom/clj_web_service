(ns web_service.views.device
  (:use [noir.core :only [defpage]]
        [web_service.views.api]
        [web_service.views.common]
        [using_set_theory.library]
        [using_set_theory.sample_library :only [get-library]]))

(def device-map
  #^{:doc "Mapping of devices to supported formats."}
  {:ipad [:epub :pdf]
   :kindle [:mobi :pdf]
   :ipod [:epub :pdf]
   :nook [:epub :pdf]
   :kobo [:epub :pdf]})

(defpage "/api/devices" []
  (json-out
   (keys device-map)))

(defpage "/api/devices/:device" {:keys [device]}
  (json-out
   (let [device (keyword device)]
     (if (in? device-map device)
       (format-union (device device-map))
       "device not found"))))