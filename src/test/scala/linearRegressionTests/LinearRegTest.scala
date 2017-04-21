package linearRegressionTests

import org.scalatest.FunSuite
import breeze.linalg._
import linearRegression.{OrdinaryLeastSquares, WLimNotIntializedException}

class LinearRegTest extends FunSuite {
  val testMatrix = DenseMatrix((1.3,2.2),(2.1,5.8),(3.7,10.2),(4.2,11.8))
  val regressionTest = new OrdinaryLeastSquares(testMatrix,1)
  val bias:Double = -1.5225601452564668
  val w_1: Double = 3.1938266000907847
  val regressionWLim = DenseMatrix((bias),(w_1))

  test("predict without w_lim initialized"){
    assertThrows[WLimNotIntializedException]{
      regressionTest.predict(DenseVector(1.0,2.0))
    }
  }
  test("OLS calc w_lim"){
    assert(regressionTest.wlim == regressionWLim)

  }
  test("OLS change column"){
    //values tested against wolframAlpha
    val biasNew: Double = 0.5007399577167031
    val w_1New: Double = 0.3099013389711064
    val regressionWLimNew = DenseMatrix((biasNew),(w_1New))
    regressionTest.setPredictColumn(0)
    assert(regressionTest.wlim == regressionWLimNew)
  }
  test("predict datapoint"){
    assert(regressionTest.predict(DenseVector(1.0,2.0)) == DenseMatrix((1.1205426356589159)))
  }
}
