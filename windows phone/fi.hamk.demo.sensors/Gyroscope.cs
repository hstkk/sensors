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
    public class Gyroscope
    {
        public float x, y, z;
        S.Gyroscope gyroscope;

        public void update()
        {
            if (S.Gyroscope.IsSupported)
            {
                if (gyroscope == null)
                {
                    gyroscope = new S.Gyroscope();
                    gyroscope.CurrentValueChanged += new EventHandler<S.SensorReadingEventArgs<S.GyroscopeReading>>(gyroscope_CurrentValueChanged);
                }
                gyroscope.Start();
            }
        }

        void gyroscope_CurrentValueChanged(object sender, S.SensorReadingEventArgs<S.GyroscopeReading> e)
        {
            x = e.SensorReading.RotationRate.X;
            y = e.SensorReading.RotationRate.Y;
            z = e.SensorReading.RotationRate.Z;
            gyroscope.Stop();
        }
    }
}
