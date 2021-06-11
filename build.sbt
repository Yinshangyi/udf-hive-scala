name := "HiveUDFScala"

version := "0.1"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
    "org.apache.hive" % "hive-exec" % "3.1.2",
    "org.scalatest" %% "scalatest" % "3.2.2" % "test"
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
