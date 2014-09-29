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

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
class DigitalWalletController  {
     @RequestMapping(value = Array("/"),method=Array(RequestMethod.POST), headers = Array("content-type=application/json"),consumes = Array("application/json") )
     def create_user(@RequestBody user:User) : User= {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
        
     return user
     }
       
        @RequestMapping(value = Array("/users/{user_id}/bankaccount"),method=Array(RequestMethod.POST), headers = Array("content-type=application/json"),consumes = Array("application/json") )
    @ResponseBody def create_bankAccount(@PathVariable user_id: String ) : String = {
   //    user_id: Int, email:String, password:String, name: Option[String], created_at:String, updated_at:String
    		val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  val out = new StringWriter
  mapper.writeValue(out, new BankAccount(user_id))
      return out.toString()
    	
     }
       
     
     
	}

