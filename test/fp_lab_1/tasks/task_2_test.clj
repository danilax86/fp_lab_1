(ns fp-lab-1.tasks.task-2-test
  (:require [clojure.test :refer :all]
            [fp-lab-1.tasks.task-2 :refer :all]))

(def answer 443839)

(deftest test-task-2
  (testing "Test task 2"
    (is (= (modular) answer))
    (is (= (rec limit) answer))
    (is (= (tail-rec limit) answer))
    (is (= (solve-map) answer))
    (is (= (solve-lazy) answer))))