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
     def create_user(@RequestBody user:User) : String= {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
      
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  val out = new StringWriter
  
  user_list.+=(user)
  mapper.writeValue(out, user)
  
 return out.toString()
     
     }
       
   //view user
        @RequestMapping(value = Array("/users/{user_id1}"),method=Array(RequestMethod.POST), headers = Array("content-type=application/json"),consumes = Array("application/json") )
    @ResponseBody def view_user(@PathVariable user_id1: String, @RequestBody bank:BankAccount ) : String = {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
     var flag : MutableList[User]=  user_list.filter(User => User.user_id == user_id1)
    var user:User = null
     if (!flag.isEmpty)
     {
         user = flag.head
         user.setBankAccount(bank);
       
     }
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val out = new StringWriter
    mapper.writeValue(out,bank)
    return out.toString()
     }
       
     
     
	}

