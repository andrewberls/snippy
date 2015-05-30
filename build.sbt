name := "snippy"

organization := "com.andrewberls"

version := "0.1.0"

scalaVersion := "2.11.5"

resolvers += "clojars" at "https://clojars.org/repo"

libraryDependencies ++= Seq(
    "org.scalatest"   %% "scalatest"    % "2.2.4"   % "test,it",
    "org.scalacheck"  %% "scalacheck"   % "1.12.2"      % "test,it",
    "org.clojure"     % "clojure"       % "1.6.0",
    "com.taoensso"    % "nippy"         % "2.8.0"
)

scalacOptions ++= List("-feature","-deprecation", "-unchecked", "-Xlint")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l", "org.scalatest.tags.Slow", "-u","target/junit-xml-reports", "-oD", "-eS")
