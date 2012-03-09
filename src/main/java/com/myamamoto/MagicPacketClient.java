package com.myamamoto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MagicPacketClient {

    public void send(String macAddress) {
	if(!MacAddress.isValid(macAddress)){
	    throw new IllegalArgumentException();
	}
	MagicPacket packet = new MagicPacket(macAddress);
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