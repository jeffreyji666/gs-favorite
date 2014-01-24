package com.ctrip.gs.favorite.thrift;

import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

import com.twitter.scrooge.Option;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec3;

public class Favorite implements ThriftStruct {
  private static final TStruct STRUCT = new TStruct("Favorite");
  private static final TField UidField = new TField("uid", TType.I64, (short) 1);
  final long uid;
  private static final TField FavoriteTypeField = new TField("favoriteType", TType.I32, (short) 2);
  final int favoriteType;
  private static final TField ResourceIdField = new TField("resourceId", TType.I32, (short) 3);
  final int resourceId;
  private static final TField DistrictIdField = new TField("districtId", TType.I32, (short) 4);
  final Option<Integer> districtId;

  public static class Builder {
    private long _uid = (long) 0;
    private Boolean _got_uid = false;

    public Builder uid(long value) {
      this._uid = value;
      this._got_uid = true;
      return this;
    }
    private int _favoriteType = 0;
    private Boolean _got_favoriteType = false;

    public Builder favoriteType(int value) {
      this._favoriteType = value;
      this._got_favoriteType = true;
      return this;
    }
    private int _resourceId = 0;
    private Boolean _got_resourceId = false;

    public Builder resourceId(int value) {
      this._resourceId = value;
      this._got_resourceId = true;
      return this;
    }
    private int _districtId = 0;
    private Boolean _got_districtId = false;

    public Builder districtId(int value) {
      this._districtId = value;
      this._got_districtId = true;
      return this;
    }

    public Favorite build() {
      return new Favorite(
        this._uid,
        this._favoriteType,
        this._resourceId,
      Option.make(this._got_districtId, this._districtId)    );
    }
  }

  public Builder copy() {
    Builder builder = new Builder();
    builder.uid(this.uid);
    builder.favoriteType(this.favoriteType);
    builder.resourceId(this.resourceId);
    if (this.districtId.isDefined()) builder.districtId(this.districtId.get());
    return builder;
  }

  public static ThriftStructCodec3<Favorite> CODEC = new ThriftStructCodec3<Favorite>() {
    public Favorite decode(TProtocol _iprot) throws org.apache.thrift.TException {
      Builder builder = new Builder();
      long uid = (long) 0;
      int favoriteType = 0;
      int resourceId = 0;
      int districtId = 0;
      Boolean _done = false;
      _iprot.readStructBegin();
      while (!_done) {
        TField _field = _iprot.readFieldBegin();
        if (_field.type == TType.STOP) {
          _done = true;
        } else {
          switch (_field.id) {
            case 1: /* uid */
              switch (_field.type) {
                case TType.I64:
                  Long uid_item;
                  uid_item = _iprot.readI64();
                  uid = uid_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.uid(uid);
              break;
            case 2: /* favoriteType */
              switch (_field.type) {
                case TType.I32:
                  Integer favoriteType_item;
                  favoriteType_item = _iprot.readI32();
                  favoriteType = favoriteType_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.favoriteType(favoriteType);
              break;
            case 3: /* resourceId */
              switch (_field.type) {
                case TType.I32:
                  Integer resourceId_item;
                  resourceId_item = _iprot.readI32();
                  resourceId = resourceId_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.resourceId(resourceId);
              break;
            case 4: /* districtId */
              switch (_field.type) {
                case TType.I32:
                  Integer districtId_item;
                  districtId_item = _iprot.readI32();
                  districtId = districtId_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.districtId(districtId);
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

    public void encode(Favorite struct, TProtocol oprot) throws org.apache.thrift.TException {
      struct.write(oprot);
    }
  };

  public static Favorite decode(TProtocol _iprot) throws org.apache.thrift.TException {
    return CODEC.decode(_iprot);
  }

  public static void encode(Favorite struct, TProtocol oprot) throws org.apache.thrift.TException {
    CODEC.encode(struct, oprot);
  }

  public Favorite(
  long uid,
  int favoriteType,
  int resourceId,
  Option<Integer> districtId
  ) {
    this.uid = uid;
    this.favoriteType = favoriteType;
    this.resourceId = resourceId;
    this.districtId = districtId;
  }

  public long getUid() {
    return this.uid;
  }
  public int getFavoriteType() {
    return this.favoriteType;
  }
  public int getResourceId() {
    return this.resourceId;
  }
  public int getDistrictId() {
    return this.districtId.get();
  }

  public void write(TProtocol _oprot) throws org.apache.thrift.TException {
    validate();
    _oprot.writeStructBegin(STRUCT);
      _oprot.writeFieldBegin(UidField);
      Long uid_item = uid;
      _oprot.writeI64(uid_item);
      _oprot.writeFieldEnd();
      _oprot.writeFieldBegin(FavoriteTypeField);
      Integer favoriteType_item = favoriteType;
      _oprot.writeI32(favoriteType_item);
      _oprot.writeFieldEnd();
      _oprot.writeFieldBegin(ResourceIdField);
      Integer resourceId_item = resourceId;
      _oprot.writeI32(resourceId_item);
      _oprot.writeFieldEnd();
    if (districtId.isDefined()) {  _oprot.writeFieldBegin(DistrictIdField);
      Integer districtId_item = districtId.get();
      _oprot.writeI32(districtId_item);
      _oprot.writeFieldEnd();
    }
    _oprot.writeFieldStop();
    _oprot.writeStructEnd();
  }

  private void validate() throws org.apache.thrift.protocol.TProtocolException {
  }

  public boolean equals(Object other) {
    if (!(other instanceof Favorite)) return false;
    Favorite that = (Favorite) other;
    return
      this.uid == that.uid &&

      this.favoriteType == that.favoriteType &&

      this.resourceId == that.resourceId &&

      this.districtId.equals(that.districtId)
;
  }

  public String toString() {
    return "Favorite(" + this.uid + "," + this.favoriteType + "," + this.resourceId + "," + this.districtId + ")";
  }

  public int hashCode() {
    int hash = 1;
    hash = hash * new Long(this.uid).hashCode();
    hash = hash * new Integer(this.favoriteType).hashCode();
    hash = hash * new Integer(this.resourceId).hashCode();
    hash = hash * (this.districtId.isDefined() ? 0 : new Integer(this.districtId.get()).hashCode());
    return hash;
  }
}