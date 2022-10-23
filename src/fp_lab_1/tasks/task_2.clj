(ns fp-lab-1.tasks.task-2)

; Заготовочки
(defn exp [x n]
  (reduce * (repeat n x)))

(defn as-digits [num]
  (map #(Character/getNumericValue (char %)) (str num)))

(defn sum-of-fifth-powers [num]
  (reduce + (map #(exp % 5) (as-digits num))))


; Модульная реализация
(def gen-seq
  (range 2 (exp 10 6)))

(def filtered-seq
  (filter #(= % (sum-of-fifth-powers %))
          gen-seq))

(def sum-seq-elms
  (reduce + filtered-seq))

(defn modular []
  sum-seq-elms
  )


; Рекурсия
(defn rec [n]
  (if (> n 2)
    (if (= n (sum-of-fifth-powers n))
      (+ n (rec (- n 1)))
      (rec (- n 1)))
    (if (= n 2) 0 n))
  )


; Хвостовая рекурсия
; todo REWRITE
(defn tail-rec [n]
  (if (= n 2) 0
              (if (= n (sum-of-fifth-powers n)) (+ n (tail-rec (- n 1)))
                                                (tail-rec (- n 1))))
  )


; Генерация с map
; todo
;(defn solve-map []
;  )
;
;(solve-map)


; Ленивые коллекции
(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq
         (cons n                                            ; Добавляем к последовательности элемент n
               (positive-numbers (inc n))))))

(defn solve-lazy []
  (reduce +
          (filter #(= % (sum-of-fifth-powers %))
                  (take (exp 10 6)
                        (drop 1 (positive-numbers))))))     ; drop 1 -- исключаем всё, что ниже 2-х
