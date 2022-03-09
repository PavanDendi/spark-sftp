name := "spark-sftp"

organization := "com.springml"

scalaVersion := "2.12.14"

val sparkVersion = "3.2.1"

// spName := "springml/spark-sftp"

version := "2.0.0"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository/"
resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"

// Dependent libraries
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.mockito" % "mockito-core" % "2.0.31-beta",
  "com.databricks" %% "spark-xml" % "0.14.0",
  "org.apache.spark" %% "spark-avro" % sparkVersion % "provided"
)

libraryDependencies += "com.springml" % "sftp.client" % "2.0.0" excludeAll(
    ExclusionRule(organization = "com.jcraft")
  )


// dependencyOverrides += "com.github.mwiede" % "jsch" % "0.2.0"

// used spark components
// sparkComponents += "sql"

// Repositories
// resolvers += "Spark Package Main Repo" at "https://dl.bintray.com/spark-packages/maven"

// Spark packages
// spDependencies += "com.databricks/spark-avro_2.11:3.2.0"

// Test dependencies
// libraryDependencies += "org.scalatest" %% "scalatest" % sparkVersion % "test"
// libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.7" % "test" exclude("org.mortbay.jetty", "servlet-api")
// libraryDependencies +=  "org.apache.spark" %% "spark-hive" % sparkVersion % "test"

// spIgnoreProvided := true
// licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

// credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (version.value.endsWith("SNAPSHOT"))
//     Some("snapshots" at nexus + "content/repositories/snapshots")
//   else
//     Some("releases"  at nexus + "service/local/staging/deploy/maven2")
// }

// pomExtra := (
//   <url>https://github.com/springml/spark-sftp</url>
//     <licenses>
//       <license>
//         <name>Apache License, Verision 2.0</name>
//         <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
//         <distribution>repo</distribution>
//       </license>
//     </licenses>
//     <scm>
//       <connection>scm:git:github.com/springml/spark-sftp</connection>
//       <developerConnection>scm:git:git@github.com:springml/spark-sftp</developerConnection>
//       <url>github.com/springml/spark-sftp</url>
//     </scm>
//     <developers>
//       <developer>
//         <id>springml</id>
//         <name>Springml</name>
//         <url>http://www.springml.com</url>
//       </developer>
//     </developers>)

// assemblyMergeStrategy := {
//   case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
//   case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
//   case "application.conf"                            => MergeStrategy.concat
//   case "unwanted.txt"                                => MergeStrategy.discard
//   case x =>
//     val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
//     oldStrategy(x)
// }

assemblyMergeStrategy := {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case PathList("META-INF", xs @ _*) =>
    (xs map {_.toLowerCase}) match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
        MergeStrategy.discard
      case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
        MergeStrategy.discard
      case "plexus" :: xs =>
        MergeStrategy.discard
      case "services" :: xs =>
        MergeStrategy.filterDistinctLines
      case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
        MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first // Changed deduplicate to first
    }
  case PathList(_*) => MergeStrategy.first // added this line
}