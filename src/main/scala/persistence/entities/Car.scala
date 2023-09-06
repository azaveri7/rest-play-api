package persistence.entities

import play.api.libs.json.{Format, Json}

case class Car(id: Long, brand: String, model: String, cc: Int) {
  def toDTO(): CarDTO = CarDTO(Some(id), brand, model, cc)
}

object Car {
  def fromDTO(carDTO: CarDTO): Car = Car(carDTO.id.getOrElse(0l), carDTO.brand, carDTO.model, carDTO.cc)
}

case class CarDTO(id: Option[Long], brand: String, model: String, cc: Int) {
  def toCar(): Car = Car(id.getOrElse(0l), brand, model, cc)
}

object CarDTO {
  implicit val format: Format[CarDTO] = Json.format[CarDTO]
  def fromCar(car: Car): CarDTO = CarDTO(Some(car.id), car.brand, car.model, car.cc)
}
