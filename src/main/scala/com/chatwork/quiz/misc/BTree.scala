package com.chatwork.quiz.misc

/**
 * [[BTree]]に格納される要素。
 */
sealed trait Node[+A] {

  /**
   * ノードが持つ値。
   */
  val value: A

  /**
   * ノード数。
   */
  val size: Int

  /**
   * ノードが保持するすべての値の合計値。
   */
  val sum: A

  /**
   * ノードが保持するすべての値の平均値。
   */
  val avg: Double

  /**
   * ノードが保持する最大値。
   */
  val max: A

  /**
   * ノードが保持する最小値。
   */
  val min: A

  /**
   * 指定した値を保持するノードを検索する。
   *
   * @param value 値
   * @return ノード
   */
  def find[B >: A](value: B): Option[Node[B]]

}

/**
 * 枝を表す[[Node]]。
 *
 * @param left　左の[[Node]]
 * @param value 値
 * @param right 右の[[Node]]
 */
case class Branch[+A](left: Node[A], value: A, right: Node[A]) extends Node[A] {

  val size: Int = ???

  val sum: A = ???

  val avg: Double = ???

  val max: A = ???

  val min: A = ???

  def find[B >: A](value: B): Option[Node[B]] = ???

}

/**
 * 葉を表す[[Node]]。
 *
 * @param value 値
 */
case class Leaf[+A](value: A) extends Node[A] {

  val size: Int = ???

  val sum: A = ???

  val avg: Double = ???

  val max: A = ???

  val min: A = ???

  def find[B >: A](value: B): Option[Node[B]] = ???

}

/**
 * 二分木データ構造。
 *
 * @param node 頂点のノード
 */
case class BTree[+A](node: Node[A]) {

  lazy val size: Int = node.size

  lazy val sum: A = node.sum

  lazy val avg: Double = node.avg

  lazy val max: A = node.max

  lazy val min: A = node.min

  def find[B >: A](value: B): Option[Node[B]] = node.find(value)

}

/**
 * [[BTree]]のコンパニオンオブジェクト。
 */
object BTree {

  /**
   * ファクトリメソッド。
   *
   * @param values ノードに格納する値の集合
   * @return [[BTree]]
   */
  def apply(values: List[Int]): BTree[Int] = ???

}

