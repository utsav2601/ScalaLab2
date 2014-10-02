package wallet
import scala.collection.mutable
import scala.beans.BeanProperty
import scala.Mutable
import scala.collection.mutable.MutableList
import scala.collection.mutable.Map
import java.util.Date
import com.github.nscala_time.time.Imports._

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception
//remove if not needed
import scala.collection.JavaConversions._
import javax.validation.constraints.NotNull

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "POST parameter(s) missing")
case class MissingFieldError(error: String) extends RuntimeException(error)


case class User( @BeanProperty var email:String ,@BeanProperty var password:String ) 
{
def this(){
  this(null,null)
  
}

var user_id:String = _
var bank_list = MutableList[BankAccount]()
var card_list = MutableList[IdCard]()
var web_list = MutableList[WebLogin]()
var created_at:String = (DateTime.now).toString()
def view_user() :String=
{

  //Only for user 
  //send data in json format
return """{"user_id":""" + """""""+this.user_id+"""""""+""","""+""""email":"""+"""""""+
this.email+"""""""+""","""+ """"password":""" +"""""""+this.password+"""""""+ ""","""+
""""created_at":""" + """""""+this.created_at+""""}"""  


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

