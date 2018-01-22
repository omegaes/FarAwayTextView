package com.strateq.farawaytextview;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by abdulrahmanbabil on 1/14/18.
 */

public class MeterDistanceDecorator implements DistanceDecorator {
    @Override
    public String getDistance(Context context, float distanceInMeter, String prefix, String postfix) {
        DecimalFormat df2 = new DecimalFormat(".##");

        if (distanceInMeter < 1000) {
            df2 = new DecimalFormat("");
            return context.getString(R.string.meters, df2.format(Math.floor(distanceInMeter)), prefix, postfix);
        } else if (distanceInMeter < 10000) {
            return context.getString(R.string.km, df2.format(distanceInMeter / 1000), prefix, postfix);
        } else {
            df2 = new DecimalFormat("");
            return context.getString(R.string.km, df2.format(Math.floor(distanceInMeter / 1000)), prefix, postfix);
        }

    }
}