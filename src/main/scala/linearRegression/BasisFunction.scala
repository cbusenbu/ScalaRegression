package linearRegression

import scala.math.exp

class BasisFunction(funcName:String){

  def function() = (x:Double)=>{
    funcName match {
      case "linear" => x
      case "logrithmic" => 1.0/(1.0+exp(-x))
    }
  }

}
