package assignment4
import assignment4.Book

class Library(val address:String, val hours:String = "9AM to 5PM"){

  var bookCollection:List[Book] = List.empty[Book]

  def addBook(item:Book):Unit = bookCollection = item :: bookCollection
  def borrowBook(title:String):Unit = {
    if (listAvailableBook.exists(_.title == title)){
      listAvailableBook.filter(_.title == title).map((item:Book) => item.rented)
      println(s"$title is being rented right now")
    }
    else if (bookCollection.exists(_.title == title)) {
      println("Book is not currently available. It must be rented right now")
    }
    else {
      println(s"this library does not have $title")
    }
  }
  def printAvailableBooks:Unit = {
    listAvailableBook.map((item:Book) => println(item.title))
  }
  def returnBook(title:String):Unit = {
    bookCollection.filter(_.title == title).map((item:Book) => item.returned)
  }
  private def listAvailableBook:List[Book] = bookCollection.filter(_.isBorrowed == false)
}
