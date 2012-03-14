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

    @Test
    public void testIsValidTrue() {
	String strAddr = "01:23:45:67:89:Ab";
	assertThat(MacAddress.isValid(strAddr), is(true));
    }

    @Test
    public void testIsValidFalse() {
	assertThat(MacAddress.isValid(null), is(false));
	String strAddr = "zz:zz:zz:zz:zz:zz";
	assertThat(MacAddress.isValid(strAddr), is(false));
	strAddr = "-1:-1:-1:-1:-1:-1";
	assertThat(MacAddress.isValid(strAddr), is(false));
    }
}
