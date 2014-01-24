package com.ctrip.gs.favorite.thrift;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec3;
import com.twitter.scrooge.Utilities;

public class Favorites implements ThriftStruct {
  private static final TStruct STRUCT = new TStruct("Favorites");
  private static final TField TotalField = new TField("total", TType.I32, (short) 1);
  final int total;
  private static final TField ItemsField = new TField("items", TType.LIST, (short) 2);
  final List<Favorite> items;

  public static class Builder {
    private int _total = 0;
    private Boolean _got_total = false;

    public Builder total(int value) {
      this._total = value;
      this._got_total = true;
      return this;
    }
    private List<Favorite> _items = Utilities.makeList();
    private Boolean _got_items = false;

    public Builder items(List<Favorite> value) {
      this._items = value;
      this._got_items = true;
      return this;
    }

    public Favorites build() {
      return new Favorites(
        this._total,
        this._items    );
    }
  }

  public Builder copy() {
    Builder builder = new Builder();
    builder.total(this.total);
    builder.items(this.items);
    return builder;
  }

  public static ThriftStructCodec3<Favorites> CODEC = new ThriftStructCodec3<Favorites>() {
    public Favorites decode(TProtocol _iprot) throws org.apache.thrift.TException {
      Builder builder = new Builder();
      int total = 0;
      List<Favorite> items = Utilities.makeList();
      Boolean _done = false;
      _iprot.readStructBegin();
      while (!_done) {
        TField _field = _iprot.readFieldBegin();
        if (_field.type == TType.STOP) {
          _done = true;
        } else {
          switch (_field.id) {
            case 1: /* total */
              switch (_field.type) {
                case TType.I32:
                  Integer total_item;
                  total_item = _iprot.readI32();
                  total = total_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.total(total);
              break;
            case 2: /* items */
              switch (_field.type) {
                case TType.LIST:
                  List<Favorite> items_item;
                  TList _list_items_item = _iprot.readListBegin();
                  items_item = new ArrayList<Favorite>();
                  int _i_items_item = 0;
                  Favorite _items_item_element;
                  while (_i_items_item < _list_items_item.size) {
                    _items_item_element = Favorite.decode(_iprot);
                    items_item.add(_items_item_element);
                    _i_items_item += 1;
                  }
                  _iprot.readListEnd();
                  items = items_item;
                  break;
                default:
                  TProtocolUtil.skip(_iprot, _field.type);
              }
              builder.items(items);
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

    public void encode(Favorites struct, TProtocol oprot) throws org.apache.thrift.TException {
      struct.write(oprot);
    }
  };

  public static Favorites decode(TProtocol _iprot) throws org.apache.thrift.TException {
    return CODEC.decode(_iprot);
  }

  public static void encode(Favorites struct, TProtocol oprot) throws org.apache.thrift.TException {
    CODEC.encode(struct, oprot);
  }

  public Favorites(
  int total,
  List<Favorite> items
  ) {
    this.total = total;
    this.items = items;
  }

  public int getTotal() {
    return this.total;
  }
  public List<Favorite> getItems() {
    return this.items;
  }

  public void write(TProtocol _oprot) throws org.apache.thrift.TException {
    validate();
    _oprot.writeStructBegin(STRUCT);
      _oprot.writeFieldBegin(TotalField);
      Integer total_item = total;
      _oprot.writeI32(total_item);
      _oprot.writeFieldEnd();
      _oprot.writeFieldBegin(ItemsField);
      List<Favorite> items_item = items;
      _oprot.writeListBegin(new TList(TType.STRUCT, items_item.size()));
      for (Favorite _items_item_element : items_item) {
        _items_item_element.write(_oprot);
      }
      _oprot.writeListEnd();
      _oprot.writeFieldEnd();
    _oprot.writeFieldStop();
    _oprot.writeStructEnd();
  }

  private void validate() throws org.apache.thrift.protocol.TProtocolException {
  }

  public boolean equals(Object other) {
    if (!(other instanceof Favorites)) return false;
    Favorites that = (Favorites) other;
    return
      this.total == that.total &&

this.items.equals(that.items)
;
  }

  public String toString() {
    return "Favorites(" + this.total + "," + this.items + ")";
  }

  public int hashCode() {
    int hash = 1;
    hash = hash * new Integer(this.total).hashCode();
    hash = hash * (this.items == null ? 0 : this.items.hashCode());
    return hash;
  }
}