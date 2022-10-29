(ns fp-lab-1.tasks.task-2-test
  (:require [clojure.test :refer :all]
            [fp-lab-1.tasks.task-2 :refer :all]))

(def answer 443839)

(deftest modular-test-task-2
  (testing "Test task 2 modular"
    (is (= (modular) answer))))

(deftest recursive-test-task-2
  (testing "Test task 2 recursive"
    (is (= (rec (exp 10 6)) answer))))

(deftest tail-recursive-test-task-2
  (testing "Test task 2 tail recursion"
    (is (= (tail-rec (exp 10 6)) answer))))

(deftest map-test-task-2
  (testing "Test task 2 with map"
    (is (= (solve-map) answer))))

(deftest lazy-test-task-2
  (testing "Test task 2 lazy-seq"
    (is (= (solve-lazy) answer))))