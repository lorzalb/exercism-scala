import org.scalatest.{Matchers, FlatSpec}

class TrinaryTest extends FlatSpec with Matchers {
  it should "empty string should error" in {
    intercept[IllegalArgumentException] {
      Trinary.trinaryToInt("")
    }
  }

  it should "invalid trinary should error" in {
    intercept[IllegalArgumentException] {
      Trinary.trinaryToInt("1239")
    }

    intercept[IllegalArgumentException] {
      Trinary.trinaryToInt("3")
    }

    intercept[IllegalArgumentException] {
      Trinary.trinaryToInt("FF")
    }
  }

  it should "handle zeros" in {
    Trinary.trinaryToInt("0") should equal(0)
    Trinary.trinaryToInt("00000000") should equal(0)
  }

  it should "handle single digit" in {
    Trinary.trinaryToInt("2") should equal(2)
  }

  it should "handle multiple digits" in {
    Trinary.trinaryToInt("10") should equal(3)
    Trinary.trinaryToInt("12") should equal(5)
    Trinary.trinaryToInt("222") should equal(26)
    Trinary.trinaryToInt("1122000120") should equal(32091)
  }

  it should "handle Int 0" in {
    Trinary.intToTrinary(0) should equal("0")
  }

  it should "handle Int to multi digit trinary" in {
    Trinary.intToTrinary(4) should equal("11")
    Trinary.intToTrinary(26) should equal("222")
    Trinary.intToTrinary(32091) should equal("1122000120")
  }

  it should "handle Int to trinary with trailing zeros" in {
    Trinary.intToTrinary(3) should equal("10")
  }
}
