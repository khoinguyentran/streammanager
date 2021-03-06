/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.kaisquare.stream.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmStreamInfo implements org.apache.thrift.TBase<SmStreamInfo, SmStreamInfo._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SmStreamInfo");

  private static final org.apache.thrift.protocol.TField STREAM_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("streamName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DEVICE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("deviceId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField CHANNEL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("channelId", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField SERVER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("serverId", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField SOURCE_FIELD_DESC = new org.apache.thrift.protocol.TField("source", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField SOURCE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("sourceType", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField OUTPUT_FIELD_DESC = new org.apache.thrift.protocol.TField("output", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField OUTPUT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("outputType", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SmStreamInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SmStreamInfoTupleSchemeFactory());
  }

  public String streamName; // required
  public long deviceId; // required
  public int channelId; // required
  public long serverId; // required
  public String source; // required
  public String sourceType; // required
  public String output; // required
  public String outputType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STREAM_NAME((short)1, "streamName"),
    DEVICE_ID((short)2, "deviceId"),
    CHANNEL_ID((short)3, "channelId"),
    SERVER_ID((short)4, "serverId"),
    SOURCE((short)5, "source"),
    SOURCE_TYPE((short)6, "sourceType"),
    OUTPUT((short)7, "output"),
    OUTPUT_TYPE((short)8, "outputType");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // STREAM_NAME
          return STREAM_NAME;
        case 2: // DEVICE_ID
          return DEVICE_ID;
        case 3: // CHANNEL_ID
          return CHANNEL_ID;
        case 4: // SERVER_ID
          return SERVER_ID;
        case 5: // SOURCE
          return SOURCE;
        case 6: // SOURCE_TYPE
          return SOURCE_TYPE;
        case 7: // OUTPUT
          return OUTPUT;
        case 8: // OUTPUT_TYPE
          return OUTPUT_TYPE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DEVICEID_ISSET_ID = 0;
  private static final int __CHANNELID_ISSET_ID = 1;
  private static final int __SERVERID_ISSET_ID = 2;
  private BitSet __isset_bit_vector = new BitSet(3);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STREAM_NAME, new org.apache.thrift.meta_data.FieldMetaData("streamName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DEVICE_ID, new org.apache.thrift.meta_data.FieldMetaData("deviceId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CHANNEL_ID, new org.apache.thrift.meta_data.FieldMetaData("channelId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SERVER_ID, new org.apache.thrift.meta_data.FieldMetaData("serverId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SOURCE, new org.apache.thrift.meta_data.FieldMetaData("source", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SOURCE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("sourceType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OUTPUT, new org.apache.thrift.meta_data.FieldMetaData("output", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OUTPUT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("outputType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SmStreamInfo.class, metaDataMap);
  }

  public SmStreamInfo() {
  }

  public SmStreamInfo(
    String streamName,
    long deviceId,
    int channelId,
    long serverId,
    String source,
    String sourceType,
    String output,
    String outputType)
  {
    this();
    this.streamName = streamName;
    this.deviceId = deviceId;
    setDeviceIdIsSet(true);
    this.channelId = channelId;
    setChannelIdIsSet(true);
    this.serverId = serverId;
    setServerIdIsSet(true);
    this.source = source;
    this.sourceType = sourceType;
    this.output = output;
    this.outputType = outputType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SmStreamInfo(SmStreamInfo other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetStreamName()) {
      this.streamName = other.streamName;
    }
    this.deviceId = other.deviceId;
    this.channelId = other.channelId;
    this.serverId = other.serverId;
    if (other.isSetSource()) {
      this.source = other.source;
    }
    if (other.isSetSourceType()) {
      this.sourceType = other.sourceType;
    }
    if (other.isSetOutput()) {
      this.output = other.output;
    }
    if (other.isSetOutputType()) {
      this.outputType = other.outputType;
    }
  }

  public SmStreamInfo deepCopy() {
    return new SmStreamInfo(this);
  }

  @Override
  public void clear() {
    this.streamName = null;
    setDeviceIdIsSet(false);
    this.deviceId = 0;
    setChannelIdIsSet(false);
    this.channelId = 0;
    setServerIdIsSet(false);
    this.serverId = 0;
    this.source = null;
    this.sourceType = null;
    this.output = null;
    this.outputType = null;
  }

  public String getStreamName() {
    return this.streamName;
  }

  public SmStreamInfo setStreamName(String streamName) {
    this.streamName = streamName;
    return this;
  }

  public void unsetStreamName() {
    this.streamName = null;
  }

  /** Returns true if field streamName is set (has been assigned a value) and false otherwise */
  public boolean isSetStreamName() {
    return this.streamName != null;
  }

  public void setStreamNameIsSet(boolean value) {
    if (!value) {
      this.streamName = null;
    }
  }

  public long getDeviceId() {
    return this.deviceId;
  }

  public SmStreamInfo setDeviceId(long deviceId) {
    this.deviceId = deviceId;
    setDeviceIdIsSet(true);
    return this;
  }

  public void unsetDeviceId() {
    __isset_bit_vector.clear(__DEVICEID_ISSET_ID);
  }

  /** Returns true if field deviceId is set (has been assigned a value) and false otherwise */
  public boolean isSetDeviceId() {
    return __isset_bit_vector.get(__DEVICEID_ISSET_ID);
  }

  public void setDeviceIdIsSet(boolean value) {
    __isset_bit_vector.set(__DEVICEID_ISSET_ID, value);
  }

  public int getChannelId() {
    return this.channelId;
  }

  public SmStreamInfo setChannelId(int channelId) {
    this.channelId = channelId;
    setChannelIdIsSet(true);
    return this;
  }

  public void unsetChannelId() {
    __isset_bit_vector.clear(__CHANNELID_ISSET_ID);
  }

  /** Returns true if field channelId is set (has been assigned a value) and false otherwise */
  public boolean isSetChannelId() {
    return __isset_bit_vector.get(__CHANNELID_ISSET_ID);
  }

  public void setChannelIdIsSet(boolean value) {
    __isset_bit_vector.set(__CHANNELID_ISSET_ID, value);
  }

  public long getServerId() {
    return this.serverId;
  }

  public SmStreamInfo setServerId(long serverId) {
    this.serverId = serverId;
    setServerIdIsSet(true);
    return this;
  }

  public void unsetServerId() {
    __isset_bit_vector.clear(__SERVERID_ISSET_ID);
  }

  /** Returns true if field serverId is set (has been assigned a value) and false otherwise */
  public boolean isSetServerId() {
    return __isset_bit_vector.get(__SERVERID_ISSET_ID);
  }

  public void setServerIdIsSet(boolean value) {
    __isset_bit_vector.set(__SERVERID_ISSET_ID, value);
  }

  public String getSource() {
    return this.source;
  }

  public SmStreamInfo setSource(String source) {
    this.source = source;
    return this;
  }

  public void unsetSource() {
    this.source = null;
  }

  /** Returns true if field source is set (has been assigned a value) and false otherwise */
  public boolean isSetSource() {
    return this.source != null;
  }

  public void setSourceIsSet(boolean value) {
    if (!value) {
      this.source = null;
    }
  }

  public String getSourceType() {
    return this.sourceType;
  }

  public SmStreamInfo setSourceType(String sourceType) {
    this.sourceType = sourceType;
    return this;
  }

  public void unsetSourceType() {
    this.sourceType = null;
  }

  /** Returns true if field sourceType is set (has been assigned a value) and false otherwise */
  public boolean isSetSourceType() {
    return this.sourceType != null;
  }

  public void setSourceTypeIsSet(boolean value) {
    if (!value) {
      this.sourceType = null;
    }
  }

  public String getOutput() {
    return this.output;
  }

  public SmStreamInfo setOutput(String output) {
    this.output = output;
    return this;
  }

  public void unsetOutput() {
    this.output = null;
  }

  /** Returns true if field output is set (has been assigned a value) and false otherwise */
  public boolean isSetOutput() {
    return this.output != null;
  }

  public void setOutputIsSet(boolean value) {
    if (!value) {
      this.output = null;
    }
  }

  public String getOutputType() {
    return this.outputType;
  }

  public SmStreamInfo setOutputType(String outputType) {
    this.outputType = outputType;
    return this;
  }

  public void unsetOutputType() {
    this.outputType = null;
  }

  /** Returns true if field outputType is set (has been assigned a value) and false otherwise */
  public boolean isSetOutputType() {
    return this.outputType != null;
  }

  public void setOutputTypeIsSet(boolean value) {
    if (!value) {
      this.outputType = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case STREAM_NAME:
      if (value == null) {
        unsetStreamName();
      } else {
        setStreamName((String)value);
      }
      break;

    case DEVICE_ID:
      if (value == null) {
        unsetDeviceId();
      } else {
        setDeviceId((Long)value);
      }
      break;

    case CHANNEL_ID:
      if (value == null) {
        unsetChannelId();
      } else {
        setChannelId((Integer)value);
      }
      break;

    case SERVER_ID:
      if (value == null) {
        unsetServerId();
      } else {
        setServerId((Long)value);
      }
      break;

    case SOURCE:
      if (value == null) {
        unsetSource();
      } else {
        setSource((String)value);
      }
      break;

    case SOURCE_TYPE:
      if (value == null) {
        unsetSourceType();
      } else {
        setSourceType((String)value);
      }
      break;

    case OUTPUT:
      if (value == null) {
        unsetOutput();
      } else {
        setOutput((String)value);
      }
      break;

    case OUTPUT_TYPE:
      if (value == null) {
        unsetOutputType();
      } else {
        setOutputType((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STREAM_NAME:
      return getStreamName();

    case DEVICE_ID:
      return Long.valueOf(getDeviceId());

    case CHANNEL_ID:
      return Integer.valueOf(getChannelId());

    case SERVER_ID:
      return Long.valueOf(getServerId());

    case SOURCE:
      return getSource();

    case SOURCE_TYPE:
      return getSourceType();

    case OUTPUT:
      return getOutput();

    case OUTPUT_TYPE:
      return getOutputType();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STREAM_NAME:
      return isSetStreamName();
    case DEVICE_ID:
      return isSetDeviceId();
    case CHANNEL_ID:
      return isSetChannelId();
    case SERVER_ID:
      return isSetServerId();
    case SOURCE:
      return isSetSource();
    case SOURCE_TYPE:
      return isSetSourceType();
    case OUTPUT:
      return isSetOutput();
    case OUTPUT_TYPE:
      return isSetOutputType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SmStreamInfo)
      return this.equals((SmStreamInfo)that);
    return false;
  }

  public boolean equals(SmStreamInfo that) {
    if (that == null)
      return false;

    boolean this_present_streamName = true && this.isSetStreamName();
    boolean that_present_streamName = true && that.isSetStreamName();
    if (this_present_streamName || that_present_streamName) {
      if (!(this_present_streamName && that_present_streamName))
        return false;
      if (!this.streamName.equals(that.streamName))
        return false;
    }

    boolean this_present_deviceId = true;
    boolean that_present_deviceId = true;
    if (this_present_deviceId || that_present_deviceId) {
      if (!(this_present_deviceId && that_present_deviceId))
        return false;
      if (this.deviceId != that.deviceId)
        return false;
    }

    boolean this_present_channelId = true;
    boolean that_present_channelId = true;
    if (this_present_channelId || that_present_channelId) {
      if (!(this_present_channelId && that_present_channelId))
        return false;
      if (this.channelId != that.channelId)
        return false;
    }

    boolean this_present_serverId = true;
    boolean that_present_serverId = true;
    if (this_present_serverId || that_present_serverId) {
      if (!(this_present_serverId && that_present_serverId))
        return false;
      if (this.serverId != that.serverId)
        return false;
    }

    boolean this_present_source = true && this.isSetSource();
    boolean that_present_source = true && that.isSetSource();
    if (this_present_source || that_present_source) {
      if (!(this_present_source && that_present_source))
        return false;
      if (!this.source.equals(that.source))
        return false;
    }

    boolean this_present_sourceType = true && this.isSetSourceType();
    boolean that_present_sourceType = true && that.isSetSourceType();
    if (this_present_sourceType || that_present_sourceType) {
      if (!(this_present_sourceType && that_present_sourceType))
        return false;
      if (!this.sourceType.equals(that.sourceType))
        return false;
    }

    boolean this_present_output = true && this.isSetOutput();
    boolean that_present_output = true && that.isSetOutput();
    if (this_present_output || that_present_output) {
      if (!(this_present_output && that_present_output))
        return false;
      if (!this.output.equals(that.output))
        return false;
    }

    boolean this_present_outputType = true && this.isSetOutputType();
    boolean that_present_outputType = true && that.isSetOutputType();
    if (this_present_outputType || that_present_outputType) {
      if (!(this_present_outputType && that_present_outputType))
        return false;
      if (!this.outputType.equals(that.outputType))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SmStreamInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SmStreamInfo typedOther = (SmStreamInfo)other;

    lastComparison = Boolean.valueOf(isSetStreamName()).compareTo(typedOther.isSetStreamName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStreamName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.streamName, typedOther.streamName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDeviceId()).compareTo(typedOther.isSetDeviceId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeviceId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deviceId, typedOther.deviceId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChannelId()).compareTo(typedOther.isSetChannelId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChannelId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.channelId, typedOther.channelId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServerId()).compareTo(typedOther.isSetServerId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServerId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serverId, typedOther.serverId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSource()).compareTo(typedOther.isSetSource());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSource()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.source, typedOther.source);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSourceType()).compareTo(typedOther.isSetSourceType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSourceType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sourceType, typedOther.sourceType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOutput()).compareTo(typedOther.isSetOutput());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOutput()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.output, typedOther.output);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOutputType()).compareTo(typedOther.isSetOutputType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOutputType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.outputType, typedOther.outputType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SmStreamInfo(");
    boolean first = true;

    sb.append("streamName:");
    if (this.streamName == null) {
      sb.append("null");
    } else {
      sb.append(this.streamName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("deviceId:");
    sb.append(this.deviceId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("channelId:");
    sb.append(this.channelId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("serverId:");
    sb.append(this.serverId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("source:");
    if (this.source == null) {
      sb.append("null");
    } else {
      sb.append(this.source);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("sourceType:");
    if (this.sourceType == null) {
      sb.append("null");
    } else {
      sb.append(this.sourceType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("output:");
    if (this.output == null) {
      sb.append("null");
    } else {
      sb.append(this.output);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("outputType:");
    if (this.outputType == null) {
      sb.append("null");
    } else {
      sb.append(this.outputType);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SmStreamInfoStandardSchemeFactory implements SchemeFactory {
    public SmStreamInfoStandardScheme getScheme() {
      return new SmStreamInfoStandardScheme();
    }
  }

  private static class SmStreamInfoStandardScheme extends StandardScheme<SmStreamInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SmStreamInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STREAM_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.streamName = iprot.readString();
              struct.setStreamNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DEVICE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.deviceId = iprot.readI64();
              struct.setDeviceIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CHANNEL_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.channelId = iprot.readI32();
              struct.setChannelIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SERVER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.serverId = iprot.readI64();
              struct.setServerIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SOURCE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.source = iprot.readString();
              struct.setSourceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SOURCE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sourceType = iprot.readString();
              struct.setSourceTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // OUTPUT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.output = iprot.readString();
              struct.setOutputIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // OUTPUT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.outputType = iprot.readString();
              struct.setOutputTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SmStreamInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.streamName != null) {
        oprot.writeFieldBegin(STREAM_NAME_FIELD_DESC);
        oprot.writeString(struct.streamName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DEVICE_ID_FIELD_DESC);
      oprot.writeI64(struct.deviceId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CHANNEL_ID_FIELD_DESC);
      oprot.writeI32(struct.channelId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SERVER_ID_FIELD_DESC);
      oprot.writeI64(struct.serverId);
      oprot.writeFieldEnd();
      if (struct.source != null) {
        oprot.writeFieldBegin(SOURCE_FIELD_DESC);
        oprot.writeString(struct.source);
        oprot.writeFieldEnd();
      }
      if (struct.sourceType != null) {
        oprot.writeFieldBegin(SOURCE_TYPE_FIELD_DESC);
        oprot.writeString(struct.sourceType);
        oprot.writeFieldEnd();
      }
      if (struct.output != null) {
        oprot.writeFieldBegin(OUTPUT_FIELD_DESC);
        oprot.writeString(struct.output);
        oprot.writeFieldEnd();
      }
      if (struct.outputType != null) {
        oprot.writeFieldBegin(OUTPUT_TYPE_FIELD_DESC);
        oprot.writeString(struct.outputType);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SmStreamInfoTupleSchemeFactory implements SchemeFactory {
    public SmStreamInfoTupleScheme getScheme() {
      return new SmStreamInfoTupleScheme();
    }
  }

  private static class SmStreamInfoTupleScheme extends TupleScheme<SmStreamInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SmStreamInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStreamName()) {
        optionals.set(0);
      }
      if (struct.isSetDeviceId()) {
        optionals.set(1);
      }
      if (struct.isSetChannelId()) {
        optionals.set(2);
      }
      if (struct.isSetServerId()) {
        optionals.set(3);
      }
      if (struct.isSetSource()) {
        optionals.set(4);
      }
      if (struct.isSetSourceType()) {
        optionals.set(5);
      }
      if (struct.isSetOutput()) {
        optionals.set(6);
      }
      if (struct.isSetOutputType()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetStreamName()) {
        oprot.writeString(struct.streamName);
      }
      if (struct.isSetDeviceId()) {
        oprot.writeI64(struct.deviceId);
      }
      if (struct.isSetChannelId()) {
        oprot.writeI32(struct.channelId);
      }
      if (struct.isSetServerId()) {
        oprot.writeI64(struct.serverId);
      }
      if (struct.isSetSource()) {
        oprot.writeString(struct.source);
      }
      if (struct.isSetSourceType()) {
        oprot.writeString(struct.sourceType);
      }
      if (struct.isSetOutput()) {
        oprot.writeString(struct.output);
      }
      if (struct.isSetOutputType()) {
        oprot.writeString(struct.outputType);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SmStreamInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.streamName = iprot.readString();
        struct.setStreamNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.deviceId = iprot.readI64();
        struct.setDeviceIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.channelId = iprot.readI32();
        struct.setChannelIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.serverId = iprot.readI64();
        struct.setServerIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.source = iprot.readString();
        struct.setSourceIsSet(true);
      }
      if (incoming.get(5)) {
        struct.sourceType = iprot.readString();
        struct.setSourceTypeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.output = iprot.readString();
        struct.setOutputIsSet(true);
      }
      if (incoming.get(7)) {
        struct.outputType = iprot.readString();
        struct.setOutputTypeIsSet(true);
      }
    }
  }

}

