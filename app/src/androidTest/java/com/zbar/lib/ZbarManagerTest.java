package com.zbar.lib;

import junit.framework.TestCase;

/**
 * Created by root on 16-1-7.
 */
public class ZbarManagerTest extends TestCase {

    static {
        System.loadLibrary("zbar");
    }

    public void testDecode() throws Exception {

        ZbarManager zb = new ZbarManager();
        System.out.print(zb.decode(new byte[]{1, 2}, 2, 3, true, 4, 2, 3, 4));
    }
}