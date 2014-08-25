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

public class SmPortInfo implements org.apache.thrift.TBase<SmPortInfo, SmPortInfo._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SmPortInfo");

  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PROTOCOL_FIELD_DESC = new org.apache.thrift.protocol.TField("protocol", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SmPortInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SmPortInfoTupleSchemeFactory());
  }

  public String port; // required
  public String protocol; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PORT((short)1, "port"),
    PROTOCOL((short)2, "protocol");

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
        case 1: // PORT
          return PORT;
        case 2: // PROTOCOL
          return PROTOCOL;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROTOCOL, new org.apache.thrift.meta_data.FieldMetaData("protocol", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SmPortInfo.class, metaDataMap);
  }

  public SmPortInfo() {
  }

  public SmPortInfo(
    String port,
    String protocol)
  {
    this();
    this.port = port;
    this.protocol = protocol;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SmPortInfo(SmPortInfo other) {
    if (other.isSetPort()) {
      this.port = other.port;
    }
    if (other.isSetProtocol()) {
      this.protocol = other.protocol;
    }
  }

  public SmPortInfo deepCopy() {
    return new SmPortInfo(this);
  }

  @Override
  public void clear() {
    this.port = null;
    this.protocol = null;
  }

  public String getPort() {
    return this.port;
  }

  public SmPortInfo setPort(String port) {
    this.port = port;
    return this;
  }

  public void unsetPort() {
    this.port = null;
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean isSetPort() {
    return this.port != null;
  }

  public void setPortIsSet(boolean value) {
    if (!value) {
      this.port = null;
    }
  }

  public String getProtocol() {
    return this.protocol;
  }

  public SmPortInfo setProtocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

  public void unsetProtocol() {
    this.protocol = null;
  }

  /** Returns true if field protocol is set (has been assigned a value) and false otherwise */
  public boolean isSetProtocol() {
    return this.protocol != null;
  }

  public void setProtocolIsSet(boolean value) {
    if (!value) {
      this.protocol = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PORT:
      if (value == null) {
        unsetPort();
      } else {
        setPort((String)value);
      }
      break;

    case PROTOCOL:
      if (value == null) {
        unsetProtocol();
      } else {
        setProtocol((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PORT:
      return getPort();

    case PROTOCOL:
      return getProtocol();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PORT:
      return isSetPort();
    case PROTOCOL:
      return isSetProtocol();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SmPortInfo)
      return this.equals((SmPortInfo)that);
    return false;
  }

  public boolean equals(SmPortInfo that) {
    if (that == null)
      return false;

    boolean this_present_port = true && this.isSetPort();
    boolean that_present_port = true && that.isSetPort();
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (!this.port.equals(that.port))
        return false;
    }

    boolean this_present_protocol = true && this.isSetProtocol();
    boolean that_present_protocol = true && that.isSetProtocol();
    if (this_present_protocol || that_present_protocol) {
      if (!(this_present_protocol && that_present_protocol))
        return false;
      if (!this.protocol.equals(that.protocol))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SmPortInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SmPortInfo typedOther = (SmPortInfo)other;

    lastComparison = Boolean.valueOf(isSetPort()).compareTo(typedOther.isSetPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, typedOther.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProtocol()).compareTo(typedOther.isSetProtocol());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProtocol()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.protocol, typedOther.protocol);
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
    StringBuilder sb = new StringBuilder("SmPortInfo(");
    boolean first = true;

    sb.append("port:");
    if (this.port == null) {
      sb.append("null");
    } else {
      sb.append(this.port);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("protocol:");
    if (this.protocol == null) {
      sb.append("null");
    } else {
      sb.append(this.protocol);
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SmPortInfoStandardSchemeFactory implements SchemeFactory {
    public SmPortInfoStandardScheme getScheme() {
      return new SmPortInfoStandardScheme();
    }
  }

  private static class SmPortInfoStandardScheme extends StandardScheme<SmPortInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SmPortInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.port = iprot.readString();
              struct.setPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROTOCOL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.protocol = iprot.readString();
              struct.setProtocolIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SmPortInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.port != null) {
        oprot.writeFieldBegin(PORT_FIELD_DESC);
        oprot.writeString(struct.port);
        oprot.writeFieldEnd();
      }
      if (struct.protocol != null) {
        oprot.writeFieldBegin(PROTOCOL_FIELD_DESC);
        oprot.writeString(struct.protocol);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SmPortInfoTupleSchemeFactory implements SchemeFactory {
    public SmPortInfoTupleScheme getScheme() {
      return new SmPortInfoTupleScheme();
    }
  }

  private static class SmPortInfoTupleScheme extends TupleScheme<SmPortInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SmPortInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPort()) {
        optionals.set(0);
      }
      if (struct.isSetProtocol()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetPort()) {
        oprot.writeString(struct.port);
      }
      if (struct.isSetProtocol()) {
        oprot.writeString(struct.protocol);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SmPortInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.port = iprot.readString();
        struct.setPortIsSet(true);
      }
      if (incoming.get(1)) {
        struct.protocol = iprot.readString();
        struct.setProtocolIsSet(true);
      }
    }
  }

}

