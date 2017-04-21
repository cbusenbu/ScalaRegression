package linearRegression

import breeze.linalg.{DenseMatrix, DenseVector}

trait linearRegression {
  def setPredictColumn(columnNum:Int)
  def predict(testData:DenseMatrix[Double]):DenseVector[Double]
  def wlim:DenseVector[Double]
}
