package wallet
import scala.collection.mutable;
import scala.beans.BeanProperty
import scala.Mutable
import scala.collection.mutable.MutableList
import scala.collection.mutable.Map

class WalletDetails
{
  

}

case class User( @BeanProperty val email:String,@BeanProperty val password:String)
{

 var user_id : String = "jjj"
//val bank_list = MutableList[BankAccount]()

def this()={
    this(null,null)
}
   

}

//case class IDCard(card_id: Int,card_name:String, card_number:String, expiration_date:Option[String]) extends User;
//case class WebLogin(login_id:Int, url:String, login:String, password:String) extends User
 case class BankAccount(@BeanProperty val ba_id:String, @BeanProperty val account_name:String, @BeanProperty val routing_number:String, @BeanProperty val account_number:String)
 {
   def this()={
    this(null,null,null,null)
}

 }

