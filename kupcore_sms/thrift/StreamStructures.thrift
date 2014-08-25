namespace java com.kaisquare.stream.thrift
namespace cpp com.kaisquare.stream.thrift

struct SmPortInfo
{
    1: string port,
    2: string protocol,
}

struct SmStreamClientInfo
{
    1: string ip,
    2: list<SmPortInfo> ports,
}

struct SmStreamInfo
{
	1: string streamName,
	2: i64 deviceId,
	3: i32 channelId,
	4: i64 serverId,
	5: string source,
	6: string sourceType,
	7: string output,
	8: string outputType,
}

struct SmStreamServerInfo
{
	1: i64 id,
	2: string type,
	3: string ip,
	4: list<SmPortInfo> ports,
}

struct SmStreamServerStatus
{
	1: i64 id,
	2: bool online,
	3: double loadAverage15mins,
	4: i32 instreamCount,
	5: i32 outstreamCount,
}