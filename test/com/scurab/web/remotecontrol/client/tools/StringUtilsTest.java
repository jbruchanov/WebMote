package com.scurab.web.remotecontrol.client.tools;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase
{

	public void testGetParentDirWin()
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
	
    public void testGetParentDirUnix() {
        String slash = "/";
        String c = StringUtils.getParentDir("/", slash);
        assertNull(c);

        c = StringUtils.getParentDir(null, slash);
        assertNull(c);

        c = StringUtils.getParentDir("", slash);
        assertNull(c);

        c = StringUtils.getParentDir("/tmp", slash);
        assertEquals("/", c);

        c = StringUtils.getParentDir("/tmp/a", slash);
        assertEquals("/tmp", c);

        c = StringUtils.getParentDir("/tmp/a/b", slash);
        assertEquals("/tmp/a", c);
    }

}
