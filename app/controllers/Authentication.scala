package controllers

import java.security.MessageDigest

import models._
import play.api.Logger
import play.api.libs.Crypto
import play.api.mvc.Action


import scala.util.Random

object Authentication {

  /**
   * Verifying if the given password, salted with the provided salts, corresponds to the hash
   * @param password The password that we want to test
   * @param hash The hash that we want to test
   * @param salt1 The first salt that have been used for the encryption
   * @param salt2 The second salt that have been used for the encryption
   * @return True if the hash corresponds to the password, else false
   */
  def verifyPassword(password: String, hash: String, salt1: Seq[Byte], salt2: Int): Boolean = {
    encryptPasswordWithSalts(password, salt1, salt2) == hash
  }


  /**
   * Password Hashing Using Message Digest Algo
   * @param password The password that we want to encrypt
   * @param salt1 The first salt that will be used for the encryption
   * @param salt2 The second salt that will be used for the encryption
   * @return The encrypted password
   */
  def encryptPasswordWithSalts(password: String, salt1: Seq[Byte], salt2: Int): String = {
    val algorithm: MessageDigest = MessageDigest.getInstance("SHA-256")
    val saltPassword = saltPasswordWithSalts(password.getBytes, salt1, salt2)
    algorithm.reset
    algorithm.update(saltPassword)
    val messageDigest: Array[Byte] = algorithm.digest

    getHexString(messageDigest)
  }

  /**
   * Password Hashing Using Message Digest Algo
   * @param password The password that we want to encrypt
   * @return A tuple[3] with:
   *         _.1 The encrypted password,
   *         ._2 the first salt that we used during the encryption,
   *         ._3 the second salt that we used during the encryption
   */
  def encryptPassword(password: String): (String, Seq[Byte], Int) = {
    val salt = salt1
    val s2 = salt2(password.length)
    val hash = encryptPasswordWithSalts(password, salt, s2)
    (hash, salt, s2)
  }

  /**
   * Generate HexString For Password & userId Encryption
   * @param messageDigest The message we want to generate
   * @return The generated HexString
   */
  def getHexString(messageDigest: Array[Byte]): String = {
    val hexString: StringBuffer = new StringBuffer
    messageDigest foreach { digest =>
      val hex = Integer.toHexString(0xFF & digest)
      if (hex.length == 1) hexString.append('0') else hexString.append(hex)
    }
    Logger.info("encrypt Data" + hexString.toString)
    hexString.toString
  }

  /**
   * Salt the password with the given salts
   * @param password The password transformed in Array[Byte]
   * @param salt1 The first salt we will use for the salting
   * @param salt2 The second salt we will use for the salting
   * @return The salted password
   */
  def saltPasswordWithSalts(password : Array[Byte], salt1: Seq[Byte], salt2: Int): Array[Byte] ={
    val saltPassword: Array[Byte] = Array.ofDim[Byte](password.length + salt1.length - 1).zipWithIndex.map{
      case (b, i) => {
        if(i >= password.length) salt1(i - password.length)
        else if(i == salt2) salt1(salt1.length - 1)
        else b
      }
    }
    saltPassword
  }

  /**
   * Salt the password and generate salts
   * @param password The password transformed in Array[Byte]
   * @return A tuple[3] with:
   *         _.1 The salted password,
   *         ._2 the first salt that we used during the salting,
   *         ._3 the second salt that we used during the salting
   */
  def saltPassword(password : Array[Byte]): (Array[Byte], Seq[Byte], Int) = {
    val salt = salt1
    val s2 = salt2(password.length)
    val saltPassword = saltPasswordWithSalts(password, salt, s2)
    (saltPassword, salt, s2)
  }

  /**
   * Provide a salt
   * @return An Array[Byte] of random bytes
   */
  def salt1(): Array[Byte] = {
    val r = new Random
    val bytes = Array.ofDim[Byte](6)
    r.nextBytes(bytes)
    bytes
  }

  /**
   * Provide a second salt
   * @param passwordLength The length of the password (Array[Byte]), obtained from the password (String)
   * @return A int between 0 and passwordLength none include
   */
  def salt2(passwordLength: Int): Int = {
    val r = new Random
    r.nextInt(passwordLength)
  }

  /**
   * Encrypt an id
   * @param id An id of an object
   * @return The encrypted id
   */
  def encrypId(id: String): String = {
    Crypto.encryptAES(id) // TODO
  }

  /**
   * Decrypt and decrypted id
   * @param id An encrypted id of an object
   * @return The decrypted id
   */
  def decrypId(id: String): String = {
    Crypto.decryptAES(id)
  }

}
