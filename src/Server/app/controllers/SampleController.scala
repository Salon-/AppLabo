/**
 *
 */
package controllers
import play.api._
import play.api.mvc._
import play.api.libs.json._

/**
 * 
 *
 */
object SampleController extends Controller {
	def sample1 = Action {
	  Ok(views.html.index("Sample controller#sample1"))
	}
	
	def sample2 = Action {
	  val jsonObject = Json.toJson(
	      Map(
	          "Name" -> "Taro",
	          "Tel" -> "03-1234-5678")
	          )
	  Ok(jsonObject)
	}
}