/*
 * Copyright 2012 m_yamamo0417
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

package com.myamamoto.wakeonlan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MagicSocket {
    public void send(MagicPacket packet) {
	DatagramPacket datagram = packet.asDatagramPacket();
	DatagramSocket socket = null;
	try {
	    socket = new DatagramSocket();
	    socket.setBroadcast(true);
	    socket.send(datagram);
	} catch (SocketException ex) {
	    throw new RuntimeException(ex);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	} finally {
	    if(null != socket) {
		socket.close();
	    }
	}
    }
}
