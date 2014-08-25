include "StreamStructures.thrift"

namespace java com.kaisquare.stream.thrift
namespace cpp com.kaisquare.stream.thrift

service StreamServerRegisterService
{
    /**
     * Register a streaming server to StreamManagementService.
     * (1) serverInfo - struct specifying server's details.
     * RETURN value is true for success and false otherwise.
     */
    bool registerStreamingServer(1: StreamStructures.SmStreamServerInfo serverInfo),

    /**
     * Deregister a streaming server from StreamManagementService.
     * (1) serverId - streaming server ID.
     */
    void deregisterStreamingServer(1: i64 serverId),

    /**
     * Update current server status.
     * (1) status - structure contains server's status (cpu, memory, number of streams etc).
     * RETURN value is true for success and false otherwise.
     */
    bool updateStreamingServerStatus(1: StreamStructures.SmStreamServerStatus status)
}