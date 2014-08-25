include "StreamStructures.thrift"

namespace java com.kaisquare.stream.thrift
namespace cpp com.kaisquare.stream.thrift

service StreamServerService
{
    /**
     * Get the id of this server.
     * RETURN is the id of the server.
     */
    i64 getServerId(),

    /**
     * Get the details of a stream.
     * (1) streamName - name of the stream.
     * (2) outputType - output type of the stream.
     * RETURN is a struct containing details of the stream.
     */
    StreamStructures.SmStreamInfo getStreamDetails(1: string streamName
                                                   2: string outputType),

    /**
     * Get details of stream's clients.
     * (1) streamName - name of the stream.
     * (2) outputType - output type of the stream.
     * RETURN is a list of clients of the stream.
     */
    list<StreamStructures.SmStreamClientInfo> getClients(1: string streamName
                                                         2: string outputType),

    /**
     * Set server configurations.
     * (1) config - JSON string specifies the configuration.
     * RETURN is true for success and false otherwise.
     */
    bool setConfigurations(1: string config),

    /**
     * Add a stream to the server.
     * (1) streamInfo - details of the stream to be added.
     * RETURN is the url of the output stream. Empty if failed.
     */
    string addStream(1: StreamStructures.SmStreamInfo streamInfo),

    /**
     * Add a playlist stream to the server.
     * (1) streamInfo - details of the stream to be added.
     * (2) sourceList - list of source urls.
     * (3) timeMap - list of time gaps between sources.
     * RETURN is the url of the output stream. Empty if failed.
     */
    string addPlaylist(1: StreamStructures.SmStreamInfo streamInfo,
					 2: list<string> sourceList,
					 3: list<i64> timeMap),

    /**
     * Remove a stream from the server.
     * (1) streamName - name of the stream.
     * (2) outputType - output type of the stream.
     */
    void removeStream(1: string streamName,
					  2: string outputType),
}