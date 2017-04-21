package linearRegression
import breeze.linalg._
//@todo change OLS to accept trainingData, and predict to accept testingData instead of just a full matrix
//@todo change predict to accept DenseMatrix instead of DenseVector
//@todo currently broken, need to redo pieces



class OrdinaryLeastSquares(val olsData:DenseMatrix[Double],var predictColumn:Int) extends linearRegression {
  var wLimMatrix:DenseMatrix[Double] = DenseMatrix.zeros[Double](1,1)

  def setPredictColumn(columnNum:Int):Unit ={
    this.predictColumn = columnNum
    wLimMatrix = DenseMatrix.zeros[Double](1,1)
  }

  private def predictVector: DenseVector[Double] = {
    olsData(::,predictColumn)
  }
  private def lengthPV: Integer = predictVector.length

  private def columnOnes:DenseMatrix[Double] = DenseMatrix.zeros[Double](lengthPV,1) map {case x => x+1}

  private def olsDataNotPredictColumn:DenseMatrix[Double] = {
    val copyOLSData = olsData
    copyOLSData.delete(predictColumn,Axis._1)
  }
  private def together:DenseMatrix[Double] = DenseMatrix.horzcat(columnOnes,olsDataNotPredictColumn)

  private def pInverse:DenseMatrix[Double] = pinv(together)

  private def prepPredictData(predictData:DenseVector[Double]): DenseVector[Double] = {
    val leftVector:DenseVector[Double] = predictData.slice(0,predictColumn)
    val rightVector:DenseVector[Double] = predictData.slice(predictColumn+1,predictData.length)
    val returnVector = DenseVector.vertcat(leftVector,rightVector)
    DenseVector.vertcat(DenseVector.ones[Double](1),returnVector)
  }

  def wlim:DenseVector[Double] = {
    wLimMatrix = pInverse*predictVector.asDenseMatrix.t
    wLimMatrix.toDenseVector
  }
  def predict(testData:DenseMatrix[Double]):DenseVector[Double] ={
    return testData.toDenseVector
  }
  /*
  def predict(predictData:DenseVector[Double]):DenseMatrix[Double] = {
    if (wLimMatrix == DenseMatrix.zeros[Double](1,1)){
      throw new WLimNotIntializedException("wLim Not Intialized, recommend running OLSObject.wlim")
    }
    val predictedData:DenseVector[Double] = prepPredictData(predictData)
    predictedData.asDenseMatrix*wLimMatrix
  }
  */
}

case class WLimNotIntializedException(message: String = "", cause: Throwable = null)
  extends Exception(message, cause)