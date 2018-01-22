package com.strateq.farawaytextview;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by abdulrahmanbabil on 1/14/18.
 */

public class MileDistanceDecorator implements DistanceDecorator {
    @Override
    public String getDistance(Context context, float distanceInMeter, String prefix, String postfix) {
        double distanceInMile = distanceInMeter * 0.000621371;
        DecimalFormat df2;
        if (distanceInMile < 0.1) {
            double distanceInFoot = distanceInMeter * 3.28084;
            df2 = new DecimalFormat("");
            return context.getString(R.string.feet, df2.format(Math.floor(distanceInFoot)), prefix, postfix);
        } else if (distanceInMile <= 1) {
            df2 = new DecimalFormat(".##");
            return context.getString(R.string.mile, df2.format(Math.floor(distanceInMile)), prefix, postfix);
        } else if (distanceInMile <= 10) {
            df2 = new DecimalFormat(".##");
            return context.getString(R.string.miles, df2.format(Math.floor(distanceInMile)), prefix, postfix);
        } else {
            df2 = new DecimalFormat("");
            return context.getString(R.string.miles, df2.format(Math.floor(distanceInMile)), prefix, postfix);
        }
    }
}

