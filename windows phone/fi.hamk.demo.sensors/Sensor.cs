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
using System.Threading;
using Newtonsoft.Json;
using System.ComponentModel;

namespace fi.hamk.demo.sensors
{
    public class Sensor
    {
        public Accelerometer accelerometer;
        public Device device;
        public Gyroscope gyroscope;
        public Location location;
        public MagneticField magfield;
        public Network network;
        public int measured;

        public Sensor()
        {
            accelerometer = new Accelerometer();
            device = new Device();
            gyroscope = new Gyroscope();
            location = new Location();
            magfield = new MagneticField();
            network = new Network();
        }

        public void update(Action<Sensor> callback){
            BackgroundWorker backgroundWorker = new BackgroundWorker();
            backgroundWorker.DoWork += (s, e) =>
            {
                e.Result = update();
            };
            backgroundWorker.RunWorkerCompleted += (s, e) =>
            {
                callback.Invoke(e.Result as Sensor);
            };
            backgroundWorker.RunWorkerAsync();
        }

        private Sensor update()
        {
            measured = DateTime.Now.Millisecond;
            accelerometer.update();
            gyroscope.update();
            location.update();
            magfield.update();
            network.update();
            /*WebClient webClient = new WebClient();
            webClient.UploadStringAsync(new Uri("http://demo.01.fi/json"), "POST", JsonConvert.SerializeObject(this));*/
            return this;
        }
    }
}
