package twitter

import java.time.Instant

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */

object Filter {

  /**
   * Find tweets written by a particular user.
   *
   * @param tweets
   * a list of tweets with distinct ids, not modified by this method.
   * @param username
   * Twitter username, required to be a valid Twitter username as
   * defined by Tweet.getAuthor()'s spec.
   * @return all and only the tweets in the list whose author is username,
   *         in the same order as in the input list.
   */
  def writtenBy(tweets: List[Tweet], username: String): List[Tweet] = {
    //throw new RuntimeException("not implemented")
    tweets.filter(_.getAuthor == username)
  }

  /**
   * Find tweets that were sent during a particular timespan.
   *
   * @param tweets
   * a list of tweets with distinct ids, not modified by this method.
   * @param timespan
   * timespan
   * @return all and only the tweets in the list that were sent during the timespan,
   *         in the same order as in the input list.
   */
  def inTimespan(tweets: List[Tweet], timespan: Timespan): List[Tweet] = {
    //throw new RuntimeException("not implemented")
    tweets.filter{x:Tweet => inBetween(x.getTimestamp,timespan.getStart,timespan.getEnd)}
  }

  private def inBetween(item:Instant,start:Instant, end:Instant):Boolean = {
    start.isBefore(item) && end.isAfter(item)
  }

  /**
   * Find tweets that contain certain words.
   *
   * @param tweets
   * a list of tweets with distinct ids, not modified by this method.
   * @param words
   * a list of words to search for in the tweets.
   * A word is a nonempty sequence of nonspace characters.
   * @return all and only the tweets in the list such that the tweet text (when
   *         represented as a sequence of nonempty words bounded by space characters
   *         and the ends of the string) includes *at least one* of the words
   *         found in the words list. Word comparison is not case-sensitive,
   *         so "Obama" is the same as "obama".  The returned tweets are in the
   *         same order as in the input list.
   */
  def containing(tweets: List[Tweet], words: List[String]): List[Tweet] = {
    //throw new RuntimeException("not implemented")
    tweets.filter{x:Tweet => hasAtleastWord(x.getText,words)}
  }
  private def hasAtleastWord(item:String, words:List[String]):Boolean = {
    words.map{x:String => item.toLowerCase.contains(x.toLowerCase)}.reduce(_ || _)
  }

  /**
   * Filter tweets using a spcial user-defined function
   * @param func
   * a user defined function from pair of string to Boolean
   * the first String is the tweet and the second one is the keword
   * @param tweets
   * a list of tweets
   * @param keyWord
   * the keyword which will be the 2nd argument for func
   * @return the list of tweets that satisfy the rule in func
  * */

  def specialFilter(func:(String,String) => Boolean, tweets: List[Tweet], keyWord:String): List[Tweet] = {
    tweets.filter{x:Tweet => func(x.getText,keyWord)}
  }



}