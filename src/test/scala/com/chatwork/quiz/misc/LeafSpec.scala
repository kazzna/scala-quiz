package com.chatwork.quiz.misc

import org.scalatest.{ Matchers, FunSpec }

import scala.math.Ordering.IntOrdering

class LeafSpec extends FunSpec with Matchers {

  describe("Leaf#size") {
    it("should return 1") {
      Leaf(1).size shouldBe 1
      Leaf(1.0).size shouldBe 1
    }
  }

  describe("Leaf#max") {
    it("should return the value of Leaf") {
      Leaf(1).max shouldBe 1
      Leaf(3.21).max shouldBe 3.21
    }
  }

  describe("Leaf#min") {
    it("should return the value of Leaf") {
      Leaf(1).min shouldBe 1
      Leaf(3.21).min shouldBe 3.21
    }
  }

  describe("Leaf#sum") {
    it("should return the value of Leaf") {
      Leaf(1).sum shouldBe 1
      Leaf(3.21).sum shouldBe 3.21
    }
  }

  describe("Leaf#avg") {
    it("should return the double value") {
      Leaf(1).avg shouldBe 1.toDouble
      Leaf(BigDecimal(3.21)).avg shouldBe 3.21
    }
  }

  describe("Leaf#find") {
    it("should return a node has the value in the Leaf") {
      Leaf(1).find(1) shouldBe Some(Leaf(1))
      Leaf(4.24f).find(4.24f) shouldBe Some(Leaf(4.24f))
    }
  }
}
