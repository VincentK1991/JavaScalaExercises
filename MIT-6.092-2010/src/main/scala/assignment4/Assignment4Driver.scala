package assignment4
import assignment4.{Book,Library}

object Main extends App {
  // the driver main method is located here
  // first create two libraries
  val firstLibrary = new Library("10 Main St.")
  val secondLibrary = new Library("228 Liberty St.")

  // and add 4 books to the first library
  firstLibrary.addBook(new Book ("The Da Vinci Code"))
  firstLibrary.addBook(new Book ("Le Petit Prince"))
  firstLibrary.addBook(new Book ("A Tale of Two Cities"))
  firstLibrary.addBook(new Book ("The Lord of the Rings"))

  // print opening hours and the address
  println("The library operated " + firstLibrary.hours)
  println("first library address = " + firstLibrary.address)
  println("second library address = " + secondLibrary.address)
  println(" ")

  // try to borrow the Lord of the Rings from Both libraries
  firstLibrary.borrowBook("The Lord of the Rings")
  firstLibrary.borrowBook("The Lord of the Rings")
  secondLibrary.borrowBook("The Lord of the Rings")
  println(" ")

  // print the title of all available books from both libraries
  println("Book available in the first library")
  firstLibrary.printAvailableBooks
  println("Book available in the second library")
  secondLibrary.printAvailableBooks
  println(" ")

  // return the Lords of the Rings to the first library
  println("returning the Lord of the Rings")
  firstLibrary.returnBook("The Lord of the Rings")
  println(" ")

  // print the available book from the first library
  firstLibrary.printAvailableBooks
  println(" ")
  println("--end of the program--")
}
