package serder

import moe.pizza.analyser.WormholeResidency.ResidencyResult
import play.api.libs.json._

/**
  * Created by Andi on 18/11/2015.
  */
object JsonSerializers {
  implicit val residencyWrites = new Writes[ResidencyResult] {
    def writes(rr: ResidencyResult) = Json.obj(
      "corporationID" -> rr.corporationID,
      "corporationName" -> rr.corporationName,
      "residencyScore" -> rr.residencyScore
    )
  }
}
