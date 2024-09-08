# PC-Simulator-Save-Editor-Server
A server software to decrypt PC Simulator saves. Deploy to Google Cloud, AWS, Azure.

# Requirements

| Type    | Minimum                                               | Recommended |
|---------|-------------------------------------------------------|-------------|
| CPU     | 32 bit single core 1GHz processor (all architectures) |             |
| RAM     | 512 MB                                                | 16GB        |
| Network | At least 10Mbps                                       | 100Mbps     |

When buying a server for this purpose, you should prioritize more on the RAM.
# Using

- Create a socket using port 60537:
```java
Socket socket = new Socket();
socket.connect(new InetSocketAddress("[your ip address]", 60537), 1000);
```

- Send the payload
```java
DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
dataOut.writeUTF("[your payload]");
```

- Use the processed payload
```java
DataInputStream dataIn = new DataInputStream(socket.getInputStream());
String serverMessage = dataIn.readUTF();
```