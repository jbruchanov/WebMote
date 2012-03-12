package com.scurab.web.remotecontrol.client;

import java.util.HashMap;
import java.util.List;

import com.scurab.web.remotecontrol.client.tools.JsonSimpleParser;

import junit.framework.TestCase;

public class MyTest extends TestCase
{
	@SuppressWarnings("unused")
	public void testDisk() throws Exception
	{
		
//		String disk = "[{\"N\" : \"C:\\\"}]";
//		String diska = "[{\"N\":\"C:\\\",\"T\":1},{\"N\":\"D:\\\",\"T\":1},{\"N\":\"F:\\\",\"T\":1},{\"N\":\"G:\\\",\"T\":3},{\"N\":\"H:\\\",\"T\":3},{\"N\":\"L:\\\",\"T\":1},{\"N\":\"R:\\\",\"T\":2},{\"N\":\"W:\\\",\"T\":2},{\"N\":\"X:\\\",\"T\":2}]";
//		String diskF = "[{\"N\":\"..\",\"T\":-1},{\"N\":\"$RECYCLE.BIN\",\"T\":10},{\"N\":\"-= Photos =-\",\"T\":10},{\"N\":\"0Work\",\"T\":10},{\"N\":\"1Skola\",\"T\":10},{\"N\":\"Release\",\"T\":10},{\"N\":\"System Volume Information\",\"T\":10},{\"N\":\"tmp\",\"T\":10},{\"N\":\"Utils\",\"T\":10},{\"N\":\"VMS\",\"T\":10},{\"N\":\"CV-Resume.pdf\",\"T\":20},{\"N\":\"EnglishDictionary.xls\",\"T\":20},{\"N\":\"LEDs_Hack_1.2.10.apk\",\"T\":20},{\"N\":\"Nolf Disc 2.bin\",\"T\":20},{\"N\":\"Nolf Disc 2.cue\",\"T\":20},{\"N\":\"NOLF_CD1.iso\",\"T\":20},{\"N\":\"PlatbyDatumy.xls\",\"T\":20},{\"N\":\"Visual Studio 2008.zip\",\"T\":20}]";
//		
////		List<HashMap<String,String>> d = JsonSimpleParser.parse(disk);
////		assertEquals(1,d.size());
////		List<HashMap<String,String>> d1 = JsonSimpleParser.parse(diska);
//		String q = "{\"power\":\"power\",\"test\":\"test\",\"input\":\"input\",\"effect\":\"effect\",\"settings\":\"settings\",\"sub+\":\"sub+\",\"sub-\":\"sub-\",\"center+\":\"center+\",\"center-\":\"center-\",\"surround+\":\"surround+\",\"surround-\":\"surround-\",\"vol+\":\"vol+\",\"vol-\":\"vol-\",\"mute\":\"mute\"}";
//		
//		
		String b = "{\"Audio\":[\"WinAmp\"],\"MediaCenter\":[],\"Picture\":[\"Media Center Pictures\",\"Windows Photo Viewer\"],\"Television\":[\"Avermedia TV\"],\"Video\":[\"Media Player classic\",\"VLC Player\"],\"WinLIRC\":[\"logitech_z680\",\"Panasonic_EUR644340\"]}";
		HashMap<String,List<String>> d3 = JsonSimpleParser.parseApps(b);
		assertTrue(d3.size() > 0);
	}

//	@Override
//	public String getModuleName()
//	{
//		// TODO Auto-generated method stub
//		return "com.scurab.web.remotecontrol.RemoteControl";
//	}
}
