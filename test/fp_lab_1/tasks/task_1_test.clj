(ns fp-lab-1.tasks.task-1-test
  (:require [clojure.test :refer :all]
            [fp-lab-1.tasks.task-1 :refer :all]))

(def answer 233168)

(deftest test-task-1
  (testing "Test task 1"
    (is (= (modular) answer))
    (is (= (rec 0) answer))
    (is (= (tail-rec 999) answer))
    (is (= (solve-map) answer))
    (is (= (solve-lazy) answer))))