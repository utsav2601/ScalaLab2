package wallet
import scala.collection.mutable;
import scala.beans.BeanProperty


class WalletDetails
{
  

}

 class User(@BeanProperty val email1:String, @BeanProperty val password:Int)
{
var user_email = email1
var user_password = password
var user_id:Int = 123
def this()={
    this(null,0)
}
}

//case class IDCard(card_id: Int,card_name:String, card_number:String, expiration_date:Option[String]) extends User;
//case class WebLogin(login_id:Int, url:String, login:String, password:String) extends User
// case class BankAccount(user:User,ba_id:Int, account_name:Option[String], routing_number:String,account_number:String)


case class BankAccount(@BeanProperty val user_id: String)
{
  var id = user_id 
  @BeanProperty val details = Map(id -> List("utsav","umang"))
}
