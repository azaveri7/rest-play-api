package repositories

import persistence.Persistence
import persistence.entities.Car
import repositories.errors.RepositoryError

import scala.collection.mutable.ListBuffer
import scala.util.Try

class CarRepository(val db: Persistence[Car, Long, ListBuffer]) {
  def findById(id: Long): Either[RepositoryError, Option[Car]] = handleErrors(db.find(id))
  def save(car: Car): Either[RepositoryError, Car] = handleErrors(db.insert(car))

  private def handleErrors[A](f: => A) =
    Try(f).fold(e => Left(RepositoryError(e.getMessage)), v => Right(v))
}
