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
import org.springframework.web.bind.annotation.{ResponseBody, RequestMapping, RequestParam, RestController}
import org.springframework.web.bind.annotation.PathVariable
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.StringWriter
import scala.Mutable
import scala.collection.mutable.MutableList
import scala.collection.mutable.Map
@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
class DigitalWalletController {
   var user_list = MutableList[User]()
   //var bank_account_list = MutableList[BankAccount]() 
   var user_bank = Map[String,MutableList[BankAccount]]()
   
   //create user
     @RequestMapping(value = Array("/"),method=Array(RequestMethod.POST), headers = Array("content-type=application/json"),consumes = Array("application/json") )
   @ResponseBody  def create_user(@RequestBody user:User) : User= {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
  user_list.+=(user)
  //create a null bank Account 
  var bank:BankAccount = BankAccount("","","","")
  var flag : MutableList[BankAccount] = MutableList(bank)
  user_bank += (user.user_id -> flag)
  return user
     
     }
       
   //view user
        @RequestMapping(value = Array("/users/{user_id1}"),method=Array(RequestMethod.POST), headers = Array("content-type=application/json"),consumes = Array("application/json") )
    @ResponseBody def view_user(@PathVariable user_id1: String, @RequestBody bank:BankAccount ) : String = {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
     var flag : MutableList[User]=  user_list.filter(User => User.user_id == user_id1)
    var user:User = null
   
    
 //   Check if the user already exists or not to avoid any exceptions
     if (!flag.isEmpty)
     {
      
     var flag_list: MutableList[BankAccount] = user_bank(user_id1)	//get the list related to the user id 
       flag_list.+=(bank)
         user = flag.head	//get the first element as there is going to be only one userid
         user_bank += (user.user_id -> flag_list) //map the userid with the list of bank account
       
     }
     
   val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  val out = new StringWriter
 
  mapper.writeValue(out, user_bank(user_id1))
  
 return out.toString()
    
     }
   
	}

