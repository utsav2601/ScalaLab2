package wallet
import org.springframework.boot.SpringApplication
object digitalWalletApp {
def main(args:Array[String]) {
  SpringApplication.run(classOf[DigitalWalletController]);
  
}
}