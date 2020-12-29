package rules

import org.scalatest.FunSuite

class Ruleof6005 extends FunSuite {
  // test1
  test("test mayUseCode in assignment"){
    assert(true == RuleOf6005.mayUseCodeInAssignment(true, true, true, true, true))
  }
}
