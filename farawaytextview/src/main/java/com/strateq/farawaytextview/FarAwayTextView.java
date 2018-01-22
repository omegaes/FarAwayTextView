package com.strateq.farawaytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by abdulrahmanbabil on 1/11/18.
 */

public class FarAwayTextView extends android.support.v7.widget.AppCompatTextView implements Observer {


    enum Units {METER, MILE}

    final static int METER_UNIT = 1;
    final static int MILE_UNIT = 2;


    private boolean useCurrentLocationAsSource;
    private Location source;
    private Location destination;
    private int unit = METER_UNIT;
    private String prefix;
    private String postfix;


    public FarAwayTextView(Context context) {
        super(context);
    }

    public FarAwayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FarAwayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (useCurrentLocationAsSource) {
            FarAwayObserver.getInstance(getContext()).addObserver(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FarAwayObserver.getInstance(getContext()).deleteObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (useCurrentLocationAsSource && o instanceof Location) {
            source = (Location) o;
            render();
        }
    }


    public boolean isUseCurrentLocationAsSource() {
        return useCurrentLocationAsSource;
    }

    public void setUseCurrentLocationAsSource(boolean useCurrentLocationAsSource) {
        setUseCurrentLocationAsSource(useCurrentLocationAsSource, true);
    }

    public Units getUnit() {
        switch (unit) {
            case 1:
                return Units.METER;
            case 2:
                return Units.MILE;
        }
        return Units.METER;
    }

    public void setUnit(Units unit) {
        switch (unit) {
            case METER:
                this.unit = METER_UNIT;
                break;
            case MILE:
                this.unit = MILE_UNIT;
                break;
            default:
                this.unit = METER_UNIT;

        }
        render();
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.FarAwayTextView, 0, 0);

        double sLat = a.getFloat(R.styleable.FarAwayTextView_source_latitude, 1000);
        double sLng = a.getFloat(R.styleable.FarAwayTextView_source_longitude, 1000);

        double dLat = a.getFloat(R.styleable.FarAwayTextView_destination_latitude, 1000);
        double dLng = a.getFloat(R.styleable.FarAwayTextView_destination_longitude, 1000);

        String prefix = a.getString(R.styleable.FarAwayTextView_prefix);
        String postfix = a.getString(R.styleable.FarAwayTextView_postfix);


        boolean useCurrentLocationAsSource = a.getBoolean(R.styleable.FarAwayTextView_use_current_location_as_source, false);
        int unit = a.getInt(R.styleable.FarAwayTextView_unit, 0);

        setSource(sLat, sLng, false);

        setUseCurrentLocationAsSource(useCurrentLocationAsSource, false);

        setUnit(unit, false);

        setPostfix(postfix, false);
        setPrefix(prefix, false);


        setDestination(dLat, dLng, false);
        a.recycle();
        render();

    }

    private void setUnit(int unit, boolean render) {
        this.unit = unit;
        if (render)
            render();
    }

    private void setUseCurrentLocationAsSource(boolean useCurrentLocationAsSource, boolean render) {
        FarAwayObserver.getInstance(getContext()).deleteObserver(this);

        if (useCurrentLocationAsSource) {
            FarAwayObserver.getInstance(getContext()).addObserver(this);
        }

        this.useCurrentLocationAsSource = useCurrentLocationAsSource;

        if (render)
            render();
    }


    private void render() {

        if (source != null && destination != null) {
            float distanceInMeters = source.distanceTo(destination);
            if (distanceInMeters < 3) {
                setText(R.string.at_destination);
                return;
            }
            DistanceDecorator distanceDecore = DistanceDecoratorFactory.getDistanceDecorator(unit);
            setText(distanceDecore.getDistance(getContext(), distanceInMeters, prefix, postfix));
        } else if (destination == null) {
            setText(R.string.destination_not_defined);

        } else if (useCurrentLocationAsSource && source == null) {
            setText(R.string.location_service_not_available);
        } else {
            setText(R.string.source_not_defined);
        }
    }

    private void setDestination(double lat, double lng, boolean render) {
        if (
                lat <= 90 && lat >= -90 &&
                        lng <= 180 && lng >= -180) {
            destination = new Location("destination");
            destination.setLatitude(lat);
            destination.setLongitude(lng);
        } else if (render) {
            throw new IllegalArgumentException("Invalid values for Longitude or Latitude");
        }
        if (render)
            render();

    }

    private void setSource(double lat, double lng, boolean render) {
        if (
                lat <= 90 && lat >= -90 &&
                        lng <= 180 && lng >= -180) {
            source = new Location("source");
            source.setLatitude(lat);
            source.setLongitude(lng);
        } else if (render) {
            throw new IllegalArgumentException("Invalid values for Longitude or Latitude");
        }
        if (render)
            render();

    }

    public void setPostfix(String postfix, boolean render) {
        this.postfix = postfix;

        if (this.postfix == null)
            this.postfix = "";

        if (render)
            render();

    }

    public void setPrefix(String prefix, boolean render) {
        this.prefix = prefix;
        if (this.prefix == null)
            this.prefix = "";

        if (render)
            render();
    }


    public void setDestination(double lat, double lng) {
        setDestination(lat, lng, true);
    }

    public void setSource(double lat, double lng) {
        setSource(lat, lng, true);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        setPrefix(prefix, true);
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        setPostfix(postfix, true);
    }


}
