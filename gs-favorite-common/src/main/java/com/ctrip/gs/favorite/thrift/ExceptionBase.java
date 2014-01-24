package com.ctrip.gs.favorite.thrift;

import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec3;

public class ExceptionBase extends Exception implements ThriftStruct {
  private static final TStruct STRUCT = new TStruct("ExceptionBase");
  private static final TField WhatField = new TField("what", TType.I32, (short) 1);
  final int what;
  private static final TField WhyField = new TField("why", TType.STRING, (short) 2);
  final String why;

  public static class Builder {
    private int _what = 0;
    private Boolean _got_what = false;

    public Builder what(int value) {
      this._what = value;
      this._got_what = true;
      return this;
    }
    private String _why = null;
    private Boolean _got_why = false;

    public Builder why(String value) {
      this._why = value;
      this._got_why = true;
      return this;
    }

    public ExceptionBase build() {
      return new ExceptionBase(
        this._what,
        this._why    );
    }
  }

  public Builder copy() {
    Builder builder = new Builder();
    builder.what(this.what);
    builder.why(this.why);
    return builder;
  }

  public static ThriftStructCodec3<ExceptionBase> CODEC = new ThriftStructCodec3<ExceptionBase>() {
    public ExceptionBase decode(TProtocol _iprot) throws org.apache.thrift.TException {
      Builder builder = new Builder();
      int what = 0;
      String why = null;
      Boolean _done = false;
      _iprot.readStructBegin();
      while (!_done) {
        TField _field = _iprot.readFieldBegin();
        if (_field.type == TType.STOP) {
          _done = true;
        } else {
          switch (_field.id) {
            case 1: /* what */
              switch (_field.type) {
                case TType.I32:
                  Integer what_item;
                  what_item = _iprot.readI32();
                  what = what_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.what(what);
              break;
            case 2: /* why */
              switch (_field.type) {
                case TType.STRING:
                  String why_item;
                  why_item = _iprot.readString();
                  why = why_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.why(why);
              break;
            default:
              TProtocolUtil.skip(_iprot, _field.type);
          }
          _iprot.readFieldEnd();
        }
      }
      _iprot.readStructEnd();
      try {
        return builder.build();
      } catch (IllegalStateException stateEx) {
        throw new TProtocolException(stateEx.getMessage());
      }
    }

    public void encode(ExceptionBase struct, TProtocol oprot) throws org.apache.thrift.TException {
      struct.write(oprot);
    }
  };

  public static ExceptionBase decode(TProtocol _iprot) throws org.apache.thrift.TException {
    return CODEC.decode(_iprot);
  }

  public static void encode(ExceptionBase struct, TProtocol oprot) throws org.apache.thrift.TException {
    CODEC.encode(struct, oprot);
  }

  public ExceptionBase(
  int what,
  String why
  ) {
    this.what = what;
    this.why = why;
  }

  public int getWhat() {
    return this.what;
  }
  public String getWhy() {
    return this.why;
  }

  public void write(TProtocol _oprot) throws org.apache.thrift.TException {
    validate();
    _oprot.writeStructBegin(STRUCT);
      _oprot.writeFieldBegin(WhatField);
      Integer what_item = what;
      _oprot.writeI32(what_item);
      _oprot.writeFieldEnd();
      _oprot.writeFieldBegin(WhyField);
      String why_item = why;
      _oprot.writeString(why_item);
      _oprot.writeFieldEnd();
    _oprot.writeFieldStop();
    _oprot.writeStructEnd();
  }

  private void validate() throws org.apache.thrift.protocol.TProtocolException {
  }

  public boolean equals(Object other) {
    if (!(other instanceof ExceptionBase)) return false;
    ExceptionBase that = (ExceptionBase) other;
    return
      this.what == that.what &&

this.why.equals(that.why)
;
  }

  public String toString() {
    return "ExceptionBase(" + this.what + "," + this.why + ")";
  }

  public int hashCode() {
    int hash = 1;
    hash = hash * new Integer(this.what).hashCode();
    hash = hash * (this.why == null ? 0 : this.why.hashCode());
    return hash;
  }
}