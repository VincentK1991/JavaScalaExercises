package twitter

import java.time.Instant

import org.scalatest.FunSuite

class SocialNetworkTest extends FunSuite {
  private val d1: Instant = Instant.parse("2016-02-17T10:00:00Z")
  private val d2: Instant = Instant.parse("2016-02-18T11:00:00Z")
  private val d3: Instant = Instant.parse("2016-02-19T11:00:00Z")
  private val d4: Instant = Instant.parse("2016-02-20T11:00:00Z")
  private val d5: Instant = Instant.parse("2017-02-21T11:00:00Z")
  private val d6: Instant = Instant.parse("2016-02-22T11:00:00Z")

  private val tweet1: Tweet = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much", d1)
  private val tweet2: Tweet = new Tweet(2, "Tate", "#hype hello world", d2)
  private val tweet3: Tweet = new Tweet(3, "Time", "#hype hello moon", d3)
  private val tweet4: Tweet = new Tweet(4, "Tom", "#hype plastic love", d4)
  private val tweet5: Tweet = new Tweet(5, "Ben", "#hello hello world", d5)
  private val tweet6: Tweet = new Tweet(6, "Bob", "#hello foo bar", d6)


  test("test twitter hashtag influencer finder") {
    val tweets = List(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6)
    val result = SocialNetwork.findInfluencerFromTag(tweets)
    //println(result)
    assert(result == Map("Tate" -> Set("Time", "Tom"), "Ben" -> Set("Bob")),
           "expected the map from influencer to the influenced")
  }
  test("test gusss following graph empty") {
    val followsGraph = SocialNetwork.guessFollowsGraph(List.empty[Tweet])
    assert(followsGraph.isEmpty, "expected empty graph")
  }

  test("test influencers empty") {
    val influencerGraph: Map[String, Set[String]] = Map()
    val influencers = SocialNetwork.influencers(influencerGraph)
    assert(influencers.isEmpty, "expected empty list")
  }

}
