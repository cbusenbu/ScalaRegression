package linearRegression

import breeze.linalg._


class StochasticGradientDescent(trainData:NormalizedData,
                                predictColumn:Int,
                                learnRate:Double,
                                numEpochs:Int,
                                bFunction:BasisFunction)extends linearRegression {
  private var wLimMatrix =DenseVector.fill(1){0.0}
  private var predictCol:Int = predictColumn

  def wlim:DenseVector[Double] = {
    val preTrain:DenseMatrix[Double] = trainData.nData
    var train:DenseMatrix[Double] = movePredictColToEnd(preTrain)
    var coefficients = DenseVector.fill(train.cols){0.0}
    for(epoch <- 0 to numEpochs){
      for(rowNum <- 0 to train.rows - 1){
        val currentRow:DenseVector[Double] = train(rowNum,::).t
        val yhat:Double = localPredict(currentRow,coefficients)
        val tailValue: Double = currentRow(train.cols - 1)
        val error:Double = tailValue - yhat
        coefficients(0) = coefficients(0) + learnRate * error * yhat * (1.0-yhat)
        for(columnNum <- 0 to train.cols -2){
          coefficients(columnNum+1) = coefficients(columnNum+1) + learnRate*error*yhat*(1.0-yhat)*currentRow(columnNum)
        }
      }
    }
    wLimMatrix = coefficients
    coefficients
  }
  private def movePredictColToEnd(matrix:DenseMatrix[Double]):DenseMatrix[Double] ={
    val copyMatrix:DenseMatrix[Double] = matrix
    val columnMoved:DenseMatrix[Double] = copyMatrix(::,predictCol).toDenseMatrix
    copyMatrix.delete(predictCol,Axis._1)
    val outgoingMatrix:DenseMatrix[Double] = DenseMatrix.horzcat(copyMatrix,columnMoved)
    outgoingMatrix
  }
  private def localPredict(row:DenseVector[Double],coefficients:DenseVector[Double]):Double ={
    var yhat = coefficients(0)
    for (i <- 0 to row.length -1) {
      yhat += coefficients(i+1)*row(i)
    }
    bFunction.function()(yhat)
  }
  def predict(testData:DenseMatrix[Double]):DenseVector[Double] = {
    var predictions = DenseVector.fill(testData.rows){0.0}
    if (wLimMatrix == DenseVector.fill(1){0.0}){
      throw new WLimNotIntializedException("wLim Not Intialized, recommend running SGDObject.wlim")
    }
    var coefficients = wLimMatrix
    for(rowNum <- 0 to testData.rows){
      val row:DenseVector[Double] = testData(rowNum,::).t
      val yhat:Double = localPredict(row,coefficients)
      predictions(rowNum) = yhat
    }
    predictions
  }
  def setPredictColumn(columnNum:Int):Unit ={
    predictCol = columnNum
    wLimMatrix = DenseVector.fill(1){0.0}
  }

}
