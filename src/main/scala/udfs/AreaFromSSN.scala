package udfs

import org.apache.hadoop.hive.ql.udf.generic.GenericUDF
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector

object AreaFromSSN extends GenericUDF {

  override def initialize(arguments: Array[ObjectInspector]): ObjectInspector = ???

  override def evaluate(arguments: Array[GenericUDF.DeferredObject]): AnyRef = ???

  override def getDisplayString(children: Array[String]): String = ???
}
