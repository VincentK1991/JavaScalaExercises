package twitter

import java.time.Instant

/**
 * Extract consists of methods that extract information from a list of tweets.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */

object Extract {

  /**
   * Get the time period spanned by tweets.
   *
   * @param tweets
   * list of tweets with distinct ids, not modified by this method.
   * @return a minimum-length time interval that contains the timestamp of
   *         every tweet in the list.
   */
  def getTimespan(tweets: List[Tweet]): Timespan = {
    //throw new RuntimeException("not implemented")
    val listTimestamp:List[Instant] = tweets.map(_.getTimestamp())
    val timeFirst = listTimestamp.foldLeft(Instant.parse("9999-01-01T10:00:00Z")){comeBefore}
    val timeLast = listTimestamp.foldLeft(Instant.parse("0001-01-01T10:00:00Z")){comeAfter}

    new Timespan(timeFirst, timeLast)
  }

  private def comeBefore(s1:Instant, s2:Instant):Instant = {
    if (s1.isBefore(s2)){
      s1
    } else{
      s2
    }
  }
  private def comeAfter(s1:Instant, s2:Instant):Instant = {
    if (s1.isBefore(s2)){
      s2
    } else{
      s1
    }
  }

  /**
   * Get usernames mentioned in a list of tweets.
   *
   * @param tweets
   * list of tweets with distinct ids, not modified by this method.
   * @return the set of usernames who are mentioned in the text of the tweets.
   *         A username-mention is "@" followed by a Twitter username (as
   *         defined by Tweet.getAuthor()'s spec).
   *         The username-mention cannot be immediately preceded or followed by any
   *         character valid in a Twitter username.
   *         For this reason, an email address like bitdiddle@mit.edu does NOT
   *         contain a mention of the username mit.
   *         Twitter usernames are case-insensitive, and the returned set may
   *         include a username at most once.
   */
  def getMentionedUsers(tweets: List[Tweet]): Set[String] = {
    //throw new RuntimeException("not implemented")
    val listText:List[String] = tweets.map(_.getText())
    val pattern1 = "@[a-zA-Z]*(?=\\s|$)".r
    listText.flatMap(pattern1.findAllIn(_).toList).map(_.toLowerCase).map(_.tail).toSet
  }

  def orderTweets(tweets:List[Tweet]):List[Tweet] = ???

}
