name := "spark-sftp"

organization := "com.springml"

scalaVersion := "2.12.14"

val sparkVersion = "3.2.1"

// spName := "springml/spark-sftp"

version := "1.1.4"

// Dependent libraries
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.springml" % "sftp.client" % "1.0.3",
  "org.mockito" % "mockito-core" % "2.0.31-beta",
  "com.databricks" %% "spark-xml" % "0.14.0",
  "org.apache.spark" %% "spark-avro" % sparkVersion % "provided"
)

dependencyOverrides += "com.jcraft" % "jsch" % "0.1.55"

// used spark components
// sparkComponents += "sql"

// Repositories
resolvers += "Spark Package Main Repo" at "https://dl.bintray.com/spark-packages/maven"

// Spark packages
// spDependencies += "com.databricks/spark-avro_2.11:3.2.0"

// Test dependencies
libraryDependencies += "org.scalatest" %% "scalatest" % sparkVersion % "test"
libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.7" % "test" exclude("org.mortbay.jetty", "servlet-api")
libraryDependencies +=  "org.apache.spark" %% "spark-hive" % sparkVersion % "test"

// spIgnoreProvided := true
// licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/springml/spark-sftp</url>
    <licenses>
      <license>
        <name>Apache License, Verision 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/springml/spark-sftp</connection>
      <developerConnection>scm:git:git@github.com:springml/spark-sftp</developerConnection>
      <url>github.com/springml/spark-sftp</url>
    </scm>
    <developers>
      <developer>
        <id>springml</id>
        <name>Springml</name>
        <url>http://www.springml.com</url>
      </developer>
    </developers>)
