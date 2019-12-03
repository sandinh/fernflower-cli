organization := "com.sandinh"
name := "fernflower-cli"
version := "0.1"
scalaVersion := "2.13.1"

resolvers += Resolver.defaultLocal
libraryDependencies += "com.sandinh" % "fernflower" % "1.0-4ca282df"

exportJars := true
mainClass := Some("Fern")
