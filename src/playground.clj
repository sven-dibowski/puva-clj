(ns playground)

(def blocker (atom false))

(defn pass-blocker[]
  (if @blocker
   (println "Blocker passiert")
   (println "Blocker nicht passiert")))

(defn main []
  (future (pass-blocker)))

(main)

(reset! blocker true)