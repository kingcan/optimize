// ORM class for table 'null'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu May 13 22:38:53 GMT+08:00 2021
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class QueryResult extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("member_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.member_id = (Integer)value;
      }
    });
    setters.put("item_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.item_id = (Integer)value;
      }
    });
    setters.put("play_duration", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.play_duration = (Integer)value;
      }
    });
    setters.put("create_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.create_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("create_date", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.create_date = (String)value;
      }
    });
    setters.put("parent_asset_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.parent_asset_id = (String)value;
      }
    });
    setters.put("name", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.name = (String)value;
      }
    });
    setters.put("description", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.description = (String)value;
      }
    });
    setters.put("category", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        QueryResult.this.category = (String)value;
      }
    });
  }
  public QueryResult() {
    init0();
  }
  private Integer member_id;
  public Integer get_member_id() {
    return member_id;
  }
  public void set_member_id(Integer member_id) {
    this.member_id = member_id;
  }
  public QueryResult with_member_id(Integer member_id) {
    this.member_id = member_id;
    return this;
  }
  private Integer item_id;
  public Integer get_item_id() {
    return item_id;
  }
  public void set_item_id(Integer item_id) {
    this.item_id = item_id;
  }
  public QueryResult with_item_id(Integer item_id) {
    this.item_id = item_id;
    return this;
  }
  private Integer play_duration;
  public Integer get_play_duration() {
    return play_duration;
  }
  public void set_play_duration(Integer play_duration) {
    this.play_duration = play_duration;
  }
  public QueryResult with_play_duration(Integer play_duration) {
    this.play_duration = play_duration;
    return this;
  }
  private java.sql.Timestamp create_time;
  public java.sql.Timestamp get_create_time() {
    return create_time;
  }
  public void set_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
  public QueryResult with_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
    return this;
  }
  private String create_date;
  public String get_create_date() {
    return create_date;
  }
  public void set_create_date(String create_date) {
    this.create_date = create_date;
  }
  public QueryResult with_create_date(String create_date) {
    this.create_date = create_date;
    return this;
  }
  private String parent_asset_id;
  public String get_parent_asset_id() {
    return parent_asset_id;
  }
  public void set_parent_asset_id(String parent_asset_id) {
    this.parent_asset_id = parent_asset_id;
  }
  public QueryResult with_parent_asset_id(String parent_asset_id) {
    this.parent_asset_id = parent_asset_id;
    return this;
  }
  private String name;
  public String get_name() {
    return name;
  }
  public void set_name(String name) {
    this.name = name;
  }
  public QueryResult with_name(String name) {
    this.name = name;
    return this;
  }
  private String description;
  public String get_description() {
    return description;
  }
  public void set_description(String description) {
    this.description = description;
  }
  public QueryResult with_description(String description) {
    this.description = description;
    return this;
  }
  private String category;
  public String get_category() {
    return category;
  }
  public void set_category(String category) {
    this.category = category;
  }
  public QueryResult with_category(String category) {
    this.category = category;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.member_id == null ? that.member_id == null : this.member_id.equals(that.member_id));
    equal = equal && (this.item_id == null ? that.item_id == null : this.item_id.equals(that.item_id));
    equal = equal && (this.play_duration == null ? that.play_duration == null : this.play_duration.equals(that.play_duration));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.create_date == null ? that.create_date == null : this.create_date.equals(that.create_date));
    equal = equal && (this.parent_asset_id == null ? that.parent_asset_id == null : this.parent_asset_id.equals(that.parent_asset_id));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.description == null ? that.description == null : this.description.equals(that.description));
    equal = equal && (this.category == null ? that.category == null : this.category.equals(that.category));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.member_id == null ? that.member_id == null : this.member_id.equals(that.member_id));
    equal = equal && (this.item_id == null ? that.item_id == null : this.item_id.equals(that.item_id));
    equal = equal && (this.play_duration == null ? that.play_duration == null : this.play_duration.equals(that.play_duration));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.create_date == null ? that.create_date == null : this.create_date.equals(that.create_date));
    equal = equal && (this.parent_asset_id == null ? that.parent_asset_id == null : this.parent_asset_id.equals(that.parent_asset_id));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.description == null ? that.description == null : this.description.equals(that.description));
    equal = equal && (this.category == null ? that.category == null : this.category.equals(that.category));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.member_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.item_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.play_duration = JdbcWritableBridge.readInteger(3, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.create_date = JdbcWritableBridge.readString(5, __dbResults);
    this.parent_asset_id = JdbcWritableBridge.readString(6, __dbResults);
    this.name = JdbcWritableBridge.readString(7, __dbResults);
    this.description = JdbcWritableBridge.readString(8, __dbResults);
    this.category = JdbcWritableBridge.readString(9, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.member_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.item_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.play_duration = JdbcWritableBridge.readInteger(3, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.create_date = JdbcWritableBridge.readString(5, __dbResults);
    this.parent_asset_id = JdbcWritableBridge.readString(6, __dbResults);
    this.name = JdbcWritableBridge.readString(7, __dbResults);
    this.description = JdbcWritableBridge.readString(8, __dbResults);
    this.category = JdbcWritableBridge.readString(9, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(member_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(item_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(play_duration, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(create_date, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(parent_asset_id, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(description, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(category, 9 + __off, 12, __dbStmt);
    return 9;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(member_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(item_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(play_duration, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(create_date, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(parent_asset_id, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(description, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(category, 9 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.member_id = null;
    } else {
    this.member_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.item_id = null;
    } else {
    this.item_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.play_duration = null;
    } else {
    this.play_duration = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.create_time = null;
    } else {
    this.create_time = new Timestamp(__dataIn.readLong());
    this.create_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.create_date = null;
    } else {
    this.create_date = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.parent_asset_id = null;
    } else {
    this.parent_asset_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.name = null;
    } else {
    this.name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.description = null;
    } else {
    this.description = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.category = null;
    } else {
    this.category = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.member_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.member_id);
    }
    if (null == this.item_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.item_id);
    }
    if (null == this.play_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.play_duration);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.create_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, create_date);
    }
    if (null == this.parent_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, parent_asset_id);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, description);
    }
    if (null == this.category) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, category);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.member_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.member_id);
    }
    if (null == this.item_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.item_id);
    }
    if (null == this.play_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.play_duration);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.create_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, create_date);
    }
    if (null == this.parent_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, parent_asset_id);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, description);
    }
    if (null == this.category) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, category);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(member_id==null?"\\N":"" + member_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(item_id==null?"\\N":"" + item_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(play_duration==null?"\\N":"" + play_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"\\N":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_date==null?"\\N":create_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(parent_asset_id==null?"\\N":parent_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(name==null?"\\N":name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(description==null?"\\N":description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(category==null?"\\N":category, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(member_id==null?"\\N":"" + member_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(item_id==null?"\\N":"" + item_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(play_duration==null?"\\N":"" + play_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"\\N":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_date==null?"\\N":create_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(parent_asset_id==null?"\\N":parent_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(name==null?"\\N":name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(description==null?"\\N":description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(category==null?"\\N":category, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.member_id = null; } else {
      this.member_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.item_id = null; } else {
      this.item_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.play_duration = null; } else {
      this.play_duration = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_time = null; } else {
      this.create_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.create_date = null; } else {
      this.create_date = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.parent_asset_id = null; } else {
      this.parent_asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.description = null; } else {
      this.description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.category = null; } else {
      this.category = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.member_id = null; } else {
      this.member_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.item_id = null; } else {
      this.item_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.play_duration = null; } else {
      this.play_duration = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_time = null; } else {
      this.create_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.create_date = null; } else {
      this.create_date = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.parent_asset_id = null; } else {
      this.parent_asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.description = null; } else {
      this.description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.category = null; } else {
      this.category = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    QueryResult o = (QueryResult) super.clone();
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
    return o;
  }

  public void clone0(QueryResult o) throws CloneNotSupportedException {
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("member_id", this.member_id);
    __sqoop$field_map.put("item_id", this.item_id);
    __sqoop$field_map.put("play_duration", this.play_duration);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("create_date", this.create_date);
    __sqoop$field_map.put("parent_asset_id", this.parent_asset_id);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("description", this.description);
    __sqoop$field_map.put("category", this.category);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("member_id", this.member_id);
    __sqoop$field_map.put("item_id", this.item_id);
    __sqoop$field_map.put("play_duration", this.play_duration);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("create_date", this.create_date);
    __sqoop$field_map.put("parent_asset_id", this.parent_asset_id);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("description", this.description);
    __sqoop$field_map.put("category", this.category);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
