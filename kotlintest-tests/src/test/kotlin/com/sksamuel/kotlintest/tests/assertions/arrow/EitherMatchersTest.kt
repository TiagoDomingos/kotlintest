package com.sksamuel.kotlintest.tests.assertions.arrow

import arrow.core.Either
import io.kotlintest.assertions.arrow.either.beLeft
import io.kotlintest.assertions.arrow.either.beRight
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

class EitherMatchersTest : WordSpec() {

  init {

    "Either shouldBe right(value)" should {
      "test that an either is a right with the given value" {

        shouldThrow<AssertionError> {
          Either.left("foo") should beRight("boo")
        }.message shouldBe "Either should be Right(boo) but was Left(foo)"

        shouldThrow<AssertionError> {
          Either.right("foo") should beRight("boo")
        }.message shouldBe "Either should be Right(boo) but was Right(foo)"

        Either.right("boo") should beRight("boo")
      }
    }

    "Either shouldBe left(value)" should {
      "test that an either is a left with the given value" {

        shouldThrow<AssertionError> {
          Either.right("foo") should beLeft("boo")
        }.message shouldBe "Either should be Left(boo) but was Right(foo)"

        shouldThrow<AssertionError> {
          Either.left("foo") should beLeft("boo")
        }.message shouldBe "Either should be Left(boo) but was Left(foo)"

        Either.left("boo") should beLeft("boo")
      }
    }
  }
}