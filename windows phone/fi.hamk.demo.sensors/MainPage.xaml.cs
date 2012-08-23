using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Newtonsoft.Json;

namespace fi.hamk.demo.sensors
{
    public partial class MainPage : PhoneApplicationPage
    {
        Sensor sensor;
        public MainPage()
        {
            InitializeComponent();
            sensor = new Sensor();
        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            (ApplicationBar.Buttons[0] as ApplicationBarIconButton).IsEnabled = false;
            Loaded += (s, ex) => sensor.update(callback);
        }

        void callback(Sensor sensor)
        {
            //MessageBox.Show(JsonConvert.SerializeObject(sensor));
            (ApplicationBar.Buttons[0] as ApplicationBarIconButton).IsEnabled = true;
            accelerometerx.Text = sensor.accelerometer.x.ToString();
            accelerometery.Text = sensor.accelerometer.y.ToString();
            accelerometerz.Text = sensor.accelerometer.z.ToString();
            manufacturer.Text = sensor.device.manufacturer;
            model.Text = sensor.device.model;
            version.Text = sensor.device.version;
            gyroscopex.Text = sensor.gyroscope.x.ToString();
            gyroscopey.Text = sensor.gyroscope.y.ToString();
            gyroscopez.Text = sensor.gyroscope.z.ToString();
            altitude.Text = sensor.location.altitude.ToString();
            latitude.Text = sensor.location.latitude.ToString();
            longitude.Text = sensor.location.longitude.ToString();
            speed.Text = sensor.location.speed.ToString();
            magnetometerx.Text = sensor.magfield.x.ToString();
            magnetometery.Text = sensor.magfield.y.ToString();
            magnetometerz.Text = sensor.magfield.z.ToString();
            roaming.Text = sensor.network.roaming.ToString();
            technology.Text = sensor.network.technology;
            telco.Text = sensor.network.telco;
        }
    }
}