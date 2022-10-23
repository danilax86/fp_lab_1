(ns fp-lab-1.tasks.task-1-test
  (:require [clojure.test :refer :all]
            [fp-lab-1.tasks.task-1 :refer :all]))

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