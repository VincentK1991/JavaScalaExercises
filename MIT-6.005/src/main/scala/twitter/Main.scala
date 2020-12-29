/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter

//import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.time.Instant

import scala.jdk.CollectionConverters._

/**
 * This is the main program.
 *
 * You may change this class if you wish, but you don't have to.
 */
object Main {
  /**
   * URL of a server that produces a list of tweets sampled from Twitter
   * within the last hour. This server may take up to a minute to respond, if
   * it has to refresh its cached sample of tweets.
   */
    val SAMPLE_SERVER:URL = makeURLAssertWellFormatted("http://courses.csail.mit.edu/6.005/ps1_tweets/tweetPoll.py")

  private def makeURLAssertWellFormatted(urlString: String) = try new URL(urlString)
  catch {
    case murle: MalformedURLException =>
      throw new AssertionError(murle)
  }

  /**
   * Main method of the program. Fetches a sample of tweets and prints some
   * facts about it.
   *
   * @param args command-line arguments (not used)
   */
  def main(args: Array[String]):Unit = {
//    try {
//      assert(false)
//      throw new Error("Always run main and tests with assertions enabled")
//    } catch {
//      case ae: AssertionError =>
//    }
//    val tweets = null
    val tweets = TweetReader.readTweetsFromWeb(SAMPLE_SERVER).asScala.toList
//    catch {
//      case ioe: IOException =>
//        throw new RuntimeException(ioe)
//    }
    // display some characteristics about the tweets

    println("fetched " + tweets.size + " tweets")

    val span = Extract.getTimespan(tweets)
    println("ranging from " + span.getStart + " to " + span.getEnd)

    val mentionedUsers = Extract.getMentionedUsers(tweets)
    println("covers " + mentionedUsers.size + " Twitter users")

    // infer the follows graph
    val followsGraph = SocialNetwork.guessFollowsGraph(tweets)
    println("follows graph has " + followsGraph.size + " nodes")


    // print the top-N influencers
    val count = 10
    val influencers:List[String] = SocialNetwork.influencers(followsGraph)
    influencers.take(count min influencers.size).foreach{
      println(_)
    }
    println(" ")
    println("==================")
    println(" ")
    val d1: Instant = Instant.parse("2016-02-17T10:00:00Z")
    val d2: Instant = Instant.parse("2016-02-18T11:00:00Z")
    val d3: Instant = Instant.parse("2016-02-19T11:00:00Z")
    val d4: Instant = Instant.parse("2016-02-20T11:00:00Z")
    val d5: Instant = Instant.parse("2016-02-21T11:00:00Z")
    val d6: Instant = Instant.parse("2016-02-22T11:00:00Z")

    val tweet1: Tweet = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much", d1)
    val tweet2: Tweet = new Tweet(2, "Tate", "#hype hello world", d2)
    val tweet3: Tweet = new Tweet(3, "Time", "#hype hello moon", d3)
    val tweet4: Tweet = new Tweet(4, "Tom", "#hype plastic love", d4)
    val tweet5: Tweet = new Tweet(5, "Ben", "#hello hello world", d5)
    val tweet6: Tweet = new Tweet(6, "Bob", "#hello foo bar", d6)
    val tweets2 = List(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6)
    val result = SocialNetwork.findInfluencerFromTag(tweets2)
    println(result)
  }
}