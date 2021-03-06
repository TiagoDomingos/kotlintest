package io.kotlintest.assertions.arrow.either

import arrow.core.Either
import io.kotlintest.Matcher
import io.kotlintest.Result
import io.kotlintest.should
import io.kotlintest.shouldNot

fun <B> Either<Any, B>.shouldBeRight(b: B) = this should beRight(b)
fun <B> Either<Any, B>.shouldNotBeRight(b: B) = this shouldNot beRight(b)
fun <B> beRight(b: B) = object : Matcher<Either<Any, B>> {
  override fun test(value: Either<Any, B>): Result {
    return when (value) {
      is Either.Left -> {
        Result(false, "Either should be Right($b) but was Left(${value.a})", "Either should not be Right($b)")
      }
      is Either.Right -> {
        if (value.b == b)
          Result(true, "Either should be Right($b)", "Either should not be Right($b)")
        else
          Result(false, "Either should be Right($b) but was Right(${value.b})", "Either should not be Right($b)")
      }
    }
  }
}

fun <A> Either<A, Any>.shouldBeLeft(a: A) = this should beLeft(a)
fun <A> Either<A, Any>.shouldNotBeLeft(a: A) = this shouldNot beLeft(a)
fun <A> beLeft(a: A) = object : Matcher<Either<A, Any>> {
  override fun test(value: Either<A, Any>): Result {
    return when (value) {
      is Either.Right -> {
        Result(false, "Either should be Left($a) but was Right(${value.b})", "Either should not be Right($a)")
      }
      is Either.Left -> {
        if (value.a == a)
          Result(true, "Either should be Left($a)", "Either should not be Left($a)")
        else
          Result(false, "Either should be Left($a) but was Left(${value.a})", "Either should not be Right($a)")
      }
    }
  }
}