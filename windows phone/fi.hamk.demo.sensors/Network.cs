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
using Microsoft.Phone.Net.NetworkInformation;
using Newtonsoft.Json;

namespace fi.hamk.demo.sensors
{
    public class Network
    {
        [JsonProperty("operator")]
        public string telco;
        public string technology;
        public bool roaming;

        public void update()
        {
            telco = DeviceNetworkInformation.CellularMobileOperator;
            roaming = DeviceNetworkInformation.IsCellularDataRoamingEnabled;
            technology = NetworkInterface.NetworkInterfaceType.ToString();
        }
    }
}
