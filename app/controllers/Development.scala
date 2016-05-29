package controllers


import models._
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import settings.Global
import settings.Global._

import controllers.Api._


import scala.util.Random

object Development extends Controller  {

  def icon = Action {
    Ok(views.html.icon())
  }
  def doc = Action {
    Ok(views.html.index("C'est la doc!!!!"))
  }

  def dev = Action{
    Ok(views.html.dev());
  }















  def init = Action {
    def square = Array.ofDim[(String, Int)](nbSquares).map{ tupple =>
      ("", -1)
    }
    for(i <- 0 until 1){
      DB.save(Square(nbSquaresOneEdge, square))
    }
    val s = "data:image/png;base64,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CgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCir8k+EAAAkfSURBVAWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgWEXYVFMSQCBAgQyBUQdrmeeiNAgACBCgX+P4cgkphNa3txAAAAAElFTkSuQmCC"
    Api.addSelectedSquare(Global.idCurrentMS, 1, 2, s)

    Ok("tout est bien: 6 MySquares ont ete ajouter a la base de donne")
  }



  // TODO: Connecter ca a la base de donne et refaire
  val colorsModel = Array.ofDim[Int](nbSquares, 3).map{array =>
    val i = r.nextInt(256)
    if(i < 250) null
    else array.map(x => r.nextInt(256))
  }

  val colors = colorsModel.map{ array =>
    if(array == null) Array(-1, -1, -1)
    else array
  }

  def mailtest = Action{
    send a new Mail (
      from = ("john.smith@mycompany.com", "John Smith"),
      to = Seq("mollierlauriane@gmail.com"),
      subject = "Import stuff",
      message = "Dear Boss..."
    )
    Ok("regarde dans taboite mail!!!!!")
  }




}
