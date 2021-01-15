import play.sbt.PlayRunHook
import sbt._

import java.io.File
import com.typesafe.config.{ Config, ConfigFactory }

import scala.sys.process.Process

/**
  * Frontend build play run hook.
  * https://www.playframework.com/documentation/2.8.x/SBTCookbook
  */
object FrontendRunHook {
  def apply(base: File): PlayRunHook = {
    object UIBuildHook extends PlayRunHook {
      var configFile = base + "/conf/application.conf"
      val config: Config = ConfigFactory.parseFile(new File(configFile))

      val delay: Int = config.getInt("delayStartUI")

      var process: Option[Process] = None
      var processTrigger: Option[Process] = None

      /**
        * Change the commands in `FrontendCommands.scala` if you want to use Yarn.
        */
      var install: String = FrontendCommands.dependencyInstall
      var run: String = FrontendCommands.serve
      var trigger: String = FrontendCommands.trigger

      // Windows requires npm commands prefixed with cmd /c
      if (System.getProperty("os.name").toLowerCase().contains("win")){
        install = "cmd /c" + install
        run = "cmd /c" + run
        trigger = "cmd /c" + trigger
      }

      /**
        * Executed before play run start.
        */
      override def beforeStarted(): Unit = {
        if (!(base / "ui" / "node_modules").exists()) Process(install, base / "ui").!
      }

      /**
        * Executed after play run start.
        * Run npm start
        */
      override def afterStarted(): Unit = {
        println("Loading UI app, please wait")
        process = Option(
          Process(run, base / "ui").run
        )

        // Wait until UI app is ready
        Thread.sleep(delay)

        processTrigger = Option(
          Process(trigger, base / "ui").run
        )
      }

      /**
        * Executed after play run stop.
        * Cleanup frontend execution processes.
        */
      override def afterStopped(): Unit = {
        process.foreach(_.destroy())
        process = None

        processTrigger.foreach(_.destroy())
        processTrigger = None
      }

    }

    UIBuildHook
  }
}
