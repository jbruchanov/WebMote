package com.scurab.web.remotecontrol.client.tools;

public class RCMath {

    /**
     * Get angle
     * 
     * @param x
     *            - current X position
     * @param y
     *            - current Y position
     * @param origX
     *            - center X point, relative origin X
     * @param origY
     *            - center Y point, relative origin Y
     * @return
     */
    public static int getAngle(int x, int y, int origX, int origY) {
        double angle = Math.atan2(y - origY, x - origX) * 180 / Math.PI + 90;
        if (angle < 0) {
            angle = 360 + angle;
        }
        return (int) angle;
    }
}