package com.ctrip.gs.favorite.thrift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransport;

import com.twitter.finagle.Service;
import com.twitter.finagle.SourcedException;
import com.twitter.finagle.stats.Counter;
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.scrooge.Option;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec3;
import com.twitter.scrooge.Utilities;
import com.twitter.util.Function;
import com.twitter.util.Function2;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;

public class FavoriteService {
  public interface Iface {
    public Void addFavorite(Favorite favorite) throws ExceptionBase;
    public Void deleteFavorite(long uid, int favoriteType, int resourceId) throws ExceptionBase;
    public Boolean isFavorite(long uid, int favoriteType, int resourceId) throws ExceptionBase;
    public Favorites getFavorites(long uid, int favoriteType, int start, int count) throws ExceptionBase;
    public List<Favorite> getLatestFavorites(long uid, int count) throws ExceptionBase;
    public Integer getFavoritesCount(long uid, int favoriteType) throws ExceptionBase;
  }

  public interface FutureIface {
    public Future<Void> addFavorite(Favorite favorite);
    public Future<Void> deleteFavorite(long uid, int favoriteType, int resourceId);
    public Future<Boolean> isFavorite(long uid, int favoriteType, int resourceId);
    public Future<Favorites> getFavorites(long uid, int favoriteType, int start, int count);
    public Future<List<Favorite>> getLatestFavorites(long uid, int count);
    public Future<Integer> getFavoritesCount(long uid, int favoriteType);
  }

  static class addFavorite_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("addFavorite_args");
    private static final TField FavoriteField = new TField("favorite", TType.STRUCT, (short) 1);
    final Favorite favorite;
  
    public static class Builder {
      private Favorite _favorite = null;
      private Boolean _got_favorite = false;
  
      public Builder favorite(Favorite value) {
        this._favorite = value;
        this._got_favorite = true;
        return this;
      }
  
