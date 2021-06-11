package com.yinshangyi.udfs

import org.apache.hadoop.hive.ql.exec.UDF

class AreaFromSSN extends UDF {

  /**
   * Return the Area code from the SSN code
   * @param str ssn code
   * @return Area code
   */
  def evaluate(ssn: String): String = {
    val ssnSplited = ssn.split("-")

    if (ssnSplited.length != 3 || ssnSplited(0).length != 3)
      return "Invalid SSN Code"

    ssnSplited(0).toInt match {
      case area if area < 100 => "Out of range"
      case area if area > 100 && area < 200 => area.toString
      case area if area > 200 => "Out of range"
    }
  }

}
