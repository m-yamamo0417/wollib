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

public class MagicPacketClient {

    public void send(String macAddress) {
	if(null == macAddress){
	    throw new NullPointerException();
	}
	if(!MacAddress.isValid(macAddress)){
	    throw new IllegalArgumentException();
	}
	MagicPacket packet = new MagicPacket(macAddress);
	MagicSocket socket = new MagicSocket();
	socket.send(packet);
    }
}