(ns core)

;; number of available parking slots
(def slots (atom 10))

(defn- remaining-slots 
  "print the value of remaining parking slots"
  []
  (println (format "-------------------[%-2d slots left]" @slots)))

(defn enter-garage   
  [name]
  (if (> @slots 0)
    (do (swap! slots dec)
        (println name ":-> entered")
        (remaining-slots)
        :parking)
    (do
      (println (format "%-2d: couldn't enter" (Integer. name)))
      :driving)))

(defn leave-garage   
  [name]
  (swap! slots inc)
  (println name ":<- left")
  (remaining-slots))

(defn car
  [id state]
  (loop [state state]
    (if  (= state :driving)
      (do
        (Thread/sleep (rand-int 3000))
        (recur (enter-garage id)))
      (do
        (Thread/sleep (rand-int 10000))
        (leave-garage id)
        (recur :driving)))))

(defn main 
  "Create n cars and init the program"
  [n]
  (loop [x 1]
    (future (car (str x) :driving))
    (when (< x n)
      (recur (inc x)))))

(comment  
  (main 40))
