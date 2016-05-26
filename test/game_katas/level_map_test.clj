(ns game-katas.level-map-test
  (:require [clojure.test :refer :all]
            [game-katas.level-map :refer :all]))

(deftest parsing-test
  (testing "parsing empty input"
    (is (empty? (:world (parse [])))))
  (testing "single empty string input"
    (is (empty? (:world (parse [""])))))
  (testing "single wall input"
    (is (empty? (:world (parse ["#"])))))
  (testing "single cell input"
    (is (= #{[0 0]} (:world (parse ["."])))))
  (testing "two cells in the same row"
    (is (= #{[0 0] [1 0]} (:world (parse [".."])))))
  (testing "cell and wall in the same row"
    (is (= #{[1 0]} (:world (parse ["#."])))))
  (testing "two cell in a column"
    (is (= #{[0 0] [0 1]} (:world (parse ["." "."])))))

  (testing "comples set of cell and walls"
    (let [input ["...##"
                 "#.##."
                 "##.##"]
          expected #{[0 0] [1 0] [2 0]
                     [1 1] [4 1]
                     [2 2]}]
      (is (= expected (:world (parse input))))))

  (testing "reading start position"
    (is (= [0 0] (:start (parse ["S"])))))
  (testing "start postion is part of the world"
    (is (= #{[0 0]} (:world (parse ["S"])))))

  (testing "reading end position"
    (is (= [0 0] (:end (parse ["E"])))))
  (testing "end postion is part of the world"
    (is (= #{[0 0]} (:world (parse ["E"])))))

)
