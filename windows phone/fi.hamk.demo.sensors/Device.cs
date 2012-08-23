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
using Microsoft.Phone.Info;

namespace fi.hamk.demo.sensors
{
    public class Device
    {
        public string manufacturer, version, model;

        public Device()
        {
            get();
        }

        public void get()
        {
            manufacturer = DeviceStatus.DeviceManufacturer;
            version = Environment.OSVersion.Version.ToString();
            model = DeviceStatus.DeviceName;
        }
    }
}
