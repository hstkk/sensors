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
using Microsoft.Xna.Framework;

namespace fi.hamk.demo.sensors
{
    public class Accelerometer
    {
        public float x, y, z;
        S.Accelerometer accelerometer;

        public void update()
        {
            if (S.Accelerometer.IsSupported)
            {
                if (accelerometer == null)
                {
                    accelerometer = new S.Accelerometer();
                    accelerometer.CurrentValueChanged += new EventHandler<S.SensorReadingEventArgs<S.AccelerometerReading>>(accelerometer_CurrentValueChanged);
                }
                accelerometer.Start();
            }
        }

        void accelerometer_CurrentValueChanged(object sender, S.SensorReadingEventArgs<S.AccelerometerReading> e)
        {
            Vector3 vector3 = e.SensorReading.Acceleration;
            x = vector3.X;
            y = vector3.Y;
            z = vector3.Z;
            accelerometer.Stop();
        }
    }
}
