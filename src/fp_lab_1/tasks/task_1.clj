(ns fp-lab-1.tasks.task-1)

; Модульная реализация
(def gen-seq
  (range 1000))

(def filtered-seq
  (filter #(or
             (zero? (rem % 3))
             (zero? (rem % 5))) gen-seq))

(def sum-seq-elms (reduce + filtered-seq))

(defn modular []
  sum-seq-elms
  )

; Рекурсия
(defn rec [n]
  (if  (< n 1000)
    (if (or (zero? (rem n 3))
            (zero? (rem n 5)))
      (+ n (rec (+ n 1)))
      (rec (+ n 1)))
    0)
  )

;; Хвостовая рекурсия
(defn tail-rec [n]
  (if (= n 0) 0
              (if (or (zero? (rem n 3))
                      (zero? (rem n 5)))
                (+ n (tail-rec (- n 1)))
                (tail-rec (- n 1)))
              ))

; Генерация с map
(defn solve-map []
  (reduce + (distinct                                       ; Убираем дубли
              (into                                         ; Совмещаем две последовательности
                (map #(* % 3) (range 1 334))
                (map #(* % 5) (range 1 200)))))
  )

; Ленивые коллекции
(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq
         (cons n                                            ; Добавляем к последовательности элемент n
               (positive-numbers (inc n))))))

(defn solve-lazy []
  (reduce + (filter #(or
                       (zero? (rem % 3))
                       (zero? (rem % 5)))
                    (take 999 (positive-numbers))))
  )
