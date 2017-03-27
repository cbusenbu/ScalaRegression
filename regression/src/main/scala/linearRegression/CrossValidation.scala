package linearRegression

import breeze.linalg.DenseMatrix
import scala.concurrent._

class CrossValidation {


/*
  def validate[LRModel <: linearRegression: Manifest](data:DenseMatrix[Double],columnNum:Int):LRModel ={
    var LRModels = List[Future]()

    for(cvFold <- 1 to 10){
      val(training:DenseMatrix[Double],testing:DenseMatrix[Double]) = extractCVFold(data,cvFold)
      Future{errorMeasure(training,testing)}::LRModels

    }
    val smallError = 0
    val
    for(x <-LRModels){

      x.onComplete()
    }

    def extractCVFold(data:DenseMatrix[Double],foldNum:Int):(DenseMatrix[Double],DenseMatrix[Double]) = {

    }

    def errorMeasure(trainingMatrix:DenseMatrix[Double],testingMatrix:DenseMatrix[Double]):Double = {

    }
  }
*/

}
