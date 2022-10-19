(ns fp-lab-1.tasks.task_1)

(defn modular []
  (reduce + (filter #(or
                       (zero? (rem % 3))
                       (zero? (rem % 5)))
                    (range 1000)))
  )

(modular)

(defn rec [n]
  (if (and (< n 1000))
    (if (or (zero? (rem n 3))
            (zero? (rem n 5)))
      (+ n (rec (+ n 1)))
      (rec (+ n 1)))
    0)
  )

(rec 0)
