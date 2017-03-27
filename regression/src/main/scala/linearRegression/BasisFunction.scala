package linearRegression

import scala.math.exp

/**
  * Created by charlesbusenburg on 3/26/17.
  */
class BasisFunction(funcName:String){

  def function() = (x:Double)=>{
    funcName match {
      case "linear" => x
      case "logrithmic" => 1.0/(1.0+exp(-x))
    }
  }

}
