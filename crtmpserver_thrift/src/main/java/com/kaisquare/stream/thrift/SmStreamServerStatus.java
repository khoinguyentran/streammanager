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

public class SmStreamServerStatus implements org.apache.thrift.TBase<SmStreamServerStatus, SmStreamServerStatus._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SmStreamServerStatus");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ONLINE_FIELD_DESC = new org.apache.thrift.protocol.TField("online", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField LOAD_AVERAGE15MINS_FIELD_DESC = new org.apache.thrift.protocol.TField("loadAverage15mins", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField INSTREAM_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("instreamCount", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField OUTSTREAM_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("outstreamCount", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SmStreamServerStatusStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SmStreamServerStatusTupleSchemeFactory());
  }

  public long id; // required
  public boolean online; // required
  public double loadAverage15mins; // required
  public int instreamCount; // required
  public int outstreamCount; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    ONLINE((short)2, "online"),
    LOAD_AVERAGE15MINS((short)3, "loadAverage15mins"),
    INSTREAM_COUNT((short)4, "instreamCount"),
    OUTSTREAM_COUNT((short)5, "outstreamCount");

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
        case 1: // ID
          return ID;
        case 2: // ONLINE
          return ONLINE;
        case 3: // LOAD_AVERAGE15MINS
          return LOAD_AVERAGE15MINS;
        case 4: // INSTREAM_COUNT
          return INSTREAM_COUNT;
        case 5: // OUTSTREAM_COUNT
          return OUTSTREAM_COUNT;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __ONLINE_ISSET_ID = 1;
  private static final int __LOADAVERAGE15MINS_ISSET_ID = 2;
  private static final int __INSTREAMCOUNT_ISSET_ID = 3;
  private static final int __OUTSTREAMCOUNT_ISSET_ID = 4;
  private BitSet __isset_bit_vector = new BitSet(5);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ONLINE, new org.apache.thrift.meta_data.FieldMetaData("online", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.LOAD_AVERAGE15MINS, new org.apache.thrift.meta_data.FieldMetaData("loadAverage15mins", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.INSTREAM_COUNT, new org.apache.thrift.meta_data.FieldMetaData("instreamCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.OUTSTREAM_COUNT, new org.apache.thrift.meta_data.FieldMetaData("outstreamCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SmStreamServerStatus.class, metaDataMap);
  }

  public SmStreamServerStatus() {
  }

  public SmStreamServerStatus(
    long id,
    boolean online,
    double loadAverage15mins,
    int instreamCount,
    int outstreamCount)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.online = online;
    setOnlineIsSet(true);
    this.loadAverage15mins = loadAverage15mins;
    setLoadAverage15minsIsSet(true);
    this.instreamCount = instreamCount;
    setInstreamCountIsSet(true);
    this.outstreamCount = outstreamCount;
    setOutstreamCountIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SmStreamServerStatus(SmStreamServerStatus other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.id = other.id;
    this.online = other.online;
    this.loadAverage15mins = other.loadAverage15mins;
    this.instreamCount = other.instreamCount;
    this.outstreamCount = other.outstreamCount;
  }

  public SmStreamServerStatus deepCopy() {
    return new SmStreamServerStatus(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setOnlineIsSet(false);
    this.online = false;
    setLoadAverage15minsIsSet(false);
    this.loadAverage15mins = 0.0;
    setInstreamCountIsSet(false);
    this.instreamCount = 0;
    setOutstreamCountIsSet(false);
    this.outstreamCount = 0;
  }

  public long getId() {
    return this.id;
  }

  public SmStreamServerStatus setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bit_vector.clear(__ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return __isset_bit_vector.get(__ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bit_vector.set(__ID_ISSET_ID, value);
  }

  public boolean isOnline() {
    return this.online;
  }

  public SmStreamServerStatus setOnline(boolean online) {
    this.online = online;
    setOnlineIsSet(true);
    return this;
  }

  public void unsetOnline() {
    __isset_bit_vector.clear(__ONLINE_ISSET_ID);
  }

  /** Returns true if field online is set (has been assigned a value) and false otherwise */
  public boolean isSetOnline() {
    return __isset_bit_vector.get(__ONLINE_ISSET_ID);
  }

  public void setOnlineIsSet(boolean value) {
    __isset_bit_vector.set(__ONLINE_ISSET_ID, value);
  }

  public double getLoadAverage15mins() {
    return this.loadAverage15mins;
  }

  public SmStreamServerStatus setLoadAverage15mins(double loadAverage15mins) {
    this.loadAverage15mins = loadAverage15mins;
    setLoadAverage15minsIsSet(true);
    return this;
  }

  public void unsetLoadAverage15mins() {
    __isset_bit_vector.clear(__LOADAVERAGE15MINS_ISSET_ID);
  }

  /** Returns true if field loadAverage15mins is set (has been assigned a value) and false otherwise */
  public boolean isSetLoadAverage15mins() {
    return __isset_bit_vector.get(__LOADAVERAGE15MINS_ISSET_ID);
  }

  public void setLoadAverage15minsIsSet(boolean value) {
    __isset_bit_vector.set(__LOADAVERAGE15MINS_ISSET_ID, value);
  }

  public int getInstreamCount() {
    return this.instreamCount;
  }

  public SmStreamServerStatus setInstreamCount(int instreamCount) {
    this.instreamCount = instreamCount;
    setInstreamCountIsSet(true);
    return this;
  }

  public void unsetInstreamCount() {
    __isset_bit_vector.clear(__INSTREAMCOUNT_ISSET_ID);
  }

  /** Returns true if field instreamCount is set (has been assigned a value) and false otherwise */
  public boolean isSetInstreamCount() {
    return __isset_bit_vector.get(__INSTREAMCOUNT_ISSET_ID);
  }

  public void setInstreamCountIsSet(boolean value) {
    __isset_bit_vector.set(__INSTREAMCOUNT_ISSET_ID, value);
  }

  public int getOutstreamCount() {
    return this.outstreamCount;
  }

  public SmStreamServerStatus setOutstreamCount(int outstreamCount) {
    this.outstreamCount = outstreamCount;
    setOutstreamCountIsSet(true);
    return this;
  }

  public void unsetOutstreamCount() {
    __isset_bit_vector.clear(__OUTSTREAMCOUNT_ISSET_ID);
  }

  /** Returns true if field outstreamCount is set (has been assigned a value) and false otherwise */
  public boolean isSetOutstreamCount() {
    return __isset_bit_vector.get(__OUTSTREAMCOUNT_ISSET_ID);
  }

  public void setOutstreamCountIsSet(boolean value) {
    __isset_bit_vector.set(__OUTSTREAMCOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case ONLINE:
      if (value == null) {
        unsetOnline();
      } else {
        setOnline((Boolean)value);
      }
      break;

    case LOAD_AVERAGE15MINS:
      if (value == null) {
        unsetLoadAverage15mins();
      } else {
        setLoadAverage15mins((Double)value);
      }
      break;

    case INSTREAM_COUNT:
      if (value == null) {
        unsetInstreamCount();
      } else {
        setInstreamCount((Integer)value);
      }
      break;

    case OUTSTREAM_COUNT:
      if (value == null) {
        unsetOutstreamCount();
      } else {
        setOutstreamCount((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Long.valueOf(getId());

    case ONLINE:
      return Boolean.valueOf(isOnline());

    case LOAD_AVERAGE15MINS:
      return Double.valueOf(getLoadAverage15mins());

    case INSTREAM_COUNT:
      return Integer.valueOf(getInstreamCount());

    case OUTSTREAM_COUNT:
      return Integer.valueOf(getOutstreamCount());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case ONLINE:
      return isSetOnline();
    case LOAD_AVERAGE15MINS:
      return isSetLoadAverage15mins();
    case INSTREAM_COUNT:
      return isSetInstreamCount();
    case OUTSTREAM_COUNT:
      return isSetOutstreamCount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SmStreamServerStatus)
      return this.equals((SmStreamServerStatus)that);
    return false;
  }

  public boolean equals(SmStreamServerStatus that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_online = true;
    boolean that_present_online = true;
    if (this_present_online || that_present_online) {
      if (!(this_present_online && that_present_online))
        return false;
      if (this.online != that.online)
        return false;
    }

    boolean this_present_loadAverage15mins = true;
    boolean that_present_loadAverage15mins = true;
    if (this_present_loadAverage15mins || that_present_loadAverage15mins) {
      if (!(this_present_loadAverage15mins && that_present_loadAverage15mins))
        return false;
      if (this.loadAverage15mins != that.loadAverage15mins)
        return false;
    }

    boolean this_present_instreamCount = true;
    boolean that_present_instreamCount = true;
    if (this_present_instreamCount || that_present_instreamCount) {
      if (!(this_present_instreamCount && that_present_instreamCount))
        return false;
      if (this.instreamCount != that.instreamCount)
        return false;
    }

    boolean this_present_outstreamCount = true;
    boolean that_present_outstreamCount = true;
    if (this_present_outstreamCount || that_present_outstreamCount) {
      if (!(this_present_outstreamCount && that_present_outstreamCount))
        return false;
      if (this.outstreamCount != that.outstreamCount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SmStreamServerStatus other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SmStreamServerStatus typedOther = (SmStreamServerStatus)other;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(typedOther.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOnline()).compareTo(typedOther.isSetOnline());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOnline()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.online, typedOther.online);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLoadAverage15mins()).compareTo(typedOther.isSetLoadAverage15mins());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoadAverage15mins()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loadAverage15mins, typedOther.loadAverage15mins);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInstreamCount()).compareTo(typedOther.isSetInstreamCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInstreamCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.instreamCount, typedOther.instreamCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOutstreamCount()).compareTo(typedOther.isSetOutstreamCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOutstreamCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.outstreamCount, typedOther.outstreamCount);
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
    StringBuilder sb = new StringBuilder("SmStreamServerStatus(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("online:");
    sb.append(this.online);
    first = false;
    if (!first) sb.append(", ");
    sb.append("loadAverage15mins:");
    sb.append(this.loadAverage15mins);
    first = false;
    if (!first) sb.append(", ");
    sb.append("instreamCount:");
    sb.append(this.instreamCount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("outstreamCount:");
    sb.append(this.outstreamCount);
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

  private static class SmStreamServerStatusStandardSchemeFactory implements SchemeFactory {
    public SmStreamServerStatusStandardScheme getScheme() {
      return new SmStreamServerStatusStandardScheme();
    }
  }

  private static class SmStreamServerStatusStandardScheme extends StandardScheme<SmStreamServerStatus> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SmStreamServerStatus struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ONLINE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.online = iprot.readBool();
              struct.setOnlineIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOAD_AVERAGE15MINS
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.loadAverage15mins = iprot.readDouble();
              struct.setLoadAverage15minsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // INSTREAM_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.instreamCount = iprot.readI32();
              struct.setInstreamCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // OUTSTREAM_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.outstreamCount = iprot.readI32();
              struct.setOutstreamCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SmStreamServerStatus struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ONLINE_FIELD_DESC);
      oprot.writeBool(struct.online);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOAD_AVERAGE15MINS_FIELD_DESC);
      oprot.writeDouble(struct.loadAverage15mins);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(INSTREAM_COUNT_FIELD_DESC);
      oprot.writeI32(struct.instreamCount);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(OUTSTREAM_COUNT_FIELD_DESC);
      oprot.writeI32(struct.outstreamCount);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SmStreamServerStatusTupleSchemeFactory implements SchemeFactory {
    public SmStreamServerStatusTupleScheme getScheme() {
      return new SmStreamServerStatusTupleScheme();
    }
  }

  private static class SmStreamServerStatusTupleScheme extends TupleScheme<SmStreamServerStatus> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SmStreamServerStatus struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetOnline()) {
        optionals.set(1);
      }
      if (struct.isSetLoadAverage15mins()) {
        optionals.set(2);
      }
      if (struct.isSetInstreamCount()) {
        optionals.set(3);
      }
      if (struct.isSetOutstreamCount()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetOnline()) {
        oprot.writeBool(struct.online);
      }
      if (struct.isSetLoadAverage15mins()) {
        oprot.writeDouble(struct.loadAverage15mins);
      }
      if (struct.isSetInstreamCount()) {
        oprot.writeI32(struct.instreamCount);
      }
      if (struct.isSetOutstreamCount()) {
        oprot.writeI32(struct.outstreamCount);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SmStreamServerStatus struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.online = iprot.readBool();
        struct.setOnlineIsSet(true);
      }
      if (incoming.get(2)) {
        struct.loadAverage15mins = iprot.readDouble();
        struct.setLoadAverage15minsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.instreamCount = iprot.readI32();
        struct.setInstreamCountIsSet(true);
      }
      if (incoming.get(4)) {
        struct.outstreamCount = iprot.readI32();
        struct.setOutstreamCountIsSet(true);
      }
    }
  }

}

