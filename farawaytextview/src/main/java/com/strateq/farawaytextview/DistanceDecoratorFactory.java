package com.strateq.farawaytextview;

/**
 * Created by abdulrahmanbabil on 1/13/18.
 */

class DistanceDecoratorFactory {
    public static DistanceDecorator getDistanceDecorator(int unit) {
        switch (unit) {
            case FarAwayTextView.METER_UNIT:
                return new MeterDistanceDecorator();
            case FarAwayTextView.MILE_UNIT:
                return new MileDistanceDecorator();

        }
        return new MeterDistanceDecorator();
    }
}
