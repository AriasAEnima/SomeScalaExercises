import scala.collection.immutable.TreeSet

val numbers = List(5, 7, 1, 1, 2, 3, 22)

val possible_changes = numbers.sorted.foldLeft(List(List()))(
  (ac: List[List[Int]], nx: Int) => {
    ac ++ ac.map((subl: List[Int]) => List(nx) ++ subl)
  })

val posible_sums: List[Int] = possible_changes.foldLeft(List())({
  (ac: List[Int], nx: List[Int]) => {
    ac :+ nx.sum
  }
})

val unique_sums = TreeSet(posible_sums *)

val jumps = unique_sums.zip(unique_sums.tail).find {
    case (prev, current) => current != prev + 1
}

val result: Int = jumps match {
  case Some(first, _ ) => first + 1
  case None => unique_sums.last + 1
}

println(s"The minimum change is $result")





