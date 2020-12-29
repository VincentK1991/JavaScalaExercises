package twitter

object SocialNetwork {
  /**
   * Guess who might follow whom, from evidence fou nd in tweets.
   *
   * @param tweets
   * a list of tweets providing the evidence, not modified by this
   * method.
   * @return a social network (as defined above) in which Ernie follows Bert
   *         if and only if there is evidence for it in the given list of
   *         tweets.
   *         One kind of evidence that Ernie follows Bert is if Ernie
   * -mentions Bert in a tweet. This must be implemented. Other kinds
   *            of evidence may be used at the implementor's discretion.
   *            All the Twitter usernames in the returned social network must be
   *            either authors or @-mentions in the list of tweets.
   */
  def guessFollowsGraph(tweets: List[Tweet]): Map[String, Set[String]] = {
    //throw new RuntimeException("not implemented")
    val tempCollection = for {
      mentioned <- tweets
      mentioner = Filter.containing(tweets,List(mentioned.getAuthor))//Extract.getMentionedUsers(List(tweet))
      follower = mentioner.map(_.getAuthor).toSet
    } yield mentioned.getAuthor -> follower

    tempCollection.toMap
  }

  /**
   * Find the people in a social network who have the greatest influence, in
   * the sense that they have the most followers.
   *
   * @param followsGraph
   * a social network (as defined above)
   * @return a list of all distinct Twitter usernames in followsGraph, in
   *         descending order of follower count.
   */
  def influencers(followsGraph: Map[String, Set[String]]): List[String] = {
    //throw new RuntimeException("not implemented")
    followsGraph.toList.sortBy(_._2.size).map(_._1).reverse
  }

  def findHashTag(tweets: List[Tweet]):Set[String] = {
    val listText:List[String] = tweets.map(_.getText())
    val patternTag = "#[a-zA-Z]*(?=\\s|$)".r
    listText.flatMap(patternTag.findAllIn(_).toList).map(_.toLowerCase).toSet
  }

  def findInfluencerFromTag(tweets:List[Tweet]):Map[String, Set[String]] = {
    val hashTagSet = findHashTag(tweets)
    val commonTagTweetSet = for {
      hashTag <- hashTagSet
      groupTweets = Filter.containing(tweets,List(hashTag))//.map(_.getAuthor)
      timeSpanTweets = Extract.getTimespan(groupTweets)
      timeStart = timeSpanTweets.getStart
      firstTweet = groupTweets.filter(_.getTimestamp == timeStart).head
      subsequentTweet = groupTweets.filter( _ != firstTweet)
      firstAuthor = firstTweet.getAuthor
      subsequentAuthor = subsequentTweet.map(_.getAuthor).toSet
    } yield firstAuthor -> subsequentAuthor
    commonTagTweetSet.toMap
  }
}
