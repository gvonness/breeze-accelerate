import Common.priorTo2_13

name := "breeze"

Common.commonSettings

libraryDependencies ++= Seq(
  "dev.ludovic.netlib" % "blas" % "5-SNAPSHOT",
  "dev.ludovic.netlib" % "lapack" % "5-SNAPSHOT",
  "dev.ludovic.netlib" % "arpack" % "5-SNAPSHOT",
  "net.sourceforge.f2j" % "arpack_combined_all" % "0.1",
  "net.sf.opencsv" % "opencsv" % "2.3",
  "com.github.wendykierp" % "JTransforms" % "3.1",
  "org.apache.commons" % "commons-math3" % "3.2",
//  ("com.chuusai" %% "shapeless" % "2.3.3").withDottyCompat(scalaVersion.value),
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.17.1" % "test",
  "org.apache.logging.log4j" % "log4j-core" % "2.17.1" % "test",
  "org.apache.logging.log4j" % "log4j-api" % "2.17.1" % "test",
  ("org.scala-lang.modules" %% "scala-collection-compat" % "2.7.0")
)

libraryDependencies += {
  if (priorTo2_13(scalaVersion.value)) {
    "org.typelevel" %% "spire" % "0.17.0"
  } else {
    "org.typelevel" %% "spire" % "0.18.0"
  }
}

// see https://github.com/typesafehub/scalalogging/issues/23
Test / testOptions += Tests.Setup(classLoader =>
  try {
    classLoader
      .loadClass("org.slf4j.LoggerFactory")
      .getMethod("getLogger", classLoader.loadClass("java.lang.String"))
      .invoke(null, "ROOT")
  } catch {
    case _: Exception =>
})

Test / fork := true

javaOptions := Seq("-Xmx4g", "-Xss10m")
