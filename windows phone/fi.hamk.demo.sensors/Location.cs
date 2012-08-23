using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Device.Location;

namespace fi.hamk.demo.sensors
{
    public class Location
    {
        GeoCoordinateWatcher geoCoordinateWatcher;
        public double altitude, latitude, longitude, speed;

        public void update()
        {
            if (geoCoordinateWatcher == null) {
                geoCoordinateWatcher = new GeoCoordinateWatcher(GeoPositionAccuracy.Default);
                geoCoordinateWatcher.PositionChanged += new EventHandler<GeoPositionChangedEventArgs<GeoCoordinate>>(geoCoordinateWatcher_PositionChanged);
            }
            geoCoordinateWatcher.Start();
        }

        void geoCoordinateWatcher_PositionChanged(object sender, GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
            altitude = e.Position.Location.Altitude;
            latitude = e.Position.Location.Latitude;
            longitude = e.Position.Location.Longitude;
            speed = e.Position.Location.Speed;
            geoCoordinateWatcher.Stop();
        }
    }
}
