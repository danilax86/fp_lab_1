(ns fp-lab-1.tasks.task-1-test
  (:require [clojure.test :refer :all]
            [fp-lab-1.tasks.task_1 :refer :all]))


(deftest modular-test-task-1
  (testing "Test task 1 modular"
    (is (= (modular) 233168))))

(deftest reccursive-test-task-1
  (testing "Test task 1 recursive"
    (is (= (rec 0) 233168))))