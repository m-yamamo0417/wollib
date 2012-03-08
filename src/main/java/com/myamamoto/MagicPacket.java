package com.myamamoto;

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