package assignment4

// private var is not accessible outside the class.
// so to get the info about private var, we need getter and setter
class Book(val title:String ,private var borrowed:Boolean = false){
  println("create book titled " + title)

  def isBorrowed = borrowed // getter
  def rented:Unit = borrowed=true // setter setting borrowed to true
  def returned:Unit = borrowed=false // setter setting borrowed to false
}
