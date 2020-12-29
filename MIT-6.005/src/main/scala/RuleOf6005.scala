package rules
object RuleOf6005{
  /*
  * @param writtenByYourself
  * @param availableToOthers
  * @param citingYourSource
  * @param implementationRequired
   */
  def mayUseCodeInAssignment(writtenByYourself:Boolean, availableToOthers:Boolean, writtenAsCourseWork:Boolean,
                            citingYourSource: Boolean, implementationRequired:Boolean):Boolean = {

      writtenByYourself && availableToOthers && writtenAsCourseWork && citingYourSource && implementationRequired
  }
  def main(args: Array[String]):Unit = {
    println("Hello World!")
    println("your may certainly use code you wrote yourself: " + mayUseCodeInAssignment(writtenByYourself = true,
      availableToOthers = true,writtenAsCourseWork = true,citingYourSource = true,implementationRequired = true))
    println("--the end--")
  }
}
