[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-FarAwayTextView-green.svg?style=flat )]( https://android-arsenal.com/details/1/6712 )
# FarAwayTextView

An A custom TextView component for Android platform that calculates and updates its text regarding to geolocation distance between two geolocation points

## Installation

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```
    dependencies {
    	compile 'com.github.omegaes:FarAwayTextView:1.0.2'
    }
````


## Usage

Define one or more from FarAwayTextView using xml or java/kotlin/anko, set destination geolocation and source geolocation,set which unit to use (meter(Meters - Kilo Meters), mile (Feet - Miles)), set using current device location as source location(Keep in mind your app have to get Location permission from device).

That's it, it's so simple!!

```
 <com.strateq.farawaytextview.FarAwayTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:destination_latitude="3.3308616"
        app:destination_longitude="101.6408900"
        app:postfix="away"
        app:prefix="just"
        app:unit="mile"
        app:use_current_location_as_source="true"
        tools:text="Hello World!" />
```

Or set information when you need:

```
 farAwayTv.setDestination( 3.101258,101.640195)

 farAwayTv.isUseCurrentLocationAsSource = true

```

When you get LocationPermission for first time, you should notify FarAwayObserver, just do that:
```
FarAwayObserver.getInstance(applicationContext).LocationPermissionGranted(applicationContext)
```

Don't like current strings displayed into FarAwayTextView? just override those strings into your strings.xml:

```
    <string name="meters">%2$s %1$s M %3$s</string>
    <string name="feet">%2$s %1$s feet %3$s</string>
    <string name="mile">%2$s %1$s mile %3$s</string>
    <string name="miles">%2$s %1$s miles %3$s</string>
    <string name="km">%2$s %1$s KM %3$s</string>
    <string name="destination_not_defined">Please define destination</string>
    <string name="at_destination">At destination!</string>
    <string name="location_service_not_available">Source location not defined, make sure gps permission is granted</string>
    <string name="source_not_defined">Source location not defined</string>

```


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

Not yet

## Todo
1. Add instrumentation test


## Author

* **Abdulrahman Babil** - *Senior Application Developer at Strateq Sdn Bhd - Malaysia* - [Portfolio](http://abdul.mega4tech.com)

