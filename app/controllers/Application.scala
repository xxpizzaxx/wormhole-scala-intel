package controllers

import moe.pizza.analyser.{WormholeResidency, DatabaseOps}
import play.api._
import play.api.mvc._
import slick.jdbc.JdbcBackend._
import moe.pizza.sdeapi._
import serder.JsonSerializers.residencyWrites
import play.api.libs.json._

class Application extends Controller {
  lazy val db = new DatabaseOps(Database.forURL("jdbc:mysql://localhost:3306/sde", "sde", "sde", driver = "com.mysql.jdbc.Driver"))
  lazy val residency = new WormholeResidency(db)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def analyse(system: String) = Action {
    val systemid = db.getSystemID(system).sync()
    Ok(Json.toJson(residency.analyseAndGenerateResults(systemid.toLong)))
  }
}
