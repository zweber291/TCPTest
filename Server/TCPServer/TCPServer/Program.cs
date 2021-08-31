using System;
using System.Text;
using System.Net.Sockets;
using System.Net;

namespace RemoteVolume {
    class Program {
        static void Main(string[] args) {
            IPEndPoint endPoint = new IPEndPoint(IPAddress.Parse("localhost"), 1234);
            TcpListener listener = new TcpListener(endPoint);
            listener.Start();
            Console.WriteLine("Server started on {0}:{1}", endPoint.Address, endPoint.Port);
            while (true) {
                TcpClient client = listener.AcceptTcpClient();
                Console.WriteLine("Client connected");
                NetworkStream nStream = client.GetStream();
                try {
                    byte[] bytes = new byte[1024];
                    int bytesRead = nStream.Read(bytes, 0, bytes.Length);
                    Console.WriteLine(Encoding.ASCII.GetString(bytes, 0, bytesRead));
                }
                catch (Exception e) {
                    Console.WriteLine(e);
                }
            }
        }
    }
}
