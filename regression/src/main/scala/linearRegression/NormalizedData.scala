package linearRegression

import breeze.linalg.DenseMatrix

class NormalizedData (data:DenseMatrix[Double]){
  val nData =normalize(data)

  private def normalize(data:DenseMatrix[Double]):DenseMatrix[Double] ={
    return data
  }
}
