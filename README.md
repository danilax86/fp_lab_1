# fp_lab_1

**Университет ИТМО**

Факультет программной инженерии и компьютерной техники \
Направление подготовки 09.03.04 Программная инженерия

Дисциплина "Функциональное программирование"

Лабораторная работа №1                                  \
Вариант 1, 30

Горелко Данила


## Problem 1
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

### Solution

#### Модульная реализация
```clojure
(def gen-seq
  (range 1000))

(def filtered-seq
  (filter #(or
             (zero? (rem % 3))
             (zero? (rem % 5))) gen-seq))

(def sum-seq-elms 
  (reduce + filtered-seq))
```

#### Рекурсия
```clojure
(defn rec [n]
  (if  (< n 1000)
    (if (or (zero? (rem n 3))
            (zero? (rem n 5)))
      (+ n (rec (+ n 1)))
      (rec (+ n 1)))
    0)
  )
```

#### Хвостовая рекурсия
```clojure
(defn tail-rec [n]
  (if (= n 0) 0
              (if (or (zero? (rem n 3))
                      (zero? (rem n 5)))
                (+ n (tail-rec (- n 1)))
                (tail-rec (- n 1)))
              ))
```

#### Генерация с `map`
```clojure
(defn solve-map []
  (reduce + (distinct                                       ; Убираем дубли
              (into                                         ; Совмещаем две последовательности
                (map #(* % 3) (range 1 334))
                (map #(* % 5) (range 1 200)))))
  )
```

#### Ленивые коллекции
```clojure
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
```

### Тесты

Просто проверяем с ответом :)

```clojure
(def answer 233168)

(deftest modular-test-task-1
  (testing "Test task 1 modular"
    (is (= (modular) answer))))

(deftest recursive-test-task-1
  (testing "Test task 1 recursive"
    (is (= (rec 0) answer))))

(deftest tail-recursive-test-task-1
  (testing "Test task 1 tail recursion"
    (is (= (tail-rec 999) answer))))

(deftest map-test-task-1
  (testing "Test task 1 with map"
    (is (= (solve-map) answer))))

(deftest lazy-test-task-1
  (testing "Test task 1 lazy-seq"
    (is (= (solve-lazy) answer))))
```

## Problem 2
Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

    1634 = 1^4 + 6^4 + 3^4 + 4^4
    8208 = 8^4 + 2^4 + 0^4 + 8^4
    9474 = 9^4 + 4^4 + 7^4 + 4^4

As 1 = 1^4 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

### Solution

Здесь максимальное значение элемента последовательности нужно брать как k * (10 - 1)^5 = 295245. \
`k` -- минимальное количетсво цифр, на котором закончится программа; 5 -- в данном случае \
`10` -- потому что числа с основанием 10, логично.


#### Заготовочки
```clojure
(def limit 295245)

(defn exp [x n]
  (reduce * (repeat n x)))

(defn as-digits [num]
  (map #(Character/getNumericValue (char %)) (str num)))

(defn sum-of-fifth-powers [num]
  (reduce + (map #(exp % 5) (as-digits num))))
```

#### Модульная реалиазция
```clojure
(def gen-seq
  (range 2 limit))

(def filtered-seq
  (filter #(= % (sum-of-fifth-powers %))
          gen-seq))

(def sum-seq-elms
  (reduce + filtered-seq))
```

#### Рекурсия
```clojure

(defn rec [n]
  (if (> n 2)
    (if (= n (sum-of-fifth-powers n))
      (+ n (rec (- n 1)))
      (rec (- n 1)))
    (if (= n 2) 0 n))
  )
```

#### Хвостовая рекурсия
```clojure
(defn tail-rec [n]
  (if (= n 2) 0
              (if (= n (sum-of-fifth-powers n)) (+ n (tail-rec (- n 1)))
                                                (tail-rec (- n 1))))
  )
```

#### Генерация с map
```clojure
(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq
         (cons n                                 ; Добавляем к последовательности элемент n
               (positive-numbers (inc n))))))

(defn solve-map []
  (reduce +                                      ; Складываем элементы
          (remove zero?                          ; Избавляемся от нулей в последовательности
                  (map #(* %                     ; Заменяем нулями числа, которые не подходят под условие
                           (if (= % (sum-of-fifth-powers %)) 1 0))
        (take limit
              (drop 1 (positive-numbers)))))))
```

#### Ленивые коллекции
```clojure
(defn solve-lazy []
  (reduce +
          (filter #(= % (sum-of-fifth-powers %))
                  (take limit
                        (drop 1 (positive-numbers))))))     ; drop 1 -- исключаем всё, что ниже 2-х

```

### Тесты

Просто проверяем с ответом :)

```clojure
(def answer 443839)

(deftest modular-test-task-2
  (testing "Test task 2 modular"
    (is (= (modular) answer))))

(deftest recursive-test-task-2
  (testing "Test task 2 recursive"
    (is (= (rec limit) answer))))

(deftest tail-recursive-test-task-2
  (testing "Test task 2 tail recursion"
    (is (= (tail-rec limit) answer))))

(deftest map-test-task-2
  (testing "Test task 2 with map"
    (is (= (solve-map) answer))))

(deftest lazy-test-task-2
  (testing "Test task 2 lazy-seq"
    (is (= (solve-lazy) answer))))
```

## Вывод

В ходе выполнения данной лабораторной работы я познакомился с основами языка Clojure.

Научился генерировать, фильтровать, совмещать по условию,
сворачивать последовательность и преобразовывать её в последовательность с уникальными элементами.

Познакомился с именоваными, анонимными и функциями высшего порядка (Higher-Order), а также ленивыми последовательностями.

_Насчёт хвостовых рекурсий.. Хе-хе-хе.._
Хвостовая рекурсия -- рекурсия, когда рекурсивный вызов будет последним действием функции перед возвратом результата.
Вызов такой рекурсии необязательно должен быть вызовом именно текущей функции, 
Как таковой поддержки хвостовой рекурсии в Clojure нет.
Однако можно оптимизировать, собрав из хвостового алгоритма цикл.
