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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAddress {
    private static final Pattern pattern;
    
    static {
	String regex = "(\\p{XDigit}{2})";
	for(int i = 0; i < 5; i++) {
	    regex += "[:-](\\p{XDigit}{2})";
	}
	pattern = Pattern.compile(regex);
    }

    public static boolean isValid(String macAddress) {
	if(null == macAddress) {
	    return false;
	}
	return MacAddress.pattern.matcher(macAddress).matches();
    }

    private final String macAddress;
    private final byte[] bytes = new byte[6];
    
    public MacAddress(String macAddress) {
	if(null == macAddress) {
	    throw new NullPointerException();
	}

	Matcher matcher = MacAddress.pattern.matcher(macAddress);
	if(!matcher.matches()) {
	    throw new IllegalArgumentException();
	}
	this.macAddress = macAddress;
	this.setBytes(matcher);
    }

    public byte[] getBytes() {
	return this.bytes;
    }

    @Override
    public String toString() {
	return this.macAddress;
    }
    
    private void setBytes(Matcher matcher) {
	if(bytes.length != matcher.groupCount()){
	    throw new AssertionError(matcher);
	}
	try{
	    for(int i = 0; i < bytes.length; i++) {
		String s = matcher.group(i + 1);
		byte b = (byte) Integer.parseInt(s, 16);
		this.bytes[i] = b;
	    }
	}catch(NumberFormatException ex) {
	    throw new AssertionError(ex);
	}
    }
}
