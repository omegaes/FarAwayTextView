package com.strateq.farawaytextview;

import android.content.Context;

/**
 * Created by abdulrahmanbabil on 1/14/18.
 */

public interface DistanceDecorator {
    String getDistance(Context context, float distanceInMeter, String prefix, String postfix);
}