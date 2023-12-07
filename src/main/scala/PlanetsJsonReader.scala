import spray.json._


case class Planet(name: String, satellites: List[String], mass: String)
case class Planets(gasGiants: List[Planet], rockyPlanets: List[Planet])

object PlanetsJsonProtocol extends DefaultJsonProtocol {
  implicit val planetFormat = jsonFormat(Planet, "name", "satellites", "mass")
  implicit val planetsFormat = jsonFormat(Planets, "gasGiants", "rockyPlanets")

}

import PlanetsJsonProtocol.planetsFormat

object PlanetsJsonReader extends App {
//  val jsonFile = scala.io.Source.fromResource("data/planets.json").mkString
  val rawText = scala.io.Source.fromResource("data/planets.json").mkString
  val jsonAst = rawText.parseJson
  val planets = jsonAst.convertTo[Planets]

  println(planets.gasGiants(0).name)
  planets.gasGiants(0).satellites.foreach(println)

}
