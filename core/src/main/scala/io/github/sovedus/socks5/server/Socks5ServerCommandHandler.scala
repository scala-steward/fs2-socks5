/*
 * Copyright 2025 Sovedus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.sovedus.socks5.server

import cats.effect.Resource

import com.comcast.ip4s.{IpAddress, Port}

trait Socks5ServerCommandHandler[F[_]] {

  /** The implementation must create a resource (e.g. http client) with [[fs2.Pipe]]. The input
    * stream contains the data from the client that should be sent to `targetIp`:`targetPort`.
    * The output stream is the data received from the receiver and will be forwarded to the
    * client.
    *
    * @param targetIp
    *   Request destination address
    * @param targetPort
    *   Request destination port
    * @return
    *   Resource with pipe with input data from client and output data from destination
    */
  def handle(targetIp: IpAddress, targetPort: Port): Resource[F, fs2.Pipe[F, Byte, Byte]]
}
