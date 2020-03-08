name := "picocli-scala-example"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "info.picocli" % "picocli" % "4.2.0",
  "info.picocli" % "picocli-codegen" % "4.2.0" % "provided"
)

lazy val processAnnotations = taskKey[Unit]("Process annotations")

processAnnotations := {
  val log = streams.value.log

  log.info("Processing annotations ...")

  val classpath = ((products in Compile).value ++ ((dependencyClasspath in Compile).value.files)) mkString ":"
  val destinationDirectory = (classDirectory in Compile).value
  val processor = "picocli.codegen.aot.graalvm.processor.NativeImageConfigGeneratorProcessor"
  val classesToProcess = Seq("com.github.takezoe.CheckSum") mkString " "

  val command = s"javac -cp $classpath -proc:only -processor $processor -XprintRounds -d $destinationDirectory $classesToProcess"

  failIfNonZeroExitStatus(command, "Failed to process annotations.", log)

  log.info("Done processing annotations.")
}

def failIfNonZeroExitStatus(command: String, message: => String, log: Logger) = {
  import scala.sys.process._
  val result = command !

  if (result != 0) {
    log.error(message)
    sys.error("Failed running command: " + command)
  }
}

assembly := (assembly dependsOn (processAnnotations in Compile)).value
