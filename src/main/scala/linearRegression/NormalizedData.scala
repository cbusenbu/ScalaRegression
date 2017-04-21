package linearRegression

import breeze.linalg._
import scala.util.Random.shuffle


case class NormalizedData(data:DenseMatrix[Double],minArray:DenseVector[Double],maxArray:DenseVector[Double]){
  def nData:DenseMatrix[Double] = normalize(data)

  private def normalize(data:DenseMatrix[Double]):DenseMatrix[Double] ={

    val minusMinArray:DenseMatrix[Double]= data(*,::) - minArray
    val maxMinusMin:DenseVector[Double] = maxArray - minArray
    val normalized: DenseMatrix[Double] = minusMinArray(*,::)/maxMinusMin

    normalized
  }
  def reverseNormalize(vectorToReverse:DenseVector[Double]): DenseVector[Double] ={
    val maxMinusMin = maxArray - minArray
    val multiplyByMaxMinusMin = (vectorToReverse.toDenseMatrix*maxMinusMin) + minArray
    multiplyByMaxMinusMin.toDenseVector
  }

  def folds(n:Int):List[NormalizedData]={
    val foldLength:Int = Math.floor(data.rows/n).toInt
    val foldsList:IndexedSeq[DenseMatrix[Double]] = for(x:Double <- 0 to n-1)yield DenseMatrix.zeros[Double](0,data.cols)
    val postFoldsList:List[DenseMatrix[Double]] = foldsList.toList
    val initialList:IndexedSeq[Int] = for(x:Int <- 0 to data.rows -1)yield x
    val shuffled = shuffle(initialList)
    for (x:Int <- shuffled){
      val foldNumber:Int = Math.floor(x/foldLength).toInt
      val foldIndex:Int  = (x % foldLength)
      val newMatrix:DenseMatrix[Double] = DenseMatrix.horzcat(foldsList(foldNumber),data(x,::))
      postFoldsList(foldNumber) = newMatrix
    }

    for(fold:DenseMatrix[Double]<-foldsList){

    }

  }

}

object NormalizedData{

  def apply(data:DenseMatrix[Double]) = new NormalizedData(data,min(data(::,*)).t,max(data(::,*)).t)

  def apply(data:DenseMatrix[Double],minArray:DenseVector[Double],maxArray:DenseVector[Double]) = {
    new NormalizedData(data,minArray,maxArray)
  }

}
