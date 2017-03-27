package linearRegression

import breeze.linalg._

class NormalizedData (data:DenseMatrix[Double]){
  val nData:DenseMatrix[Double] =normalize(data)

  private def normalize(data:DenseMatrix[Double]):DenseMatrix[Double] ={
    val maxArray:DenseVector[Double] = max(data(::,*)).t
    val minArray:DenseVector[Double] = min(data(::,*)).t

    val minusMinArray:DenseMatrix[Double]= data(*,::) - minArray
    val maxMinusMin:DenseVector[Double] = maxArray - minArray
    val normalized: DenseMatrix[Double] = minusMinArray(*,::)/maxMinusMin

    normalized
  }
}
