include "StreamStructures.thrift"

namespace java com.kaisquare.stream.thrift
namespace cpp com.kaisquare.stream.thrift

service StreamManagementService
{
    /**
     * Get info of all stream servers.
     * RETURN is a list of StreamServerInfo.
     */
    list<StreamStructures.SmStreamServerInfo> getServerPoolInfo(),

    /**
     * Add a stream.
     * (1) deviceId - the device ID.
     * (2) channelId - the channel ID.
     * (3) streamName - Name of the output stream that is appended to the server's url.
     *     If empty, (deviceId, channelId) will be used.
     * (4) outputType - output stream type.
     *     Now supported: rtmp/h264.
     * (5) source - URL of the source stream.
     * (6) sourceType - source stream type.
     *     Now suported: rtsp/h264.
     * RETURN value is the output URL of the stream.
     * Empty if failed.
     */
    string addStream(1: i64 deviceId,
                     2: i32 channelId,
                     3: string streamName,
                     4: string outputType
                     5: string source,
                     6: string sourceType),

    /**
     * Add a playlist stream.
     * (1) deviceId - the device ID.
     * (2) channelId - the channel ID.
     * (3) streamName - Name of the output stream that is appended to the server's url.
     *     If empty, (deviceId, channelId) will be used.
     * (4) outputType - output stream type.
     *     Now supported: rtmp/h264.
     * (5) sourceList - path to the source files.
     * (6) timeMap - list of silent periods between items in playlist (in milliseconds).
     * RETURN value is the output URL of playlist.
     * Empty if failed.
     */
    string addPlaylist(1: i64 deviceId,
                       2: i32 channelId,
                       3: string streamName,
                       4: string outputType,
                       5: list<string> sourceList,
                       6: list<i64> timeMap),

    /**
     * Remove a stream from crtmpserver.
     * (1) deviceId - the device ID.
     * (2) channelId - the channel ID.
     * (3) streamName - Name of the stream.
     *     If empty, (deviceId, channelId) will be used.
     * (4) outputType - output type of the stream.
     * RETURN value is only false if the stream is being locked in database.
     */
    bool removeStream(1: i64 deviceId,
                      2: i32 channel,
                      3: string streamName
                      4: string outputType),

    /**
     * Query the list of clients for a stream.
     * (1) deviceId - the device ID.
     * (2) channelId - the channel ID.
     * (3) streamName - Name of the stream.
     *     If empty, (deviceId, channelId) will be used.
     * (4) outputType - output type of the stream.
     * RETURN value is the list of all clients of the stream.
     */
    list<StreamStructures.SmStreamClientInfo> getClients(
        1: i64 deviceId,
        2: i32 channelId,
        3: string streamName,
        4: string outputType),
}

