package com.chatwork.quiz.collection

import com.chatwork.quiz.{ MyNone, MyOption, MySome }

sealed trait MyList[+A] {

  // Easy
  def length: Int = this.foldLeft(0) { (a, _) => a + 1 }

  // Normal
  def foldLeft[B](z: B)(f: (B, A) => B): B = {
    def g(as: MyList[A])(b: B): B = as match {
      case MyCons(h, t) => g(t)(f(b, h))
      case _            => b
    }
    g(this)(z)
  }

  // 難易度選択制
  // Normal: 条件 - 特にありません、気の向くままに実装してください。
  // Hard:   条件 - foldLeftを使って実装してください。
  def foldRight[B](z: B)(f: (A, B) => B): B = this.reverse.foldLeft(z) { (b, a) => f(a, b) }

  // Normal
  // scalastyle:off
  def ::[B >: A](b: B): MyList[B] = MyCons(b, this)
  // scalastyle:on

  // Normal
  def reverse: MyList[A] = this.foldLeft(MyList.empty[A]) { (as, a) => a :: as }

  // Normal
  // scalastyle:off
  def ++[B >: A](b: MyList[B]): MyList[B] = this.foldRight(b) { (a, b) => a :: b }
  // scalastyle:on

  // Normal
  def map[B](f: A => B): MyList[B] = this flatMap { a => MyList(f(a)) }

  // Normal
  def flatMap[B](f: A => MyList[B]): MyList[B] = this.foldLeft(MyList.empty[B]) { (a, b) => a ++ f(b) }

  // Normal
  def filter(f: A => Boolean): MyList[A] = this.foldRight(MyList.empty[A]) { (a, b) =>
    if (f(a)) a :: b else b
  }

  // Normal: 条件 - filterと同様の実装でも構いません。
  // Hard:   条件 - 中間リストを生成しないように実装してください。
  def withFilter(f: A => Boolean): MyList[A] = {
    case class WithFilter[A](as: MyList[A], f: A => Boolean) extends MyList[A] {
      override def foldLeft[B](z: B)(g: (B, A) => B): B = {
        def h(as: MyList[A])(b: B): B = as match {
          case MyCons(a, as) => if (f(a)) h(as)(g(b, a)) else h(as)(b)
          case _             => b
        }
        h(as)(z)
      }

      override def withFilter(g: A => Boolean): MyList[A] = {
        val h = (a: A) => f(a) && g(a)
        WithFilter(as, h)
      }
    }
    WithFilter(this, f)
  }

  // Normal
  def find(f: A => Boolean): MyOption[A] = this.filter(f) match {
    case MyCons(a, _) => MySome(a)
    case _            => MyNone
  }

  // Normal
  def startsWith[B >: A](prefix: MyList[B]): Boolean = ???

}

case object MyNil extends MyList[Nothing]

case class MyCons[+A](head: A, tail: MyList[A]) extends MyList[A]

object MyList {

  // Easy
  def empty[A]: MyList[A] = MyNil

  // Normal
  def apply[A](as: A*): MyList[A] =
    as.foldRight(MyList.empty[A]) { MyCons(_, _) }
}
