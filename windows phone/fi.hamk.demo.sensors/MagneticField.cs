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
using S = Microsoft.Devices.Sensors;

namespace fi.hamk.demo.sensors
{
    public class MagneticField
    {
        public float x, y, z;
        S.Compass compass;

        public void update()
        {
            if (S.Compass.IsSupported)
            {
                if (compass == null)
                {
                    compass = new S.Compass();
                    compass.CurrentValueChanged += new EventHandler<S.SensorReadingEventArgs<S.CompassReading>>(compass_CurrentValueChanged);
                }
                compass.Start();
            }
        }

        void compass_CurrentValueChanged(object sender, S.SensorReadingEventArgs<S.CompassReading> e)
        {
            x = e.SensorReading.MagnetometerReading.X;
            y = e.SensorReading.MagnetometerReading.Y;
            z = e.SensorReading.MagnetometerReading.Z;
            compass.Stop();
        }
    }
}
