(ns game-katas.level-map-test
  (:require [clojure.test :refer :all]
            [game-katas.level-map :refer :all]))

(deftest parsing-test
  (testing "parsing empty input"
    (is (empty? (:cells (parse [])))))
  (testing "single empty string input"
    (is (empty? (:cells (parse [""])))))
  (testing "single wall input"
    (is (empty? (:cells (parse ["#"])))))
  (testing "single cell input"
    (is (= {:cells #{[0 0]}} (parse ["."]))))
  (testing "two cells in the same row"
    (is (= #{[0 0] [1 0]} (:cells (parse [".."])))))
  (testing "cell and wall in the same row"
    (is (= #{[1 0]} (:cells (parse ["#."])))))
  (testing "two cell in a column"
    (is (= #{[0 0] [0 1]} (:cells (parse ["." "."])))))

  (testing "comples set of cell and walls"
    (let [input ["...##"
                 "#.##."
                 "##.##"]
          expected #{[0 0] [1 0] [2 0]
                     [1 1] [4 1]
                     [2 2]}]
      (is (= expected (:cells (parse input))))))
)
