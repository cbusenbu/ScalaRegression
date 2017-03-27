package linearRegression

import breeze.linalg.{DenseMatrix, DenseVector}


class StochasticGradientDescent(trainData:DenseMatrix[Double],
                                predictColumn:Int,
                                learnRate:Double,
                                numEpochs:Int,
                                bFunction:BasisFunction)extends linearRegression {

  private var predictCol:Int = predictColumn

  def wlim:DenseMatrix[Double] = {
    return trainData
  }
  def predict(testData:DenseMatrix[Double]):DenseMatrix[Double] = {
    return testData
  }
  def setPredictColumn(columnNum:Int):Unit ={
    predictCol = columnNum
  }

}
