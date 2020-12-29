package twitter

import java.time.Instant
import org.scalatest.FunSuite
class ExtractTest extends FunSuite {
    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
  private val d1:Instant = Instant.parse("2016-02-17T10:00:00Z")
  private val d2:Instant = Instant.parse("2016-02-17T11:00:00Z")

  private val tweet1: Tweet = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much", d1)
  private val tweet2: Tweet = new Tweet(2, "bbitdiddle", "rivest talk in 30minutes # hype", d2)

  //val extract1 = new Extract() // create object so we can use static method
  val listTweet:List[Tweet] = List(tweet1,tweet2)

  test("test get time span two tweets"){
    val timespan: Timespan = Extract.getTimespan(listTweet)

    assert(d1 == timespan.getStart,"expected start")
    assert(d2 == timespan.getEnd,"expected end")
  }

  test("test get mentioned users no mention"){
    val mentionedUsers:Set[String] = Extract.getMentionedUsers(listTweet)
    assert(mentionedUsers.isEmpty,"expected empty set")
  }
}
