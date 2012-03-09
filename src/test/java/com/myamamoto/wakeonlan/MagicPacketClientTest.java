package com.myamamoto.wakeonlan;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MagicPacketClientTest {

    
    @Test(expected=IllegalArgumentException.class)
    public void sendIllegalFormatMacAddress() {
	MagicPacketClient client = new MagicPacketClient();
	client.send("");
    }

    @Test
    public void testSend() {
	MagicPacketClient client = new MagicPacketClient();
	client.send("FF:FF:FF:FF:FF:FF");
    }
}
