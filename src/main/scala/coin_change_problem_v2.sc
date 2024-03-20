import scala.collection.immutable.TreeSet

val numbers = List(5, 7, 1, 1, 2, 3, 22)


def allSums(numbers: List[Int]): List[Int] = {
  def helper(numbers: List[Int], accum: Int = 0): List[Int]  = {
    if (numbers.isEmpty) {
      List(accum)
    } else {
      val caseTail = helper(numbers.tail,accum)
      val caseWithHead = helper(numbers.tail, accum + numbers.head )
      caseTail ++ caseWithHead
    }
  }
  helper(numbers)
}

val unique_sums = TreeSet(allSums(numbers) *)

val jumps = unique_sums.zip(unique_sums.tail).find {
  case (prev, current) => current != prev + 1
}

val result: Int = jumps match {
  case Some(first, _ ) => first + 1
  case None => unique_sums.last + 1
}


println(s"The minimum change is $result")

