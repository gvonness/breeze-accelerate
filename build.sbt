//enablePlugins(GitVersioning)

Common.commonSettings

name := "breeze-parent"

lazy val root = project
  .in(file("."))
  .aggregate(math, natives, viz, macros)
  .dependsOn(math, viz)

lazy val macros = project.in(file("macros"))

lazy val math = project.in(file("math")).dependsOn(macros)

lazy val natives = project.in(file("natives")).dependsOn(math)

lazy val viz = project.in(file("viz")).dependsOn(math)

lazy val benchmark = project.in(file("benchmark")).dependsOn(math, natives)

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / resolvers += Resolver.mavenLocal