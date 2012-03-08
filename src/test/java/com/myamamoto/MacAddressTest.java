package com.myamamoto;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MacAddressTest {

    @Test(expected=NullPointerException.class)
    public void testConstructorNull() {
	String macAddress = null;
	new MacAddress(macAddress);
    }

    @Test
    public void testGetBytesFFFFFFFFFFFF() {
	String macAddress = "FF:FF:FF:FF:FF:FF";
	MacAddress addr = new MacAddress(macAddress);

	byte[] expected = new byte[]{
	    (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF
	};
	assertThat(addr.getBytes(), is(expected));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgument() {
	String macAddress = "";
	new MacAddress(macAddress);
    }

    @Test
    public void testToString() {
	String strAddr = "01:23:45:67:89:AB";
	MacAddress macAddress = new MacAddress(strAddr);
	assertThat(macAddress.toString(), is(strAddr));
    }
}