package wallet
import scala.collection.mutable;
import scala.beans.BeanProperty
import scala.Mutable
import scala.collection.mutable.MutableList
import scala.collection.mutable.Map


class WalletDetails
{
  

}

case class User( @BeanProperty var email:String,@BeanProperty var password:String)
{

var user_id:String = null
var bank_list = MutableList[BankAccount]()
var card_list = MutableList[IdCard]()
var web_list = MutableList[WebLogin]()
def view_user():String=
{
 return ( this.user_id + this.email + this.password )
}
 
def getBankAccount():MutableList[BankAccount]=
 {
   this.bank_list 
   
 }
 def setBankAccount(bank:BankAccount) {
   this.bank_list += bank
   
 }
 
 def replaceBankList(bank:MutableList[BankAccount])
 {
   this.bank_list = bank
   
 }
 def setIdCard(card:MutableList[IdCard]) {
   this.card_list.++=(card)
   }
 
 def replaceCardList(card:MutableList[IdCard])
 {
   this.card_list = card
   
 }

 def getIdCard():MutableList[IdCard]=
 {
   this.card_list 
   
 }
 
 def getWebLogin(): MutableList[WebLogin]=
 {
   this.web_list 
   
 }
 def setWebLogin(web:MutableList[WebLogin]) {
   this.web_list.++=(web)
   }
 
 def replaceWebList(web:MutableList[WebLogin])
 {
   this.web_list = web
   
 }

def this()={
    this(null,null)
}
   

}

case class IdCard(@BeanProperty val card_name:String, @BeanProperty val card_number:String,@BeanProperty val expiration_date:Option[String])
{
 var card_id: String = null
   def this()={
    this(null,null,None)
   }

}
case class WebLogin(@BeanProperty url:String, @BeanProperty login:String, @BeanProperty password:String)
{
   var login_id:String = null
   def this()={
    this(null,null,null)
   }  

}
 case class BankAccount( @BeanProperty val account_name:String, @BeanProperty val routing_number:String, @BeanProperty val account_number:String)
 {
   var ba_id:String = null
   def this()={
    this(null,null,null)
}

 }

