import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure, Try}

@main
def main(): Unit = {
  // Define a list of tasks to execute in parallel
  val tasks = List(
    Future {
      Thread.sleep(1000) // Simulating a task that takes some time
      "Task 1 result"
    },
    Future {
      Thread.sleep(2000) // Simulating a task that takes some time
      throw new Exception("Task 2 failed")
    },
    Future {
      Thread.sleep(1500) // Simulating a task that takes some time
      "Task 3 result"
    }
  )

  // Execute all tasks in parallel and collect the results
  val results: Future[List[Try[String]]] = Future.sequence(tasks.map(_.transform(Success(_))))

  // Process the results
  results.onComplete {
    case Success(resultList) =>
      resultList.foreach {
        case Success(value) => println("Debug " + value)
        case Failure(exception) => println("Debug " + exception.getMessage)
      }
    case Failure(exception) =>
      println("Error occurred: " + exception.getMessage)
  }
  Thread.sleep(2000)
}