def maxsubstring(str: String) :Int = {
  def helper(idx: Int = 0, accum: String = ""): Int ={
    if (idx >= str.length){
      accum.length
    }else {
      if (!accum.contains(str(idx))){
        val c = helper(idx+1, accum + str(idx) )
        val n = helper(idx+1, "")
        c.max(n)
      }else {
        accum.length.max(helper(idx+1, ""))
      }
    }
  }
  helper()
}

maxsubstring("abbcbeffkktyuikgbnii")