      public addFavorite_args build() {
        return new addFavorite_args(
          this._favorite    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.favorite(this.favorite);
      return builder;
    }
  
    public static ThriftStructCodec3<addFavorite_args> CODEC = new ThriftStructCodec3<addFavorite_args>() {
      public addFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        Favorite favorite = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 1: /* favorite */
                switch (_field.type) {
                  case TType.STRUCT:
                    Favorite favorite_item;
                    favorite_item = Favorite.decode(_iprot);
                    favorite = favorite_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.favorite(favorite);
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
  
      public void encode(addFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static addFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(addFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public addFavorite_args(
    Favorite favorite
    ) {
      this.favorite = favorite;
    }
  
    public Favorite getFavorite() {
      return this.favorite;
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
        _oprot.writeFieldBegin(FavoriteField);
        Favorite favorite_item = favorite;
        favorite_item.write(_oprot);
        _oprot.writeFieldEnd();
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof addFavorite_args)) return false;
      addFavorite_args that = (addFavorite_args) other;
      return
  this.favorite.equals(that.favorite);
    }
  
    public String toString() {
      return "addFavorite_args(" + this.favorite + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.favorite == null ? 0 : this.favorite.hashCode());
      return hash;
    }
  }
  static class addFavorite_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("addFavorite_result");
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public addFavorite_result build() {
        return new addFavorite_result(
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<addFavorite_result> CODEC = new ThriftStructCodec3<addFavorite_result>() {
      public addFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(addFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static addFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(addFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public addFavorite_result(
    Option<ExceptionBase> eb
    ) {
      this.eb = eb;
    }
  
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof addFavorite_result)) return false;
      addFavorite_result that = (addFavorite_result) other;
      return
  this.eb.equals(that.eb);
    }
  
    public String toString() {
      return "addFavorite_result(" + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  static class deleteFavorite_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("deleteFavorite_args");
    private static final TField UidField = new TField("uid", TType.I64, (short) 1);
    final long uid;
    private static final TField FavoriteTypeField = new TField("favoriteType", TType.I32, (short) 2);
    final int favoriteType;
    private static final TField ResourceIdField = new TField("resourceId", TType.I32, (short) 3);
    final int resourceId;
  
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
  
      public deleteFavorite_args build() {
        return new deleteFavorite_args(
          this._uid,
          this._favoriteType,
          this._resourceId    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.uid(this.uid);
      builder.favoriteType(this.favoriteType);
      builder.resourceId(this.resourceId);
      return builder;
    }
  
    public static ThriftStructCodec3<deleteFavorite_args> CODEC = new ThriftStructCodec3<deleteFavorite_args>() {
      public deleteFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        long uid = (long) 0;
        int favoriteType = 0;
        int resourceId = 0;
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
  
      public void encode(deleteFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static deleteFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(deleteFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public deleteFavorite_args(
    long uid,
    int favoriteType,
    int resourceId
    ) {
      this.uid = uid;
      this.favoriteType = favoriteType;
      this.resourceId = resourceId;
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
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof deleteFavorite_args)) return false;
      deleteFavorite_args that = (deleteFavorite_args) other;
      return
        this.uid == that.uid &&
  
        this.favoriteType == that.favoriteType &&
  
        this.resourceId == that.resourceId
  ;
    }
  
    public String toString() {
      return "deleteFavorite_args(" + this.uid + "," + this.favoriteType + "," + this.resourceId + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * new Long(this.uid).hashCode();
      hash = hash * new Integer(this.favoriteType).hashCode();
      hash = hash * new Integer(this.resourceId).hashCode();
      return hash;
    }
  }
  static class deleteFavorite_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("deleteFavorite_result");
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public deleteFavorite_result build() {
        return new deleteFavorite_result(
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<deleteFavorite_result> CODEC = new ThriftStructCodec3<deleteFavorite_result>() {
      public deleteFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(deleteFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static deleteFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(deleteFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public deleteFavorite_result(
    Option<ExceptionBase> eb
    ) {
      this.eb = eb;
    }
  
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof deleteFavorite_result)) return false;
      deleteFavorite_result that = (deleteFavorite_result) other;
      return
  this.eb.equals(that.eb);
    }
  
    public String toString() {
      return "deleteFavorite_result(" + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  static class isFavorite_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("isFavorite_args");
    private static final TField UidField = new TField("uid", TType.I64, (short) 1);
    final long uid;
    private static final TField FavoriteTypeField = new TField("favoriteType", TType.I32, (short) 2);
    final int favoriteType;
    private static final TField ResourceIdField = new TField("resourceId", TType.I32, (short) 3);
    final int resourceId;
  
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
  
      public isFavorite_args build() {
        return new isFavorite_args(
          this._uid,
          this._favoriteType,
          this._resourceId    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.uid(this.uid);
      builder.favoriteType(this.favoriteType);
      builder.resourceId(this.resourceId);
      return builder;
    }
  
    public static ThriftStructCodec3<isFavorite_args> CODEC = new ThriftStructCodec3<isFavorite_args>() {
      public isFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        long uid = (long) 0;
        int favoriteType = 0;
        int resourceId = 0;
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
  
      public void encode(isFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static isFavorite_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(isFavorite_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public isFavorite_args(
    long uid,
    int favoriteType,
    int resourceId
    ) {
      this.uid = uid;
      this.favoriteType = favoriteType;
      this.resourceId = resourceId;
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
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof isFavorite_args)) return false;
      isFavorite_args that = (isFavorite_args) other;
      return
        this.uid == that.uid &&
  
        this.favoriteType == that.favoriteType &&
  
        this.resourceId == that.resourceId
  ;
    }
  
    public String toString() {
      return "isFavorite_args(" + this.uid + "," + this.favoriteType + "," + this.resourceId + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * new Long(this.uid).hashCode();
      hash = hash * new Integer(this.favoriteType).hashCode();
      hash = hash * new Integer(this.resourceId).hashCode();
      return hash;
    }
  }
  static class isFavorite_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("isFavorite_result");
    private static final TField SuccessField = new TField("success", TType.BOOL, (short) 0);
    final Option<Boolean> success;
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private boolean _success = false;
      private Boolean _got_success = false;
  
      public Builder success(boolean value) {
        this._success = value;
        this._got_success = true;
        return this;
      }
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public isFavorite_result build() {
        return new isFavorite_result(
        Option.make(this._got_success, this._success),
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.success.isDefined()) builder.success(this.success.get());
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<isFavorite_result> CODEC = new ThriftStructCodec3<isFavorite_result>() {
      public isFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        boolean success = false;
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 0: /* success */
                switch (_field.type) {
                  case TType.BOOL:
                    Boolean success_item;
                    success_item = _iprot.readBool();
                    success = success_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.success(success);
                break;
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(isFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static isFavorite_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(isFavorite_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public isFavorite_result(
    Option<Boolean> success,
    Option<ExceptionBase> eb
    ) {
      this.success = success;
      this.eb = eb;
    }
  
    public boolean getSuccess() {
      return this.success.get();
    }
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (success.isDefined()) {  _oprot.writeFieldBegin(SuccessField);
        Boolean success_item = success.get();
        _oprot.writeBool(success_item);
        _oprot.writeFieldEnd();
      }
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof isFavorite_result)) return false;
      isFavorite_result that = (isFavorite_result) other;
      return
        this.success.equals(that.success) &&
  
  this.eb.equals(that.eb)
  ;
    }
  
    public String toString() {
      return "isFavorite_result(" + this.success + "," + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.success.isDefined() ? 0 : new Boolean(this.success.get()).hashCode());
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  static class getFavorites_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getFavorites_args");
    private static final TField UidField = new TField("uid", TType.I64, (short) 1);
    final long uid;
    private static final TField FavoriteTypeField = new TField("favoriteType", TType.I32, (short) 2);
    final int favoriteType;
    private static final TField StartField = new TField("start", TType.I32, (short) 3);
    final int start;
    private static final TField CountField = new TField("count", TType.I32, (short) 4);
    final int count;
  
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
      private int _start = 0;
      private Boolean _got_start = false;
  
      public Builder start(int value) {
        this._start = value;
        this._got_start = true;
        return this;
      }
      private int _count = 0;
      private Boolean _got_count = false;
  
      public Builder count(int value) {
        this._count = value;
        this._got_count = true;
        return this;
      }
  
      public getFavorites_args build() {
        return new getFavorites_args(
          this._uid,
          this._favoriteType,
          this._start,
          this._count    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.uid(this.uid);
      builder.favoriteType(this.favoriteType);
      builder.start(this.start);
      builder.count(this.count);
      return builder;
    }
  
    public static ThriftStructCodec3<getFavorites_args> CODEC = new ThriftStructCodec3<getFavorites_args>() {
      public getFavorites_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        long uid = (long) 0;
        int favoriteType = 0;
        int start = 0;
        int count = 0;
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
              case 3: /* start */
                switch (_field.type) {
                  case TType.I32:
                    Integer start_item;
                    start_item = _iprot.readI32();
                    start = start_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.start(start);
                break;
              case 4: /* count */
                switch (_field.type) {
                  case TType.I32:
                    Integer count_item;
                    count_item = _iprot.readI32();
                    count = count_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.count(count);
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
  
      public void encode(getFavorites_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getFavorites_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getFavorites_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getFavorites_args(
    long uid,
    int favoriteType,
    int start,
    int count
    ) {
      this.uid = uid;
      this.favoriteType = favoriteType;
      this.start = start;
      this.count = count;
    }
  
    public long getUid() {
      return this.uid;
    }
    public int getFavoriteType() {
      return this.favoriteType;
    }
    public int getStart() {
      return this.start;
    }
    public int getCount() {
      return this.count;
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
        _oprot.writeFieldBegin(StartField);
        Integer start_item = start;
        _oprot.writeI32(start_item);
        _oprot.writeFieldEnd();
        _oprot.writeFieldBegin(CountField);
        Integer count_item = count;
        _oprot.writeI32(count_item);
        _oprot.writeFieldEnd();
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getFavorites_args)) return false;
      getFavorites_args that = (getFavorites_args) other;
      return
        this.uid == that.uid &&
  
        this.favoriteType == that.favoriteType &&
  
        this.start == that.start &&
  
        this.count == that.count
  ;
    }
  
    public String toString() {
      return "getFavorites_args(" + this.uid + "," + this.favoriteType + "," + this.start + "," + this.count + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * new Long(this.uid).hashCode();
      hash = hash * new Integer(this.favoriteType).hashCode();
      hash = hash * new Integer(this.start).hashCode();
      hash = hash * new Integer(this.count).hashCode();
      return hash;
    }
  }
  static class getFavorites_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getFavorites_result");
    private static final TField SuccessField = new TField("success", TType.STRUCT, (short) 0);
    final Option<Favorites> success;
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private Favorites _success = null;
      private Boolean _got_success = false;
  
      public Builder success(Favorites value) {
        this._success = value;
        this._got_success = true;
        return this;
      }
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public getFavorites_result build() {
        return new getFavorites_result(
        Option.make(this._got_success, this._success),
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.success.isDefined()) builder.success(this.success.get());
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<getFavorites_result> CODEC = new ThriftStructCodec3<getFavorites_result>() {
      public getFavorites_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        Favorites success = null;
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 0: /* success */
                switch (_field.type) {
                  case TType.STRUCT:
                    Favorites success_item;
                    success_item = Favorites.decode(_iprot);
                    success = success_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.success(success);
                break;
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(getFavorites_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getFavorites_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getFavorites_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getFavorites_result(
    Option<Favorites> success,
    Option<ExceptionBase> eb
    ) {
      this.success = success;
      this.eb = eb;
    }
  
    public Favorites getSuccess() {
      return this.success.get();
    }
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (success.isDefined()) {  _oprot.writeFieldBegin(SuccessField);
        Favorites success_item = success.get();
        success_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getFavorites_result)) return false;
      getFavorites_result that = (getFavorites_result) other;
      return
  this.success.equals(that.success) &&
  this.eb.equals(that.eb);
    }
  
    public String toString() {
      return "getFavorites_result(" + this.success + "," + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.success.isDefined() ? 0 : this.success.get().hashCode());
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  static class getLatestFavorites_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getLatestFavorites_args");
    private static final TField UidField = new TField("uid", TType.I64, (short) 1);
    final long uid;
    private static final TField CountField = new TField("count", TType.I32, (short) 2);
    final int count;
  
    public static class Builder {
      private long _uid = (long) 0;
      private Boolean _got_uid = false;
  
      public Builder uid(long value) {
        this._uid = value;
        this._got_uid = true;
        return this;
      }
      private int _count = 0;
      private Boolean _got_count = false;
  
      public Builder count(int value) {
        this._count = value;
        this._got_count = true;
        return this;
      }
  
      public getLatestFavorites_args build() {
        return new getLatestFavorites_args(
          this._uid,
          this._count    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.uid(this.uid);
      builder.count(this.count);
      return builder;
    }
  
    public static ThriftStructCodec3<getLatestFavorites_args> CODEC = new ThriftStructCodec3<getLatestFavorites_args>() {
      public getLatestFavorites_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        long uid = (long) 0;
        int count = 0;
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
              case 2: /* count */
                switch (_field.type) {
                  case TType.I32:
                    Integer count_item;
                    count_item = _iprot.readI32();
                    count = count_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.count(count);
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
  
      public void encode(getLatestFavorites_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getLatestFavorites_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getLatestFavorites_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getLatestFavorites_args(
    long uid,
    int count
    ) {
      this.uid = uid;
      this.count = count;
    }
  
    public long getUid() {
      return this.uid;
    }
    public int getCount() {
      return this.count;
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
        _oprot.writeFieldBegin(UidField);
        Long uid_item = uid;
        _oprot.writeI64(uid_item);
        _oprot.writeFieldEnd();
        _oprot.writeFieldBegin(CountField);
        Integer count_item = count;
        _oprot.writeI32(count_item);
        _oprot.writeFieldEnd();
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getLatestFavorites_args)) return false;
      getLatestFavorites_args that = (getLatestFavorites_args) other;
      return
        this.uid == that.uid &&
  
        this.count == that.count
  ;
    }
  
    public String toString() {
      return "getLatestFavorites_args(" + this.uid + "," + this.count + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * new Long(this.uid).hashCode();
      hash = hash * new Integer(this.count).hashCode();
      return hash;
    }
  }
  static class getLatestFavorites_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getLatestFavorites_result");
    private static final TField SuccessField = new TField("success", TType.LIST, (short) 0);
    final Option<List<Favorite>> success;
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private List<Favorite> _success = Utilities.makeList();
      private Boolean _got_success = false;
  
      public Builder success(List<Favorite> value) {
        this._success = value;
        this._got_success = true;
        return this;
      }
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public getLatestFavorites_result build() {
        return new getLatestFavorites_result(
        Option.make(this._got_success, this._success),
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.success.isDefined()) builder.success(this.success.get());
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<getLatestFavorites_result> CODEC = new ThriftStructCodec3<getLatestFavorites_result>() {
      public getLatestFavorites_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        List<Favorite> success = Utilities.makeList();
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 0: /* success */
                switch (_field.type) {
                  case TType.LIST:
                    List<Favorite> success_item;
                    TList _list_success_item = _iprot.readListBegin();
                    success_item = new ArrayList<Favorite>();
                    int _i_success_item = 0;
                    Favorite _success_item_element;
                    while (_i_success_item < _list_success_item.size) {
                      _success_item_element = Favorite.decode(_iprot);
                      success_item.add(_success_item_element);
                      _i_success_item += 1;
                    }
                    _iprot.readListEnd();
                    success = success_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.success(success);
                break;
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(getLatestFavorites_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getLatestFavorites_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getLatestFavorites_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getLatestFavorites_result(
    Option<List<Favorite>> success,
    Option<ExceptionBase> eb
    ) {
      this.success = success;
      this.eb = eb;
    }
  
    public List<Favorite> getSuccess() {
      return this.success.get();
    }
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (success.isDefined()) {  _oprot.writeFieldBegin(SuccessField);
        List<Favorite> success_item = success.get();
        _oprot.writeListBegin(new TList(TType.STRUCT, success_item.size()));
        for (Favorite _success_item_element : success_item) {
          _success_item_element.write(_oprot);
        }
        _oprot.writeListEnd();
        _oprot.writeFieldEnd();
      }
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getLatestFavorites_result)) return false;
      getLatestFavorites_result that = (getLatestFavorites_result) other;
      return
  this.success.equals(that.success) &&
  this.eb.equals(that.eb);
    }
  
    public String toString() {
      return "getLatestFavorites_result(" + this.success + "," + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.success.isDefined() ? 0 : this.success.get().hashCode());
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  static class getFavoritesCount_args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getFavoritesCount_args");
    private static final TField UidField = new TField("uid", TType.I64, (short) 1);
    final long uid;
    private static final TField FavoriteTypeField = new TField("favoriteType", TType.I32, (short) 2);
    final int favoriteType;
  
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
  
      public getFavoritesCount_args build() {
        return new getFavoritesCount_args(
          this._uid,
          this._favoriteType    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.uid(this.uid);
      builder.favoriteType(this.favoriteType);
      return builder;
    }
  
    public static ThriftStructCodec3<getFavoritesCount_args> CODEC = new ThriftStructCodec3<getFavoritesCount_args>() {
      public getFavoritesCount_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        long uid = (long) 0;
        int favoriteType = 0;
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
  
      public void encode(getFavoritesCount_args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getFavoritesCount_args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getFavoritesCount_args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getFavoritesCount_args(
    long uid,
    int favoriteType
    ) {
      this.uid = uid;
      this.favoriteType = favoriteType;
    }
  
    public long getUid() {
      return this.uid;
    }
    public int getFavoriteType() {
      return this.favoriteType;
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
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getFavoritesCount_args)) return false;
      getFavoritesCount_args that = (getFavoritesCount_args) other;
      return
        this.uid == that.uid &&
  
        this.favoriteType == that.favoriteType
  ;
    }
  
    public String toString() {
      return "getFavoritesCount_args(" + this.uid + "," + this.favoriteType + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * new Long(this.uid).hashCode();
      hash = hash * new Integer(this.favoriteType).hashCode();
      return hash;
    }
  }
  static class getFavoritesCount_result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("getFavoritesCount_result");
    private static final TField SuccessField = new TField("success", TType.I32, (short) 0);
    final Option<Integer> success;
    private static final TField EbField = new TField("eb", TType.STRUCT, (short) 1);
    final Option<ExceptionBase> eb;
  
    public static class Builder {
      private int _success = 0;
      private Boolean _got_success = false;
  
      public Builder success(int value) {
        this._success = value;
        this._got_success = true;
        return this;
      }
      private ExceptionBase _eb = null;
      private Boolean _got_eb = false;
  
      public Builder eb(ExceptionBase value) {
        this._eb = value;
        this._got_eb = true;
        return this;
      }
  
      public getFavoritesCount_result build() {
        return new getFavoritesCount_result(
        Option.make(this._got_success, this._success),
        Option.make(this._got_eb, this._eb)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.success.isDefined()) builder.success(this.success.get());
      if (this.eb.isDefined()) builder.eb(this.eb.get());
      return builder;
    }
  
    public static ThriftStructCodec3<getFavoritesCount_result> CODEC = new ThriftStructCodec3<getFavoritesCount_result>() {
      public getFavoritesCount_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        int success = 0;
        ExceptionBase eb = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 0: /* success */
                switch (_field.type) {
                  case TType.I32:
                    Integer success_item;
                    success_item = _iprot.readI32();
                    success = success_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.success(success);
                break;
              case 1: /* eb */
                switch (_field.type) {
                  case TType.STRUCT:
                    ExceptionBase eb_item;
                    eb_item = ExceptionBase.decode(_iprot);
                    eb = eb_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.eb(eb);
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
  
      public void encode(getFavoritesCount_result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static getFavoritesCount_result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(getFavoritesCount_result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public getFavoritesCount_result(
    Option<Integer> success,
    Option<ExceptionBase> eb
    ) {
      this.success = success;
      this.eb = eb;
    }
  
    public int getSuccess() {
      return this.success.get();
    }
    public ExceptionBase getEb() {
      return this.eb.get();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (success.isDefined()) {  _oprot.writeFieldBegin(SuccessField);
        Integer success_item = success.get();
        _oprot.writeI32(success_item);
        _oprot.writeFieldEnd();
      }
      if (eb.isDefined()) {  _oprot.writeFieldBegin(EbField);
        ExceptionBase eb_item = eb.get();
        eb_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
    public boolean equals(Object other) {
      if (!(other instanceof getFavoritesCount_result)) return false;
      getFavoritesCount_result that = (getFavoritesCount_result) other;
      return
        this.success.equals(that.success) &&
  
  this.eb.equals(that.eb)
  ;
    }
  
    public String toString() {
      return "getFavoritesCount_result(" + this.success + "," + this.eb + ")";
    }
  
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.success.isDefined() ? 0 : new Integer(this.success.get()).hashCode());
      hash = hash * (this.eb.isDefined() ? 0 : this.eb.get().hashCode());
      return hash;
    }
  }
  public static class FinagledClient implements FutureIface {
    private com.twitter.finagle.Service<ThriftClientRequest, byte[]> service;
    private String serviceName;
    private TProtocolFactory protocolFactory /* new TBinaryProtocol.Factory */;
    private StatsReceiver scopedStats = new NullStatsReceiver();
  
    public FinagledClient(
      com.twitter.finagle.Service<ThriftClientRequest, byte[]> service,
      TProtocolFactory protocolFactory /* new TBinaryProtocol.Factory */,
      String serviceName
    ) {
      this.service = service;
      this.serviceName = serviceName;
      this.protocolFactory = protocolFactory;
      }
  
    // ----- boilerplate that should eventually be moved into finagle:
  
    protected ThriftClientRequest encodeRequest(String name, ThriftStruct args) {
      TMemoryBuffer buf = new TMemoryBuffer(512);
      TProtocol oprot = protocolFactory.getProtocol(buf);
  
      try {
        oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0));
        args.write(oprot);
        oprot.writeMessageEnd();
      } catch (TException e) {
        // not real.
      }
  
      byte[] bytes = Arrays.copyOfRange(buf.getArray(), 0, buf.length());
      return new ThriftClientRequest(bytes, false);
    }
  
    protected <T extends ThriftStruct> T decodeResponse(byte[] resBytes, ThriftStructCodec3<T> codec) throws TException {
      TProtocol iprot = protocolFactory.getProtocol(new TMemoryInputTransport(resBytes));
      TMessage msg = iprot.readMessageBegin();
      try {
        if (msg.type == TMessageType.EXCEPTION) {
          TException exception = TApplicationException.read(iprot);
          if (exception instanceof SourcedException) {
            if (this.serviceName != "") ((SourcedException) exception).serviceName_$eq(this.serviceName);
          }
          throw exception;
        } else {
          return codec.decode(iprot);
        }
      } finally {
        iprot.readMessageEnd();
      }
    }
  
    protected Exception missingResult(String name) {
      return new TApplicationException(
        TApplicationException.MISSING_RESULT,
        "`" + name + "` failed: unknown result"
      );
    }
  
    class __Stats {
      Counter requestsCounter, successCounter, failuresCounter;
      StatsReceiver failuresScope;
  
      public __Stats(String name) {
        StatsReceiver scope = FinagledClient.this.scopedStats.scope(name);
        this.requestsCounter = scope.counter0("requests");
        this.successCounter = scope.counter0("success");
        this.failuresCounter = scope.counter0("failures");
        this.failuresScope = scope.scope("failures");
      }
    }
  
    // ----- end boilerplate.
  
    private __Stats __stats_AddFavorite = new __Stats("AddFavorite");
  
    public Future<Void> addFavorite(Favorite favorite) {
      __stats_AddFavorite.requestsCounter.incr();
  
      Future<Void> rv = this.service.apply(encodeRequest("AddFavorite", new addFavorite_args(favorite))).flatMap(new Function<byte[], Future<Void>>() {
        public Future<Void> apply(byte[] in) {
          try {
            addFavorite_result result = decodeResponse(in, addFavorite_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            return Future.value(null);
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<Void>>() {
        public Future<Void> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<Void>() {
        public void onSuccess(Void result) {
          __stats_AddFavorite.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_AddFavorite.failuresCounter.incr();
          __stats_AddFavorite.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
    private __Stats __stats_DeleteFavorite = new __Stats("DeleteFavorite");
  
    public Future<Void> deleteFavorite(long uid, int favoriteType, int resourceId) {
      __stats_DeleteFavorite.requestsCounter.incr();
  
      Future<Void> rv = this.service.apply(encodeRequest("DeleteFavorite", new deleteFavorite_args(uid, favoriteType, resourceId))).flatMap(new Function<byte[], Future<Void>>() {
        public Future<Void> apply(byte[] in) {
          try {
            deleteFavorite_result result = decodeResponse(in, deleteFavorite_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            return Future.value(null);
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<Void>>() {
        public Future<Void> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<Void>() {
        public void onSuccess(Void result) {
          __stats_DeleteFavorite.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_DeleteFavorite.failuresCounter.incr();
          __stats_DeleteFavorite.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
    private __Stats __stats_IsFavorite = new __Stats("IsFavorite");
  
    public Future<Boolean> isFavorite(long uid, int favoriteType, int resourceId) {
      __stats_IsFavorite.requestsCounter.incr();
  
      Future<Boolean> rv = this.service.apply(encodeRequest("IsFavorite", new isFavorite_args(uid, favoriteType, resourceId))).flatMap(new Function<byte[], Future<Boolean>>() {
        public Future<Boolean> apply(byte[] in) {
          try {
            isFavorite_result result = decodeResponse(in, isFavorite_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            if (result.success.isDefined()) return Future.value(result.success.get());
            return Future.exception(missingResult("IsFavorite"));
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<Boolean>>() {
        public Future<Boolean> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<Boolean>() {
        public void onSuccess(Boolean result) {
          __stats_IsFavorite.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_IsFavorite.failuresCounter.incr();
          __stats_IsFavorite.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
    private __Stats __stats_GetFavorites = new __Stats("GetFavorites");
  
    public Future<Favorites> getFavorites(long uid, int favoriteType, int start, int count) {
      __stats_GetFavorites.requestsCounter.incr();
  
      Future<Favorites> rv = this.service.apply(encodeRequest("GetFavorites", new getFavorites_args(uid, favoriteType, start, count))).flatMap(new Function<byte[], Future<Favorites>>() {
        public Future<Favorites> apply(byte[] in) {
          try {
            getFavorites_result result = decodeResponse(in, getFavorites_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            if (result.success.isDefined()) return Future.value(result.success.get());
            return Future.exception(missingResult("GetFavorites"));
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<Favorites>>() {
        public Future<Favorites> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<Favorites>() {
        public void onSuccess(Favorites result) {
          __stats_GetFavorites.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_GetFavorites.failuresCounter.incr();
          __stats_GetFavorites.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
    private __Stats __stats_GetLatestFavorites = new __Stats("GetLatestFavorites");
  
    public Future<List<Favorite>> getLatestFavorites(long uid, int count) {
      __stats_GetLatestFavorites.requestsCounter.incr();
  
      Future<List<Favorite>> rv = this.service.apply(encodeRequest("GetLatestFavorites", new getLatestFavorites_args(uid, count))).flatMap(new Function<byte[], Future<List<Favorite>>>() {
        public Future<List<Favorite>> apply(byte[] in) {
          try {
            getLatestFavorites_result result = decodeResponse(in, getLatestFavorites_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            if (result.success.isDefined()) return Future.value(result.success.get());
            return Future.exception(missingResult("GetLatestFavorites"));
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<List<Favorite>>>() {
        public Future<List<Favorite>> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<List<Favorite>>() {
        public void onSuccess(List<Favorite> result) {
          __stats_GetLatestFavorites.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_GetLatestFavorites.failuresCounter.incr();
          __stats_GetLatestFavorites.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
    private __Stats __stats_GetFavoritesCount = new __Stats("GetFavoritesCount");
  
    public Future<Integer> getFavoritesCount(long uid, int favoriteType) {
      __stats_GetFavoritesCount.requestsCounter.incr();
  
      Future<Integer> rv = this.service.apply(encodeRequest("GetFavoritesCount", new getFavoritesCount_args(uid, favoriteType))).flatMap(new Function<byte[], Future<Integer>>() {
        public Future<Integer> apply(byte[] in) {
          try {
            getFavoritesCount_result result = decodeResponse(in, getFavoritesCount_result.CODEC);
  
            Exception exception = null;
            if (exception == null && result.eb.isDefined()) exception = result.eb.get();
            if (exception != null) return Future.exception(exception);
  
            if (result.success.isDefined()) return Future.value(result.success.get());
            return Future.exception(missingResult("GetFavoritesCount"));
          } catch (TException e) {
            return Future.exception(e);
          }
        }
      }).rescue(new Function<Throwable, Future<Integer>>() {
        public Future<Integer> apply(Throwable t) {
          if (t instanceof SourcedException) {
            ((SourcedException) t).serviceName_$eq(FinagledClient.this.serviceName);
          }
          return Future.exception(t);
        }
      });
  
      rv.addEventListener(new FutureEventListener<Integer>() {
        public void onSuccess(Integer result) {
          __stats_GetFavoritesCount.successCounter.incr();
        }
  
        public void onFailure(Throwable t) {
          __stats_GetFavoritesCount.failuresCounter.incr();
          __stats_GetFavoritesCount.failuresScope.counter0(t.getClass().getName()).incr();
        }
      });
  
      return rv;
    }
  }
  public static class FinagledService extends Service<byte[], byte[]> {
    final private FutureIface iface;
    final private TProtocolFactory protocolFactory;
  
    public FinagledService(final FutureIface iface, final TProtocolFactory protocolFactory) {
      this.iface = iface;
      this.protocolFactory = protocolFactory;
  
      addFunction("AddFavorite", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            addFavorite_args args = addFavorite_args.decode(iprot);
            iprot.readMessageEnd();
            Future<Void> result;
            try {
              result = iface.addFavorite(args.favorite);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<Void, Future<byte[]>>() {
              public Future<byte[]> apply(Void value){
                return reply("AddFavorite", seqid, new addFavorite_result.Builder().build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("AddFavorite", seqid, new addFavorite_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("AddFavorite", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
      addFunction("DeleteFavorite", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            deleteFavorite_args args = deleteFavorite_args.decode(iprot);
            iprot.readMessageEnd();
            Future<Void> result;
            try {
              result = iface.deleteFavorite(args.uid, args.favoriteType, args.resourceId);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<Void, Future<byte[]>>() {
              public Future<byte[]> apply(Void value){
                return reply("DeleteFavorite", seqid, new deleteFavorite_result.Builder().build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("DeleteFavorite", seqid, new deleteFavorite_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("DeleteFavorite", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
      addFunction("IsFavorite", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            isFavorite_args args = isFavorite_args.decode(iprot);
            iprot.readMessageEnd();
            Future<Boolean> result;
            try {
              result = iface.isFavorite(args.uid, args.favoriteType, args.resourceId);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<Boolean, Future<byte[]>>() {
              public Future<byte[]> apply(Boolean value){
                return reply("IsFavorite", seqid, new isFavorite_result.Builder().success(value).build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("IsFavorite", seqid, new isFavorite_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("IsFavorite", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
      addFunction("GetFavorites", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            getFavorites_args args = getFavorites_args.decode(iprot);
            iprot.readMessageEnd();
            Future<Favorites> result;
            try {
              result = iface.getFavorites(args.uid, args.favoriteType, args.start, args.count);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<Favorites, Future<byte[]>>() {
              public Future<byte[]> apply(Favorites value){
                return reply("GetFavorites", seqid, new getFavorites_result.Builder().success(value).build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("GetFavorites", seqid, new getFavorites_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("GetFavorites", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
      addFunction("GetLatestFavorites", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            getLatestFavorites_args args = getLatestFavorites_args.decode(iprot);
            iprot.readMessageEnd();
            Future<List<Favorite>> result;
            try {
              result = iface.getLatestFavorites(args.uid, args.count);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<List<Favorite>, Future<byte[]>>() {
              public Future<byte[]> apply(List<Favorite> value){
                return reply("GetLatestFavorites", seqid, new getLatestFavorites_result.Builder().success(value).build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("GetLatestFavorites", seqid, new getLatestFavorites_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("GetLatestFavorites", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
      addFunction("GetFavoritesCount", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(TProtocol iprot, final Integer seqid) {
          try {
            getFavoritesCount_args args = getFavoritesCount_args.decode(iprot);
            iprot.readMessageEnd();
            Future<Integer> result;
            try {
              result = iface.getFavoritesCount(args.uid, args.favoriteType);
            } catch (Throwable t) {
              result = Future.exception(t);
            }
            return result.flatMap(new Function<Integer, Future<byte[]>>() {
              public Future<byte[]> apply(Integer value){
                return reply("GetFavoritesCount", seqid, new getFavoritesCount_result.Builder().success(value).build());
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                if (t instanceof ExceptionBase) {
                  return reply("GetFavoritesCount", seqid, new getFavoritesCount_result.Builder().eb((ExceptionBase) t).build());
                }
                return Future.exception(t);
              }
            });
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              return exception("GetFavoritesCount", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage());
            } catch (Exception unrecoverable) {
              return Future.exception(unrecoverable);
            }
          } catch (Throwable t) {
            return Future.exception(t);
          }
        }
      });
    }
  
    // ----- boilerplate that should eventually be moved into finagle:
  
    protected Map<String, Function2<TProtocol, Integer, Future<byte[]>>> functionMap =
      new HashMap<String, Function2<TProtocol, Integer, Future<byte[]>>>();
  
    protected void addFunction(String name, Function2<TProtocol, Integer, Future<byte[]>> fn) {
      functionMap.put(name, fn);
    }
  
    protected Function2<TProtocol, Integer, Future<byte[]>> getFunction(String name) {
      return functionMap.get(name);
    }
  
    protected Future<byte[]> exception(String name, int seqid, int code, String message) {
      try {
        TApplicationException x = new TApplicationException(code, message);
        TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
        TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
  
        oprot.writeMessageBegin(new TMessage(name, TMessageType.EXCEPTION, seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
      } catch (Exception e) {
        return Future.exception(e);
      }
    }
  
    protected Future<byte[]> reply(String name, int seqid, ThriftStruct result) {
      try {
        TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
        TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
  
        oprot.writeMessageBegin(new TMessage(name, TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
  
        return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
      } catch (Exception e) {
        return Future.exception(e);
      }
    }
  
    public final Future<byte[]> apply(byte[] request) {
      TTransport inputTransport = new TMemoryInputTransport(request);
      TProtocol iprot = protocolFactory.getProtocol(inputTransport);
  
      try {
        TMessage msg = iprot.readMessageBegin();
        Function2<TProtocol, Integer, Future<byte[]>> f = functionMap.get(msg.name);
        if (f != null) {
          return f.apply(iprot, msg.seqid);
        } else {
          TProtocolUtil.skip(iprot, TType.STRUCT);
          return exception(msg.name, msg.seqid, TApplicationException.UNKNOWN_METHOD, "Invalid method name: '" + msg.name + "'");
        }
      } catch (Exception e) {
        return Future.exception(e);
      }
    }
  
    // ---- end boilerplate.
  }
}