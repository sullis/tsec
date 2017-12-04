package tsec.libsodium.authentication

import tsec.libsodium.ScalaSodium
import tsec.libsodium.authentication._
import tsec.libsodium.authentication.internal.SodiumMacPlatform

trait HS512

object HS512 extends SodiumMacPlatform[HS512] {
  val keyLen: Int       = ScalaSodium.crypto_auth_hmacsha512_KEYBYTES
  val macLen: Int       = ScalaSodium.crypto_auth_hmacsha512_BYTES
  val algorithm: String = "HMACSHA512"

  private[tsec] def sodiumSign(in: Array[Byte], out: Array[Byte], key: SodiumMACKey[HS512])(
      implicit S: ScalaSodium
  ): Int = S.crypto_auth(out, in, in.length, key)

  private[tsec] def sodiumVerify(in: Array[Byte], hashed: SodiumMAC[HS512], key: SodiumMACKey[HS512])(
      implicit S: ScalaSodium
  ): Int = S.crypto_auth_verify(hashed, in, in.length, key)
}
