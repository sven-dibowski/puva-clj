(ns core)

(def places (atom 10))

(defn enter-garage []
  (when (> 0 @places)
    (swap! places dec ))
  (println "Es sind noch " @places " Plätze übrig"))

(defn leave-garage []
  (swap! places inc)
  (println "Es sind " @places " Plätze frei."))

(defn car
  [name]
  (while true
    ;; (println name ": --start")
    (Thread/sleep (rand-int 10000))
    (enter-garage)
    (println name ": eingefahren")
    (Thread/sleep (rand-int 20000))
    (leave-garage )
    (println name ": ausgefahren")
    ))

(defn main
  []
  ;; parking garage erzeugen

  ;; n autos erzeugen und garage übergeben
  (loop [x 1]
    (future (car (str x)))
    (when (< x 40)
      (recur (inc x)))
))


(comment   
  (main)
  )