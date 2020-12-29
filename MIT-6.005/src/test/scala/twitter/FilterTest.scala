package twitter

import java.time.Instant
import org.scalatest.FunSuite



class FilterTest extends FunSuite {
  private final val d1:Instant = Instant.parse("2016-02-17T10:00:00Z")
  private final val d2:Instant = Instant.parse("2016-02-17T10:00:00Z")

  private val tweet1: Tweet = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much", d1)
  private val tweet2: Tweet = new Tweet(2, "bbitdiddle", "rivest talk in 30minutes # hype", d2)

  //val filter1 = new Filter()

  val listTweet:List[Tweet] = List(tweet1,tweet2)

  test("test written by multiple tweets single result"){
    val writtenBy1:List[Tweet] = Filter.writtenBy(listTweet,"alyssa")

    assert(1 == writtenBy1.size,"expected singleton list")
    assert( writtenBy1.contains(tweet1),"expected list to contain tweet")
  }

  test("test in time span multiple tweet multiple results"){
    val testStart:Instant = Instant.parse("2015-02-17T09:00:00Z")
    val testEnd:Instant = Instant.parse("2016-08-17T09:00:00Z")

    val inTimespan:List[Tweet] = Filter.inTimespan(listTweet, new Timespan(testStart, testEnd))

    assert(inTimespan.nonEmpty,"expected non-empty list")
    assert(listContainElement(listTweet,inTimespan),"expected list to contain tweets")
    assert(inTimespan.indexOf(tweet1) == 0,"expected same order")
  }

  test("test containing"){
    val containing:List[Tweet] = Filter.containing(listTweet,List("talk"))
    assert(containing.nonEmpty,"expected non empty list")
    assert(listContainElement(listTweet,containing),"expect list to contain tweets")
    assert(containing.indexOf(tweet1) == 0, "expect same order")
  }

  def listContainElement(itemList:List[Tweet],span:List[Tweet]):Boolean = {
    // return true if at least one tweet in the itemList is in the time span
    (for (item <- itemList) yield span.contains(item)).reduce(_ || _)
  }
}
