/**
  * Frontend build commands.
  */
object FrontendCommands {
  val dependencyInstall: String = "npm install --save-dev @angular-devkit/build-angular"
  val test: String = "npm run test:ci"
  val serve: String = "npm run start"
  val build: String = "npm run build:prod"
  val trigger: String = "curl http://localhost:9000/welcome"
}