package com.yinshangyi.udfs

import org.scalatest.flatspec.AnyFlatSpec

class AreaFromSSNTest extends AnyFlatSpec {

  "A area code" should "be extracted and returned from the SSN code" in {
    val inputSSN = "123-82-1234"
    val expectedAreaCode = "123"
    val udf = new AreaFromSSN()
    val actualAreaCode = udf.evaluate(inputSSN)
    assert(actualAreaCode.equals(expectedAreaCode))
  }

  "The Invalid SSN Code string" should "be returned from the SSN code if the area code is not valid" in {
    val inputSSN = "12-82-1234"
    val expectedAreaCode = "Invalid SSN Code"
    val udf = new AreaFromSSN()
    val actualAreaCode = udf.evaluate(inputSSN)
    assert(actualAreaCode.equals(expectedAreaCode))
  }

  "The Out of range string" should "be returned from the area code is out of range" in {
    val inputSSNTooSmall = "080-82-1234"
    val inputSSNTooBig = "250-82-1234"
    val expectedAreaCode = "Out of range"
    val udf = new AreaFromSSN()
    val actualAreaCodeTooSmall = udf.evaluate(inputSSNTooSmall)
    val actualAreaCodeTooBig = udf.evaluate(inputSSNTooBig)

    assert(actualAreaCodeTooSmall.equals(expectedAreaCode))
    assert(actualAreaCodeTooBig.equals(expectedAreaCode))
  }


}
