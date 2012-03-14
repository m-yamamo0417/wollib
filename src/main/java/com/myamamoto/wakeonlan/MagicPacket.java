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

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MagicPacket {
    private static final InetAddress LIMITED_BROADCAST;
    private static final int PORT_DISCARD = 9;

    static {
	try {
	    byte[] address = new byte[]{
		(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF
	    };
	    LIMITED_BROADCAST = InetAddress.getByAddress(address);
	}catch(UnknownHostException ex) {
	    throw new AssertionError(ex);
	}
    }
    
    private final byte[] bytes = new byte[102];

    public MagicPacket(String macAddress) {
	this(new MacAddress(macAddress));
    }
    
    public MagicPacket(MacAddress macAddress) {
	if(null == macAddress) {
	    throw new NullPointerException();
	}
	for(int i = 0; i < 6; i++) {
	    this.bytes[i] = (byte) 0xFF;
	}
	byte[] macAddressBytes = macAddress.getBytes();
	for(int i = 0; i < 16; i++) {
	    for(int j = 0; j < macAddressBytes.length; j++){
		int indexOfBytes = (i + 1) * 6 + j;
		this.bytes[indexOfBytes] = macAddressBytes[j];
	    }
	}
    }

    public byte[] getBytes() {
	return this.bytes;
    }

    public DatagramPacket asDatagramPacket() {
	return this.asDatagramPacket(MagicPacket.LIMITED_BROADCAST);
    }

    public DatagramPacket asDatagramPacket(InetAddress address) {
	byte[] data = this.getBytes();
	int port = MagicPacket.PORT_DISCARD;
	return new DatagramPacket(data, data.length, address, port);
    }
    
}