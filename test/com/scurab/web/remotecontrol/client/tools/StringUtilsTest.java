package com.scurab.web.remotecontrol.client.tools;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase
{

	public void testGetParentDir()
	{
		String c = StringUtils.getParentDir("C:\\");
		assertNull(c);
		
		c = StringUtils.getParentDir(null);
		assertNull(c);
		
		c = StringUtils.getParentDir("");
		assertNull(c);
		
		c = StringUtils.getParentDir("C:\\Dir");
		assertEquals("C:\\",c);
		
		c = StringUtils.getParentDir("C:\\Dir\\A");
		assertEquals("C:\\Dir",c);
		
		c = StringUtils.getParentDir("C:\\Dir\\A\\B");
		assertEquals("C:\\Dir\\A",c);
	}

}
