package wallet
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.{ ResponseBody, RequestMapping, RequestParam, RestController }
import org.springframework.web.bind.annotation.PathVariable
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.StringWriter
import scala.Mutable
import scala.collection.mutable.MutableList
import scala.collection.mutable.Map
import javax.validation.Valid
import org.springframework.validation.BindingResult;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
class DigitalWalletController {
 
var user_autoId:Int = 10000  
var card_autoId:Int = 20000
var web_autoId:Int = 30000
var bank_autoId:Int = 40000
  var user_list = MutableList[User]()
 
  //create user
  @RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes=Array("application/json"))
  @ResponseBody def create_user( @RequestBody user: User): String = {
    //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String

  user.user_id  = "u-"+ user_autoId
 user_autoId = user_autoId+1 
  user_list.+=(user)

//   val mapper = new ObjectMapper()
//    mapper.registerModule(DefaultScalaModule)
//    val out = new StringWriter
//    mapper.writeValue(out, user.view_user())
//    return out.toString()
  return user.view_user();
  }
  

  //View User
  @RequestMapping(value = Array("/users/{user_id1}"), method = Array(RequestMethod.GET),produces = Array("application/json"))
  @ResponseBody def view_user(@PathVariable user_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)

    }

    return user1.view_user()

  }

  
  //Update User
  @RequestMapping(value = Array("/users/{user_id1}"), method = Array(RequestMethod.PUT), headers = Array("content-type=application/json"), consumes = Array("application/json"))
  @ResponseBody def update_user(@PathVariable user_id1: String, @RequestBody user: User): String = {
    //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String

    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
    var user1: User = find_user(0)
    user1.email = user.email
    user1.password = user.password
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter
    mapper.writeValue(out, user1)
    return out.toString()

  }
  
  
  //create IDCard 
  @RequestMapping(value = Array("/users/{user_id1}/idcards"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def create_IdCard(@PathVariable user_id1: String, @RequestBody card: IdCard): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    card.card_id  = "c-"+ card_autoId
    card_autoId = card_autoId +1
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
      user1.setIdCard(MutableList(card))
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,card)

    return out.toString()

  }

  
  
//List all ID cards 
  @RequestMapping(value = Array("/users/{user_id1}/idcards"), method = Array(RequestMethod.GET), produces = Array("application/json"))
 def list_IdCard(@PathVariable user_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getIdCard())

    return out.toString()

  }
  
//delete IDCard 
  @RequestMapping(value = Array("/users/{user_id1}/idcards/{card_id1}"), method = Array(RequestMethod.DELETE), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def delete_IdCard(@PathVariable user_id1: String, @PathVariable card_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
 var card = MutableList[IdCard]()
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
      card = user1.getIdCard()
      //assume card id is unique so there will be only one card id
  
  
    var card_list: MutableList[IdCard] = card.filter(IdCard => IdCard.card_id  != card_id1)
 
    user1.replaceCardList(card_list)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getIdCard())

    return out.toString()

  }

 //create Web Login
 @RequestMapping(value = Array("/users/{user_id1}/weblogins"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def create_Weblogin(@PathVariable user_id1: String, @RequestBody web: WebLogin): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
   web.login_id = "l-"+ web_autoId
   web_autoId = web_autoId +1
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)

    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      var user1: User = find_user(0)
      user1.setWebLogin(MutableList(web))
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out, web)

    return out.toString()
 }
    
  //List all web site logins 
  @RequestMapping(value = Array("/users/{user_id1}/weblogins"), method = Array(RequestMethod.GET), produces = Array("application/json"))
 def list_weblogin(@PathVariable user_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getWebLogin())

    return out.toString()

  }
  
 //delete web logins 
  @RequestMapping(value = Array("/users/{user_id1}/weblogins/{login_id1}"), method = Array(RequestMethod.DELETE), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def delete_webLogin(@PathVariable user_id1: String, @PathVariable login_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
 var web = MutableList[WebLogin]()
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
      web = user1.getWebLogin()
      //assume card id is unique so there will be only one card id
   
    var web_list: MutableList[WebLogin] = web.filter(WebLogin => WebLogin.login_id != login_id1)
   
    user1.replaceWebList(web_list)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getWebLogin())

    return out.toString()

  }

  
//create Bank Account for a particular user
 @RequestMapping(value = Array("/users/{user_id1}/bankaccounts"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def create_bankAccount(@PathVariable user_id1: String, @RequestBody bank: BankAccount): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
   bank.ba_id = "b-" + bank_autoId
   bank_autoId = bank_autoId +1
   var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)

    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      var user1: User = find_user(0)
      user1.setBankAccount(bank)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out, bank)

    return out.toString()

  }
 
 //List all the bank Accounts
 @RequestMapping(value = Array("/users/{user_id1}/bankaccounts"), method = Array(RequestMethod.GET), produces = Array("application/json"))
 def list_bankaccount(@PathVariable user_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getBankAccount())

    return out.toString()

  }
  //delete web logins 
  @RequestMapping(value = Array("/users/{user_id1}/bankaccounts/{ba_id_id1}"), method = Array(RequestMethod.DELETE), headers = Array("content-type=application/json"), consumes = Array("application/json"),produces = Array("application/json"))
 def delete_bankaccount(@PathVariable user_id1: String, @PathVariable ba_id_id1: String): String = {
    //  user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    var find_user: MutableList[User] = user_list.filter(User => User.user_id == user_id1)
var user1:User = null
 var bank = MutableList[BankAccount]()
    //   Check if the user already exists or not to avoid any exceptions
    if (!find_user.isEmpty) {

      user1 = find_user(0)
      bank = user1.getBankAccount()
      //assume card id is unique so there will be only one card id

    var bank_list: MutableList[BankAccount] = bank.filter(BankAccount => BankAccount.ba_id != ba_id_id1)
   
    user1.replaceBankList(bank_list)
    }

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter

    mapper.writeValue(out,user1.getBankAccount())

    return out.toString()

  }

 

}

