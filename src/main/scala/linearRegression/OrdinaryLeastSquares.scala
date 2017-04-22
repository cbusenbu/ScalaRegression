package linearRegression
import breeze.linalg._
//@todo change OLS to accept trainingData, and predict to accept testingData instead of just a full matrix
//@todo change predict to accept DenseMatrix instead of DenseVector
//@todo currently broken, need to redo pieces



class OrdinaryLeastSquares(trainingData:NormalizedData[Double]],
                          predictColumn:Int,
                          bFunction:BasisFunction ) extends linearRegression {

  private var wlimvector =densevector.fill(1){0.0}

  def setpredictcolumn(columnnum:int):unit ={
    this.predictcolumn = columnnum
    wlimmatrix = densematrix.fill(1){0.0}
  }

  private def predictvector: densevector[double] = {
    olsdata(::,predictcolumn)
  }
  private def lengthpv: integer = predictvector.length

  private def columnones:densematrix[double] = densematrix.zeros[double](lengthpv,1) map {case x => x+1}

  private def olsdatanotpredictcolumn:densematrix[double] = {
    val copyolsdata = olsdata
    copyolsdata.delete(predictcolumn,axis._1)
  }
  private def together:densematrix[double] = densematrix.horzcat(columnones,olsdatanotpredictcolumn)

  private def pinverse:densematrix[double] = pinv(together)

  private def preppredictdata(predictdata:densevector[double]): densevector[double] = {
    val leftvector:densevector[double] = predictdata.slice(0,predictcolumn)
    val rightvector:densevector[double] = predictdata.slice(predictcolumn+1,predictdata.length)
    val returnvector = densevector.vertcat(leftvector,rightvector)
    densevector.vertcat(densevector.ones[double](1),returnvector)
  }

  def wlim:densevector[double] = {
    wlimmatrix = pinverse*predictvector.asdensematrix.t
    wlimmatrix.todensevector
  }
  def predict(testData:densematrix[double]):densevector[double] ={
    val predictions = DenseVector.fill(testData.rows){0.0}
    if (wlimvector == densevector.fill(1){0.0}){
      throw new wlimnotintializedexception("wlim not initialized, recommend running olsobject.wlim")
    }
    for (rowNum <- 0 to testdata.rows ){
      val predictedData:DenseVector[Double] = preppredictdata(testData(rowNum,::))
      predictions(rowNum) = predicteddata.asdensematrix*wlimmatrix
    }
    predictions 
  }
  /*
  def predict(predictdata:densevector[double]):densematrix[double] = {
    
    if (wlimvector == densevector.fill(1){0.0}){
      throw new wlimnotintializedexception("wlim not intialized, recommend running .wlim")
    }
    val predicteddata:densevector[double] = preppredictdata(predictdata)
    predicteddata.asdensematrix*wlimmatrix
  }
  */
}

case class WLimNotIntializedException(message: String = "", cause: Throwable = null)
  extends Exception(message, cause)
