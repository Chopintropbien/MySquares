package controllers

import play.api.mvc.{Call, RequestHeader}
import models.Squares._


object FlashSession {
  // get the session params
  def connected(implicit request : RequestHeader) = request.session.get("email") match{
    case Some(s) => true
    case None => false
  }

  def getUserId(implicit request : RequestHeader): Option[Long] = request.session.get("idUser") match{
    case Some(id) => Some(id.toLong)
    case None => None

  }

  def cookieEmail = "wtf.squareit.www.email"

  def cookieHash = "wtf.squareit.www.passwordHash"



  def getUserEmail(implicit request : RequestHeader): Option[String] = request.session.get("email")

  def redirectByFlash(implicit request : RequestHeader, default: Call = routes.Application.home()): Call = {
    val redirection = Map(
      "haveSquares" -> routes.Application.haveSquares(idCurrentSquare),
      "home" -> routes.Application.home(),
      "company" -> routes.Application.company()
    )

    request.flash.get("redirection") match {
      case None => default
      case Some(redirect) => redirection.get(redirect).getOrElse(default)
    }
  }

  def getRedirectionFlashString(implicit request : RequestHeader): String =
    request.flash.get("redirection").getOrElse("")
}
