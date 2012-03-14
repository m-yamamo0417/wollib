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

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MagicPacketTest {

    private MacAddress macAddress;
    private byte[] all255;

    @Before
    public void setUp() {
	this.macAddress = new MacAddress("FF:FF:FF:FF:FF:FF");
	this.all255 = new byte[] {
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF 
	};
    }

    @Test(expected = NullPointerException.class)
    public void testConstructWithNull() {
	MacAddress addr = null;
	new MagicPacket(addr);
    }

    @Test
    public void testConstructorWithString() {
	MagicPacket packet = new MagicPacket("FF:FF:FF:FF:FF:FF");
	assertThat(packet.getBytes(), is(this.all255));
    }

    @Test
    public void testGetBytesFFFFFFFFFFFF() {
	byte[] expected = this.all255;
	MagicPacket packet = new MagicPacket(this.macAddress);
	assertThat(packet.getBytes(), is(expected));
    }

    @Test
    public void testAsDatagramPacket() throws UnknownHostException { 
	InetAddress addr = InetAddress.getByAddress(new byte[]{
		(byte)0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
	    });

	MagicPacket packet = new MagicPacket(this.macAddress);
	DatagramPacket datagram = packet.asDatagramPacket();
	
	assertThat(datagram.getAddress(), is(addr));
	assertThat(datagram.getData(), is(this.all255));
	assertThat(datagram.getPort(), is(9));
	assertThat(datagram.getLength(), is(102));
    }

    @Test
    public void testAsDatagramPacketWithAddr() throws UnknownHostException {
	InetAddress addr = InetAddress.getByAddress(new byte[]{
		(byte)192, (byte)168, (byte)1, (byte)255
	    });
	MagicPacket packet = new MagicPacket(this.macAddress);
	DatagramPacket datagram = packet.asDatagramPacket(addr);

	assertThat(datagram.getAddress(), is(addr));
    }
}
