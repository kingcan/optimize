// ORM class for table 'bo_video_content'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu May 13 22:21:18 GMT+08:00 2021
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

public class bo_video_content extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.id = (Integer)value;
      }
    });
    setters.put("asset_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.asset_id = (String)value;
      }
    });
    setters.put("originalid", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.originalid = (Integer)value;
      }
    });
    setters.put("parent_asset_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.parent_asset_id = (String)value;
      }
    });
    setters.put("provider_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.provider_code = (String)value;
      }
    });
    setters.put("type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.type = (String)value;
      }
    });
    setters.put("list_sort", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.list_sort = (Integer)value;
      }
    });
    setters.put("episode_updated", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.episode_updated = (Integer)value;
      }
    });
    setters.put("episode_total", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.episode_total = (Integer)value;
      }
    });
    setters.put("director_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.director_id = (String)value;
      }
    });
    setters.put("actor_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.actor_id = (String)value;
      }
    });
    setters.put("writer_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.writer_id = (String)value;
      }
    });
    setters.put("category_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.category_id = (String)value;
      }
    });
    setters.put("online_year_tag_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.online_year_tag_id = (String)value;
      }
    });
    setters.put("area_tag_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.area_tag_id = (String)value;
      }
    });
    setters.put("language_tag_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.language_tag_id = (String)value;
      }
    });
    setters.put("hit_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.hit_count = (Long)value;
      }
    });
    setters.put("likeCount", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.likeCount = (Integer)value;
      }
    });
    setters.put("shareCount", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.shareCount = (Integer)value;
      }
    });
    setters.put("device_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.device_type = (String)value;
      }
    });
    setters.put("grade", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.grade = (Integer)value;
      }
    });
    setters.put("star", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.star = (String)value;
      }
    });
    setters.put("tags_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.tags_id = (String)value;
      }
    });
    setters.put("duration", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.duration = (Long)value;
      }
    });
    setters.put("title_duration", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.title_duration = (Long)value;
      }
    });
    setters.put("end_duration", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.end_duration = (Long)value;
      }
    });
    setters.put("is_parent_lock", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_parent_lock = (String)value;
      }
    });
    setters.put("is_ads", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_ads = (String)value;
      }
    });
    setters.put("is_preview", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_preview = (String)value;
      }
    });
    setters.put("seasons_asset_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.seasons_asset_id = (String)value;
      }
    });
    setters.put("seasons_total_num", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.seasons_total_num = (Integer)value;
      }
    });
    setters.put("mark_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.mark_id = (String)value;
      }
    });
    setters.put("template_model", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.template_model = (String)value;
      }
    });
    setters.put("resource_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.resource_id = (Integer)value;
      }
    });
    setters.put("onshelf", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.onshelf = (String)value;
      }
    });
    setters.put("audit_status", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.audit_status = (String)value;
      }
    });
    setters.put("audit_description", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.audit_description = (String)value;
      }
    });
    setters.put("ads_list", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.ads_list = (String)value;
      }
    });
    setters.put("audio_track", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.audio_track = (String)value;
      }
    });
    setters.put("is_trash", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_trash = (String)value;
      }
    });
    setters.put("is_download", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_download = (String)value;
      }
    });
    setters.put("user_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.user_id = (Integer)value;
      }
    });
    setters.put("create_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.create_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("update_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.update_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("start_datetime", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.start_datetime = (java.sql.Timestamp)value;
      }
    });
    setters.put("end_datetime", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.end_datetime = (java.sql.Timestamp)value;
      }
    });
    setters.put("play_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.play_count = (Integer)value;
      }
    });
    setters.put("movie_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.movie_id = (Integer)value;
      }
    });
    setters.put("is_fee", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.is_fee = (String)value;
      }
    });
    setters.put("product_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.product_id = (String)value;
      }
    });
    setters.put("intact", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        bo_video_content.this.intact = (Integer)value;
      }
    });
  }
  public bo_video_content() {
    init0();
  }
  private Integer id;
  public Integer get_id() {
    return id;
  }
  public void set_id(Integer id) {
    this.id = id;
  }
  public bo_video_content with_id(Integer id) {
    this.id = id;
    return this;
  }
  private String asset_id;
  public String get_asset_id() {
    return asset_id;
  }
  public void set_asset_id(String asset_id) {
    this.asset_id = asset_id;
  }
  public bo_video_content with_asset_id(String asset_id) {
    this.asset_id = asset_id;
    return this;
  }
  private Integer originalid;
  public Integer get_originalid() {
    return originalid;
  }
  public void set_originalid(Integer originalid) {
    this.originalid = originalid;
  }
  public bo_video_content with_originalid(Integer originalid) {
    this.originalid = originalid;
    return this;
  }
  private String parent_asset_id;
  public String get_parent_asset_id() {
    return parent_asset_id;
  }
  public void set_parent_asset_id(String parent_asset_id) {
    this.parent_asset_id = parent_asset_id;
  }
  public bo_video_content with_parent_asset_id(String parent_asset_id) {
    this.parent_asset_id = parent_asset_id;
    return this;
  }
  private String provider_code;
  public String get_provider_code() {
    return provider_code;
  }
  public void set_provider_code(String provider_code) {
    this.provider_code = provider_code;
  }
  public bo_video_content with_provider_code(String provider_code) {
    this.provider_code = provider_code;
    return this;
  }
  private String type;
  public String get_type() {
    return type;
  }
  public void set_type(String type) {
    this.type = type;
  }
  public bo_video_content with_type(String type) {
    this.type = type;
    return this;
  }
  private Integer list_sort;
  public Integer get_list_sort() {
    return list_sort;
  }
  public void set_list_sort(Integer list_sort) {
    this.list_sort = list_sort;
  }
  public bo_video_content with_list_sort(Integer list_sort) {
    this.list_sort = list_sort;
    return this;
  }
  private Integer episode_updated;
  public Integer get_episode_updated() {
    return episode_updated;
  }
  public void set_episode_updated(Integer episode_updated) {
    this.episode_updated = episode_updated;
  }
  public bo_video_content with_episode_updated(Integer episode_updated) {
    this.episode_updated = episode_updated;
    return this;
  }
  private Integer episode_total;
  public Integer get_episode_total() {
    return episode_total;
  }
  public void set_episode_total(Integer episode_total) {
    this.episode_total = episode_total;
  }
  public bo_video_content with_episode_total(Integer episode_total) {
    this.episode_total = episode_total;
    return this;
  }
  private String director_id;
  public String get_director_id() {
    return director_id;
  }
  public void set_director_id(String director_id) {
    this.director_id = director_id;
  }
  public bo_video_content with_director_id(String director_id) {
    this.director_id = director_id;
    return this;
  }
  private String actor_id;
  public String get_actor_id() {
    return actor_id;
  }
  public void set_actor_id(String actor_id) {
    this.actor_id = actor_id;
  }
  public bo_video_content with_actor_id(String actor_id) {
    this.actor_id = actor_id;
    return this;
  }
  private String writer_id;
  public String get_writer_id() {
    return writer_id;
  }
  public void set_writer_id(String writer_id) {
    this.writer_id = writer_id;
  }
  public bo_video_content with_writer_id(String writer_id) {
    this.writer_id = writer_id;
    return this;
  }
  private String category_id;
  public String get_category_id() {
    return category_id;
  }
  public void set_category_id(String category_id) {
    this.category_id = category_id;
  }
  public bo_video_content with_category_id(String category_id) {
    this.category_id = category_id;
    return this;
  }
  private String online_year_tag_id;
  public String get_online_year_tag_id() {
    return online_year_tag_id;
  }
  public void set_online_year_tag_id(String online_year_tag_id) {
    this.online_year_tag_id = online_year_tag_id;
  }
  public bo_video_content with_online_year_tag_id(String online_year_tag_id) {
    this.online_year_tag_id = online_year_tag_id;
    return this;
  }
  private String area_tag_id;
  public String get_area_tag_id() {
    return area_tag_id;
  }
  public void set_area_tag_id(String area_tag_id) {
    this.area_tag_id = area_tag_id;
  }
  public bo_video_content with_area_tag_id(String area_tag_id) {
    this.area_tag_id = area_tag_id;
    return this;
  }
  private String language_tag_id;
  public String get_language_tag_id() {
    return language_tag_id;
  }
  public void set_language_tag_id(String language_tag_id) {
    this.language_tag_id = language_tag_id;
  }
  public bo_video_content with_language_tag_id(String language_tag_id) {
    this.language_tag_id = language_tag_id;
    return this;
  }
  private Long hit_count;
  public Long get_hit_count() {
    return hit_count;
  }
  public void set_hit_count(Long hit_count) {
    this.hit_count = hit_count;
  }
  public bo_video_content with_hit_count(Long hit_count) {
    this.hit_count = hit_count;
    return this;
  }
  private Integer likeCount;
  public Integer get_likeCount() {
    return likeCount;
  }
  public void set_likeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }
  public bo_video_content with_likeCount(Integer likeCount) {
    this.likeCount = likeCount;
    return this;
  }
  private Integer shareCount;
  public Integer get_shareCount() {
    return shareCount;
  }
  public void set_shareCount(Integer shareCount) {
    this.shareCount = shareCount;
  }
  public bo_video_content with_shareCount(Integer shareCount) {
    this.shareCount = shareCount;
    return this;
  }
  private String device_type;
  public String get_device_type() {
    return device_type;
  }
  public void set_device_type(String device_type) {
    this.device_type = device_type;
  }
  public bo_video_content with_device_type(String device_type) {
    this.device_type = device_type;
    return this;
  }
  private Integer grade;
  public Integer get_grade() {
    return grade;
  }
  public void set_grade(Integer grade) {
    this.grade = grade;
  }
  public bo_video_content with_grade(Integer grade) {
    this.grade = grade;
    return this;
  }
  private String star;
  public String get_star() {
    return star;
  }
  public void set_star(String star) {
    this.star = star;
  }
  public bo_video_content with_star(String star) {
    this.star = star;
    return this;
  }
  private String tags_id;
  public String get_tags_id() {
    return tags_id;
  }
  public void set_tags_id(String tags_id) {
    this.tags_id = tags_id;
  }
  public bo_video_content with_tags_id(String tags_id) {
    this.tags_id = tags_id;
    return this;
  }
  private Long duration;
  public Long get_duration() {
    return duration;
  }
  public void set_duration(Long duration) {
    this.duration = duration;
  }
  public bo_video_content with_duration(Long duration) {
    this.duration = duration;
    return this;
  }
  private Long title_duration;
  public Long get_title_duration() {
    return title_duration;
  }
  public void set_title_duration(Long title_duration) {
    this.title_duration = title_duration;
  }
  public bo_video_content with_title_duration(Long title_duration) {
    this.title_duration = title_duration;
    return this;
  }
  private Long end_duration;
  public Long get_end_duration() {
    return end_duration;
  }
  public void set_end_duration(Long end_duration) {
    this.end_duration = end_duration;
  }
  public bo_video_content with_end_duration(Long end_duration) {
    this.end_duration = end_duration;
    return this;
  }
  private String is_parent_lock;
  public String get_is_parent_lock() {
    return is_parent_lock;
  }
  public void set_is_parent_lock(String is_parent_lock) {
    this.is_parent_lock = is_parent_lock;
  }
  public bo_video_content with_is_parent_lock(String is_parent_lock) {
    this.is_parent_lock = is_parent_lock;
    return this;
  }
  private String is_ads;
  public String get_is_ads() {
    return is_ads;
  }
  public void set_is_ads(String is_ads) {
    this.is_ads = is_ads;
  }
  public bo_video_content with_is_ads(String is_ads) {
    this.is_ads = is_ads;
    return this;
  }
  private String is_preview;
  public String get_is_preview() {
    return is_preview;
  }
  public void set_is_preview(String is_preview) {
    this.is_preview = is_preview;
  }
  public bo_video_content with_is_preview(String is_preview) {
    this.is_preview = is_preview;
    return this;
  }
  private String seasons_asset_id;
  public String get_seasons_asset_id() {
    return seasons_asset_id;
  }
  public void set_seasons_asset_id(String seasons_asset_id) {
    this.seasons_asset_id = seasons_asset_id;
  }
  public bo_video_content with_seasons_asset_id(String seasons_asset_id) {
    this.seasons_asset_id = seasons_asset_id;
    return this;
  }
  private Integer seasons_total_num;
  public Integer get_seasons_total_num() {
    return seasons_total_num;
  }
  public void set_seasons_total_num(Integer seasons_total_num) {
    this.seasons_total_num = seasons_total_num;
  }
  public bo_video_content with_seasons_total_num(Integer seasons_total_num) {
    this.seasons_total_num = seasons_total_num;
    return this;
  }
  private String mark_id;
  public String get_mark_id() {
    return mark_id;
  }
  public void set_mark_id(String mark_id) {
    this.mark_id = mark_id;
  }
  public bo_video_content with_mark_id(String mark_id) {
    this.mark_id = mark_id;
    return this;
  }
  private String template_model;
  public String get_template_model() {
    return template_model;
  }
  public void set_template_model(String template_model) {
    this.template_model = template_model;
  }
  public bo_video_content with_template_model(String template_model) {
    this.template_model = template_model;
    return this;
  }
  private Integer resource_id;
  public Integer get_resource_id() {
    return resource_id;
  }
  public void set_resource_id(Integer resource_id) {
    this.resource_id = resource_id;
  }
  public bo_video_content with_resource_id(Integer resource_id) {
    this.resource_id = resource_id;
    return this;
  }
  private String onshelf;
  public String get_onshelf() {
    return onshelf;
  }
  public void set_onshelf(String onshelf) {
    this.onshelf = onshelf;
  }
  public bo_video_content with_onshelf(String onshelf) {
    this.onshelf = onshelf;
    return this;
  }
  private String audit_status;
  public String get_audit_status() {
    return audit_status;
  }
  public void set_audit_status(String audit_status) {
    this.audit_status = audit_status;
  }
  public bo_video_content with_audit_status(String audit_status) {
    this.audit_status = audit_status;
    return this;
  }
  private String audit_description;
  public String get_audit_description() {
    return audit_description;
  }
  public void set_audit_description(String audit_description) {
    this.audit_description = audit_description;
  }
  public bo_video_content with_audit_description(String audit_description) {
    this.audit_description = audit_description;
    return this;
  }
  private String ads_list;
  public String get_ads_list() {
    return ads_list;
  }
  public void set_ads_list(String ads_list) {
    this.ads_list = ads_list;
  }
  public bo_video_content with_ads_list(String ads_list) {
    this.ads_list = ads_list;
    return this;
  }
  private String audio_track;
  public String get_audio_track() {
    return audio_track;
  }
  public void set_audio_track(String audio_track) {
    this.audio_track = audio_track;
  }
  public bo_video_content with_audio_track(String audio_track) {
    this.audio_track = audio_track;
    return this;
  }
  private String is_trash;
  public String get_is_trash() {
    return is_trash;
  }
  public void set_is_trash(String is_trash) {
    this.is_trash = is_trash;
  }
  public bo_video_content with_is_trash(String is_trash) {
    this.is_trash = is_trash;
    return this;
  }
  private String is_download;
  public String get_is_download() {
    return is_download;
  }
  public void set_is_download(String is_download) {
    this.is_download = is_download;
  }
  public bo_video_content with_is_download(String is_download) {
    this.is_download = is_download;
    return this;
  }
  private Integer user_id;
  public Integer get_user_id() {
    return user_id;
  }
  public void set_user_id(Integer user_id) {
    this.user_id = user_id;
  }
  public bo_video_content with_user_id(Integer user_id) {
    this.user_id = user_id;
    return this;
  }
  private java.sql.Timestamp create_time;
  public java.sql.Timestamp get_create_time() {
    return create_time;
  }
  public void set_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
  public bo_video_content with_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
    return this;
  }
  private java.sql.Timestamp update_time;
  public java.sql.Timestamp get_update_time() {
    return update_time;
  }
  public void set_update_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }
  public bo_video_content with_update_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
    return this;
  }
  private java.sql.Timestamp start_datetime;
  public java.sql.Timestamp get_start_datetime() {
    return start_datetime;
  }
  public void set_start_datetime(java.sql.Timestamp start_datetime) {
    this.start_datetime = start_datetime;
  }
  public bo_video_content with_start_datetime(java.sql.Timestamp start_datetime) {
    this.start_datetime = start_datetime;
    return this;
  }
  private java.sql.Timestamp end_datetime;
  public java.sql.Timestamp get_end_datetime() {
    return end_datetime;
  }
  public void set_end_datetime(java.sql.Timestamp end_datetime) {
    this.end_datetime = end_datetime;
  }
  public bo_video_content with_end_datetime(java.sql.Timestamp end_datetime) {
    this.end_datetime = end_datetime;
    return this;
  }
  private Integer play_count;
  public Integer get_play_count() {
    return play_count;
  }
  public void set_play_count(Integer play_count) {
    this.play_count = play_count;
  }
  public bo_video_content with_play_count(Integer play_count) {
    this.play_count = play_count;
    return this;
  }
  private Integer movie_id;
  public Integer get_movie_id() {
    return movie_id;
  }
  public void set_movie_id(Integer movie_id) {
    this.movie_id = movie_id;
  }
  public bo_video_content with_movie_id(Integer movie_id) {
    this.movie_id = movie_id;
    return this;
  }
  private String is_fee;
  public String get_is_fee() {
    return is_fee;
  }
  public void set_is_fee(String is_fee) {
    this.is_fee = is_fee;
  }
  public bo_video_content with_is_fee(String is_fee) {
    this.is_fee = is_fee;
    return this;
  }
  private String product_id;
  public String get_product_id() {
    return product_id;
  }
  public void set_product_id(String product_id) {
    this.product_id = product_id;
  }
  public bo_video_content with_product_id(String product_id) {
    this.product_id = product_id;
    return this;
  }
  private Integer intact;
  public Integer get_intact() {
    return intact;
  }
  public void set_intact(Integer intact) {
    this.intact = intact;
  }
  public bo_video_content with_intact(Integer intact) {
    this.intact = intact;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof bo_video_content)) {
      return false;
    }
    bo_video_content that = (bo_video_content) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.asset_id == null ? that.asset_id == null : this.asset_id.equals(that.asset_id));
    equal = equal && (this.originalid == null ? that.originalid == null : this.originalid.equals(that.originalid));
    equal = equal && (this.parent_asset_id == null ? that.parent_asset_id == null : this.parent_asset_id.equals(that.parent_asset_id));
    equal = equal && (this.provider_code == null ? that.provider_code == null : this.provider_code.equals(that.provider_code));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    equal = equal && (this.list_sort == null ? that.list_sort == null : this.list_sort.equals(that.list_sort));
    equal = equal && (this.episode_updated == null ? that.episode_updated == null : this.episode_updated.equals(that.episode_updated));
    equal = equal && (this.episode_total == null ? that.episode_total == null : this.episode_total.equals(that.episode_total));
    equal = equal && (this.director_id == null ? that.director_id == null : this.director_id.equals(that.director_id));
    equal = equal && (this.actor_id == null ? that.actor_id == null : this.actor_id.equals(that.actor_id));
    equal = equal && (this.writer_id == null ? that.writer_id == null : this.writer_id.equals(that.writer_id));
    equal = equal && (this.category_id == null ? that.category_id == null : this.category_id.equals(that.category_id));
    equal = equal && (this.online_year_tag_id == null ? that.online_year_tag_id == null : this.online_year_tag_id.equals(that.online_year_tag_id));
    equal = equal && (this.area_tag_id == null ? that.area_tag_id == null : this.area_tag_id.equals(that.area_tag_id));
    equal = equal && (this.language_tag_id == null ? that.language_tag_id == null : this.language_tag_id.equals(that.language_tag_id));
    equal = equal && (this.hit_count == null ? that.hit_count == null : this.hit_count.equals(that.hit_count));
    equal = equal && (this.likeCount == null ? that.likeCount == null : this.likeCount.equals(that.likeCount));
    equal = equal && (this.shareCount == null ? that.shareCount == null : this.shareCount.equals(that.shareCount));
    equal = equal && (this.device_type == null ? that.device_type == null : this.device_type.equals(that.device_type));
    equal = equal && (this.grade == null ? that.grade == null : this.grade.equals(that.grade));
    equal = equal && (this.star == null ? that.star == null : this.star.equals(that.star));
    equal = equal && (this.tags_id == null ? that.tags_id == null : this.tags_id.equals(that.tags_id));
    equal = equal && (this.duration == null ? that.duration == null : this.duration.equals(that.duration));
    equal = equal && (this.title_duration == null ? that.title_duration == null : this.title_duration.equals(that.title_duration));
    equal = equal && (this.end_duration == null ? that.end_duration == null : this.end_duration.equals(that.end_duration));
    equal = equal && (this.is_parent_lock == null ? that.is_parent_lock == null : this.is_parent_lock.equals(that.is_parent_lock));
    equal = equal && (this.is_ads == null ? that.is_ads == null : this.is_ads.equals(that.is_ads));
    equal = equal && (this.is_preview == null ? that.is_preview == null : this.is_preview.equals(that.is_preview));
    equal = equal && (this.seasons_asset_id == null ? that.seasons_asset_id == null : this.seasons_asset_id.equals(that.seasons_asset_id));
    equal = equal && (this.seasons_total_num == null ? that.seasons_total_num == null : this.seasons_total_num.equals(that.seasons_total_num));
    equal = equal && (this.mark_id == null ? that.mark_id == null : this.mark_id.equals(that.mark_id));
    equal = equal && (this.template_model == null ? that.template_model == null : this.template_model.equals(that.template_model));
    equal = equal && (this.resource_id == null ? that.resource_id == null : this.resource_id.equals(that.resource_id));
    equal = equal && (this.onshelf == null ? that.onshelf == null : this.onshelf.equals(that.onshelf));
    equal = equal && (this.audit_status == null ? that.audit_status == null : this.audit_status.equals(that.audit_status));
    equal = equal && (this.audit_description == null ? that.audit_description == null : this.audit_description.equals(that.audit_description));
    equal = equal && (this.ads_list == null ? that.ads_list == null : this.ads_list.equals(that.ads_list));
    equal = equal && (this.audio_track == null ? that.audio_track == null : this.audio_track.equals(that.audio_track));
    equal = equal && (this.is_trash == null ? that.is_trash == null : this.is_trash.equals(that.is_trash));
    equal = equal && (this.is_download == null ? that.is_download == null : this.is_download.equals(that.is_download));
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.update_time == null ? that.update_time == null : this.update_time.equals(that.update_time));
    equal = equal && (this.start_datetime == null ? that.start_datetime == null : this.start_datetime.equals(that.start_datetime));
    equal = equal && (this.end_datetime == null ? that.end_datetime == null : this.end_datetime.equals(that.end_datetime));
    equal = equal && (this.play_count == null ? that.play_count == null : this.play_count.equals(that.play_count));
    equal = equal && (this.movie_id == null ? that.movie_id == null : this.movie_id.equals(that.movie_id));
    equal = equal && (this.is_fee == null ? that.is_fee == null : this.is_fee.equals(that.is_fee));
    equal = equal && (this.product_id == null ? that.product_id == null : this.product_id.equals(that.product_id));
    equal = equal && (this.intact == null ? that.intact == null : this.intact.equals(that.intact));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof bo_video_content)) {
      return false;
    }
    bo_video_content that = (bo_video_content) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.asset_id == null ? that.asset_id == null : this.asset_id.equals(that.asset_id));
    equal = equal && (this.originalid == null ? that.originalid == null : this.originalid.equals(that.originalid));
    equal = equal && (this.parent_asset_id == null ? that.parent_asset_id == null : this.parent_asset_id.equals(that.parent_asset_id));
    equal = equal && (this.provider_code == null ? that.provider_code == null : this.provider_code.equals(that.provider_code));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    equal = equal && (this.list_sort == null ? that.list_sort == null : this.list_sort.equals(that.list_sort));
    equal = equal && (this.episode_updated == null ? that.episode_updated == null : this.episode_updated.equals(that.episode_updated));
    equal = equal && (this.episode_total == null ? that.episode_total == null : this.episode_total.equals(that.episode_total));
    equal = equal && (this.director_id == null ? that.director_id == null : this.director_id.equals(that.director_id));
    equal = equal && (this.actor_id == null ? that.actor_id == null : this.actor_id.equals(that.actor_id));
    equal = equal && (this.writer_id == null ? that.writer_id == null : this.writer_id.equals(that.writer_id));
    equal = equal && (this.category_id == null ? that.category_id == null : this.category_id.equals(that.category_id));
    equal = equal && (this.online_year_tag_id == null ? that.online_year_tag_id == null : this.online_year_tag_id.equals(that.online_year_tag_id));
    equal = equal && (this.area_tag_id == null ? that.area_tag_id == null : this.area_tag_id.equals(that.area_tag_id));
    equal = equal && (this.language_tag_id == null ? that.language_tag_id == null : this.language_tag_id.equals(that.language_tag_id));
    equal = equal && (this.hit_count == null ? that.hit_count == null : this.hit_count.equals(that.hit_count));
    equal = equal && (this.likeCount == null ? that.likeCount == null : this.likeCount.equals(that.likeCount));
    equal = equal && (this.shareCount == null ? that.shareCount == null : this.shareCount.equals(that.shareCount));
    equal = equal && (this.device_type == null ? that.device_type == null : this.device_type.equals(that.device_type));
    equal = equal && (this.grade == null ? that.grade == null : this.grade.equals(that.grade));
    equal = equal && (this.star == null ? that.star == null : this.star.equals(that.star));
    equal = equal && (this.tags_id == null ? that.tags_id == null : this.tags_id.equals(that.tags_id));
    equal = equal && (this.duration == null ? that.duration == null : this.duration.equals(that.duration));
    equal = equal && (this.title_duration == null ? that.title_duration == null : this.title_duration.equals(that.title_duration));
    equal = equal && (this.end_duration == null ? that.end_duration == null : this.end_duration.equals(that.end_duration));
    equal = equal && (this.is_parent_lock == null ? that.is_parent_lock == null : this.is_parent_lock.equals(that.is_parent_lock));
    equal = equal && (this.is_ads == null ? that.is_ads == null : this.is_ads.equals(that.is_ads));
    equal = equal && (this.is_preview == null ? that.is_preview == null : this.is_preview.equals(that.is_preview));
    equal = equal && (this.seasons_asset_id == null ? that.seasons_asset_id == null : this.seasons_asset_id.equals(that.seasons_asset_id));
    equal = equal && (this.seasons_total_num == null ? that.seasons_total_num == null : this.seasons_total_num.equals(that.seasons_total_num));
    equal = equal && (this.mark_id == null ? that.mark_id == null : this.mark_id.equals(that.mark_id));
    equal = equal && (this.template_model == null ? that.template_model == null : this.template_model.equals(that.template_model));
    equal = equal && (this.resource_id == null ? that.resource_id == null : this.resource_id.equals(that.resource_id));
    equal = equal && (this.onshelf == null ? that.onshelf == null : this.onshelf.equals(that.onshelf));
    equal = equal && (this.audit_status == null ? that.audit_status == null : this.audit_status.equals(that.audit_status));
    equal = equal && (this.audit_description == null ? that.audit_description == null : this.audit_description.equals(that.audit_description));
    equal = equal && (this.ads_list == null ? that.ads_list == null : this.ads_list.equals(that.ads_list));
    equal = equal && (this.audio_track == null ? that.audio_track == null : this.audio_track.equals(that.audio_track));
    equal = equal && (this.is_trash == null ? that.is_trash == null : this.is_trash.equals(that.is_trash));
    equal = equal && (this.is_download == null ? that.is_download == null : this.is_download.equals(that.is_download));
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.update_time == null ? that.update_time == null : this.update_time.equals(that.update_time));
    equal = equal && (this.start_datetime == null ? that.start_datetime == null : this.start_datetime.equals(that.start_datetime));
    equal = equal && (this.end_datetime == null ? that.end_datetime == null : this.end_datetime.equals(that.end_datetime));
    equal = equal && (this.play_count == null ? that.play_count == null : this.play_count.equals(that.play_count));
    equal = equal && (this.movie_id == null ? that.movie_id == null : this.movie_id.equals(that.movie_id));
    equal = equal && (this.is_fee == null ? that.is_fee == null : this.is_fee.equals(that.is_fee));
    equal = equal && (this.product_id == null ? that.product_id == null : this.product_id.equals(that.product_id));
    equal = equal && (this.intact == null ? that.intact == null : this.intact.equals(that.intact));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.asset_id = JdbcWritableBridge.readString(2, __dbResults);
    this.originalid = JdbcWritableBridge.readInteger(3, __dbResults);
    this.parent_asset_id = JdbcWritableBridge.readString(4, __dbResults);
    this.provider_code = JdbcWritableBridge.readString(5, __dbResults);
    this.type = JdbcWritableBridge.readString(6, __dbResults);
    this.list_sort = JdbcWritableBridge.readInteger(7, __dbResults);
    this.episode_updated = JdbcWritableBridge.readInteger(8, __dbResults);
    this.episode_total = JdbcWritableBridge.readInteger(9, __dbResults);
    this.director_id = JdbcWritableBridge.readString(10, __dbResults);
    this.actor_id = JdbcWritableBridge.readString(11, __dbResults);
    this.writer_id = JdbcWritableBridge.readString(12, __dbResults);
    this.category_id = JdbcWritableBridge.readString(13, __dbResults);
    this.online_year_tag_id = JdbcWritableBridge.readString(14, __dbResults);
    this.area_tag_id = JdbcWritableBridge.readString(15, __dbResults);
    this.language_tag_id = JdbcWritableBridge.readString(16, __dbResults);
    this.hit_count = JdbcWritableBridge.readLong(17, __dbResults);
    this.likeCount = JdbcWritableBridge.readInteger(18, __dbResults);
    this.shareCount = JdbcWritableBridge.readInteger(19, __dbResults);
    this.device_type = JdbcWritableBridge.readString(20, __dbResults);
    this.grade = JdbcWritableBridge.readInteger(21, __dbResults);
    this.star = JdbcWritableBridge.readString(22, __dbResults);
    this.tags_id = JdbcWritableBridge.readString(23, __dbResults);
    this.duration = JdbcWritableBridge.readLong(24, __dbResults);
    this.title_duration = JdbcWritableBridge.readLong(25, __dbResults);
    this.end_duration = JdbcWritableBridge.readLong(26, __dbResults);
    this.is_parent_lock = JdbcWritableBridge.readString(27, __dbResults);
    this.is_ads = JdbcWritableBridge.readString(28, __dbResults);
    this.is_preview = JdbcWritableBridge.readString(29, __dbResults);
    this.seasons_asset_id = JdbcWritableBridge.readString(30, __dbResults);
    this.seasons_total_num = JdbcWritableBridge.readInteger(31, __dbResults);
    this.mark_id = JdbcWritableBridge.readString(32, __dbResults);
    this.template_model = JdbcWritableBridge.readString(33, __dbResults);
    this.resource_id = JdbcWritableBridge.readInteger(34, __dbResults);
    this.onshelf = JdbcWritableBridge.readString(35, __dbResults);
    this.audit_status = JdbcWritableBridge.readString(36, __dbResults);
    this.audit_description = JdbcWritableBridge.readString(37, __dbResults);
    this.ads_list = JdbcWritableBridge.readString(38, __dbResults);
    this.audio_track = JdbcWritableBridge.readString(39, __dbResults);
    this.is_trash = JdbcWritableBridge.readString(40, __dbResults);
    this.is_download = JdbcWritableBridge.readString(41, __dbResults);
    this.user_id = JdbcWritableBridge.readInteger(42, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(43, __dbResults);
    this.update_time = JdbcWritableBridge.readTimestamp(44, __dbResults);
    this.start_datetime = JdbcWritableBridge.readTimestamp(45, __dbResults);
    this.end_datetime = JdbcWritableBridge.readTimestamp(46, __dbResults);
    this.play_count = JdbcWritableBridge.readInteger(47, __dbResults);
    this.movie_id = JdbcWritableBridge.readInteger(48, __dbResults);
    this.is_fee = JdbcWritableBridge.readString(49, __dbResults);
    this.product_id = JdbcWritableBridge.readString(50, __dbResults);
    this.intact = JdbcWritableBridge.readInteger(51, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.asset_id = JdbcWritableBridge.readString(2, __dbResults);
    this.originalid = JdbcWritableBridge.readInteger(3, __dbResults);
    this.parent_asset_id = JdbcWritableBridge.readString(4, __dbResults);
    this.provider_code = JdbcWritableBridge.readString(5, __dbResults);
    this.type = JdbcWritableBridge.readString(6, __dbResults);
    this.list_sort = JdbcWritableBridge.readInteger(7, __dbResults);
    this.episode_updated = JdbcWritableBridge.readInteger(8, __dbResults);
    this.episode_total = JdbcWritableBridge.readInteger(9, __dbResults);
    this.director_id = JdbcWritableBridge.readString(10, __dbResults);
    this.actor_id = JdbcWritableBridge.readString(11, __dbResults);
    this.writer_id = JdbcWritableBridge.readString(12, __dbResults);
    this.category_id = JdbcWritableBridge.readString(13, __dbResults);
    this.online_year_tag_id = JdbcWritableBridge.readString(14, __dbResults);
    this.area_tag_id = JdbcWritableBridge.readString(15, __dbResults);
    this.language_tag_id = JdbcWritableBridge.readString(16, __dbResults);
    this.hit_count = JdbcWritableBridge.readLong(17, __dbResults);
    this.likeCount = JdbcWritableBridge.readInteger(18, __dbResults);
    this.shareCount = JdbcWritableBridge.readInteger(19, __dbResults);
    this.device_type = JdbcWritableBridge.readString(20, __dbResults);
    this.grade = JdbcWritableBridge.readInteger(21, __dbResults);
    this.star = JdbcWritableBridge.readString(22, __dbResults);
    this.tags_id = JdbcWritableBridge.readString(23, __dbResults);
    this.duration = JdbcWritableBridge.readLong(24, __dbResults);
    this.title_duration = JdbcWritableBridge.readLong(25, __dbResults);
    this.end_duration = JdbcWritableBridge.readLong(26, __dbResults);
    this.is_parent_lock = JdbcWritableBridge.readString(27, __dbResults);
    this.is_ads = JdbcWritableBridge.readString(28, __dbResults);
    this.is_preview = JdbcWritableBridge.readString(29, __dbResults);
    this.seasons_asset_id = JdbcWritableBridge.readString(30, __dbResults);
    this.seasons_total_num = JdbcWritableBridge.readInteger(31, __dbResults);
    this.mark_id = JdbcWritableBridge.readString(32, __dbResults);
    this.template_model = JdbcWritableBridge.readString(33, __dbResults);
    this.resource_id = JdbcWritableBridge.readInteger(34, __dbResults);
    this.onshelf = JdbcWritableBridge.readString(35, __dbResults);
    this.audit_status = JdbcWritableBridge.readString(36, __dbResults);
    this.audit_description = JdbcWritableBridge.readString(37, __dbResults);
    this.ads_list = JdbcWritableBridge.readString(38, __dbResults);
    this.audio_track = JdbcWritableBridge.readString(39, __dbResults);
    this.is_trash = JdbcWritableBridge.readString(40, __dbResults);
    this.is_download = JdbcWritableBridge.readString(41, __dbResults);
    this.user_id = JdbcWritableBridge.readInteger(42, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(43, __dbResults);
    this.update_time = JdbcWritableBridge.readTimestamp(44, __dbResults);
    this.start_datetime = JdbcWritableBridge.readTimestamp(45, __dbResults);
    this.end_datetime = JdbcWritableBridge.readTimestamp(46, __dbResults);
    this.play_count = JdbcWritableBridge.readInteger(47, __dbResults);
    this.movie_id = JdbcWritableBridge.readInteger(48, __dbResults);
    this.is_fee = JdbcWritableBridge.readString(49, __dbResults);
    this.product_id = JdbcWritableBridge.readString(50, __dbResults);
    this.intact = JdbcWritableBridge.readInteger(51, __dbResults);
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
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(asset_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(originalid, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(parent_asset_id, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(provider_code, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(type, 6 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(list_sort, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(episode_updated, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(episode_total, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(director_id, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(actor_id, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(writer_id, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(category_id, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(online_year_tag_id, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(area_tag_id, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(language_tag_id, 16 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(hit_count, 17 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(likeCount, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(shareCount, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(device_type, 20 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(grade, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(star, 22 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(tags_id, 23 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(duration, 24 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(title_duration, 25 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(end_duration, 26 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(is_parent_lock, 27 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_ads, 28 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_preview, 29 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(seasons_asset_id, 30 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(seasons_total_num, 31 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(mark_id, 32 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(template_model, 33 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(resource_id, 34 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(onshelf, 35 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(audit_status, 36 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(audit_description, 37 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(ads_list, 38 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(audio_track, 39 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(is_trash, 40 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_download, 41 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(user_id, 42 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 43 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(update_time, 44 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(start_datetime, 45 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(end_datetime, 46 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(play_count, 47 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(movie_id, 48 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(is_fee, 49 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(product_id, 50 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(intact, 51 + __off, 4, __dbStmt);
    return 51;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(asset_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(originalid, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(parent_asset_id, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(provider_code, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(type, 6 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(list_sort, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(episode_updated, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(episode_total, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(director_id, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(actor_id, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(writer_id, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(category_id, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(online_year_tag_id, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(area_tag_id, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(language_tag_id, 16 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(hit_count, 17 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(likeCount, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(shareCount, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(device_type, 20 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(grade, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(star, 22 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(tags_id, 23 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(duration, 24 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(title_duration, 25 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(end_duration, 26 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(is_parent_lock, 27 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_ads, 28 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_preview, 29 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(seasons_asset_id, 30 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(seasons_total_num, 31 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(mark_id, 32 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(template_model, 33 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(resource_id, 34 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(onshelf, 35 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(audit_status, 36 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(audit_description, 37 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(ads_list, 38 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(audio_track, 39 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(is_trash, 40 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(is_download, 41 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(user_id, 42 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 43 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(update_time, 44 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(start_datetime, 45 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(end_datetime, 46 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(play_count, 47 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(movie_id, 48 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(is_fee, 49 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(product_id, 50 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(intact, 51 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.asset_id = null;
    } else {
    this.asset_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.originalid = null;
    } else {
    this.originalid = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.parent_asset_id = null;
    } else {
    this.parent_asset_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.provider_code = null;
    } else {
    this.provider_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.type = null;
    } else {
    this.type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.list_sort = null;
    } else {
    this.list_sort = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.episode_updated = null;
    } else {
    this.episode_updated = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.episode_total = null;
    } else {
    this.episode_total = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.director_id = null;
    } else {
    this.director_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.actor_id = null;
    } else {
    this.actor_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.writer_id = null;
    } else {
    this.writer_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.category_id = null;
    } else {
    this.category_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.online_year_tag_id = null;
    } else {
    this.online_year_tag_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.area_tag_id = null;
    } else {
    this.area_tag_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.language_tag_id = null;
    } else {
    this.language_tag_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.hit_count = null;
    } else {
    this.hit_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.likeCount = null;
    } else {
    this.likeCount = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.shareCount = null;
    } else {
    this.shareCount = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.device_type = null;
    } else {
    this.device_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.grade = null;
    } else {
    this.grade = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.star = null;
    } else {
    this.star = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tags_id = null;
    } else {
    this.tags_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.duration = null;
    } else {
    this.duration = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.title_duration = null;
    } else {
    this.title_duration = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.end_duration = null;
    } else {
    this.end_duration = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.is_parent_lock = null;
    } else {
    this.is_parent_lock = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.is_ads = null;
    } else {
    this.is_ads = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.is_preview = null;
    } else {
    this.is_preview = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.seasons_asset_id = null;
    } else {
    this.seasons_asset_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.seasons_total_num = null;
    } else {
    this.seasons_total_num = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.mark_id = null;
    } else {
    this.mark_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.template_model = null;
    } else {
    this.template_model = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.resource_id = null;
    } else {
    this.resource_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.onshelf = null;
    } else {
    this.onshelf = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.audit_status = null;
    } else {
    this.audit_status = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.audit_description = null;
    } else {
    this.audit_description = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ads_list = null;
    } else {
    this.ads_list = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.audio_track = null;
    } else {
    this.audio_track = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.is_trash = null;
    } else {
    this.is_trash = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.is_download = null;
    } else {
    this.is_download = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_id = null;
    } else {
    this.user_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.create_time = null;
    } else {
    this.create_time = new Timestamp(__dataIn.readLong());
    this.create_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.update_time = null;
    } else {
    this.update_time = new Timestamp(__dataIn.readLong());
    this.update_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.start_datetime = null;
    } else {
    this.start_datetime = new Timestamp(__dataIn.readLong());
    this.start_datetime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.end_datetime = null;
    } else {
    this.end_datetime = new Timestamp(__dataIn.readLong());
    this.end_datetime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.play_count = null;
    } else {
    this.play_count = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.movie_id = null;
    } else {
    this.movie_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.is_fee = null;
    } else {
    this.is_fee = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.product_id = null;
    } else {
    this.product_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.intact = null;
    } else {
    this.intact = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, asset_id);
    }
    if (null == this.originalid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.originalid);
    }
    if (null == this.parent_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, parent_asset_id);
    }
    if (null == this.provider_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, provider_code);
    }
    if (null == this.type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, type);
    }
    if (null == this.list_sort) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.list_sort);
    }
    if (null == this.episode_updated) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.episode_updated);
    }
    if (null == this.episode_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.episode_total);
    }
    if (null == this.director_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, director_id);
    }
    if (null == this.actor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, actor_id);
    }
    if (null == this.writer_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, writer_id);
    }
    if (null == this.category_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, category_id);
    }
    if (null == this.online_year_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, online_year_tag_id);
    }
    if (null == this.area_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, area_tag_id);
    }
    if (null == this.language_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, language_tag_id);
    }
    if (null == this.hit_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.hit_count);
    }
    if (null == this.likeCount) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.likeCount);
    }
    if (null == this.shareCount) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.shareCount);
    }
    if (null == this.device_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, device_type);
    }
    if (null == this.grade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.grade);
    }
    if (null == this.star) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, star);
    }
    if (null == this.tags_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tags_id);
    }
    if (null == this.duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.duration);
    }
    if (null == this.title_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.title_duration);
    }
    if (null == this.end_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_duration);
    }
    if (null == this.is_parent_lock) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_parent_lock);
    }
    if (null == this.is_ads) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_ads);
    }
    if (null == this.is_preview) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_preview);
    }
    if (null == this.seasons_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, seasons_asset_id);
    }
    if (null == this.seasons_total_num) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.seasons_total_num);
    }
    if (null == this.mark_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, mark_id);
    }
    if (null == this.template_model) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, template_model);
    }
    if (null == this.resource_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.resource_id);
    }
    if (null == this.onshelf) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, onshelf);
    }
    if (null == this.audit_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audit_status);
    }
    if (null == this.audit_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audit_description);
    }
    if (null == this.ads_list) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ads_list);
    }
    if (null == this.audio_track) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audio_track);
    }
    if (null == this.is_trash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_trash);
    }
    if (null == this.is_download) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_download);
    }
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.update_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_time.getTime());
    __dataOut.writeInt(this.update_time.getNanos());
    }
    if (null == this.start_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.start_datetime.getTime());
    __dataOut.writeInt(this.start_datetime.getNanos());
    }
    if (null == this.end_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_datetime.getTime());
    __dataOut.writeInt(this.end_datetime.getNanos());
    }
    if (null == this.play_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.play_count);
    }
    if (null == this.movie_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.movie_id);
    }
    if (null == this.is_fee) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_fee);
    }
    if (null == this.product_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, product_id);
    }
    if (null == this.intact) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.intact);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, asset_id);
    }
    if (null == this.originalid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.originalid);
    }
    if (null == this.parent_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, parent_asset_id);
    }
    if (null == this.provider_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, provider_code);
    }
    if (null == this.type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, type);
    }
    if (null == this.list_sort) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.list_sort);
    }
    if (null == this.episode_updated) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.episode_updated);
    }
    if (null == this.episode_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.episode_total);
    }
    if (null == this.director_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, director_id);
    }
    if (null == this.actor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, actor_id);
    }
    if (null == this.writer_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, writer_id);
    }
    if (null == this.category_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, category_id);
    }
    if (null == this.online_year_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, online_year_tag_id);
    }
    if (null == this.area_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, area_tag_id);
    }
    if (null == this.language_tag_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, language_tag_id);
    }
    if (null == this.hit_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.hit_count);
    }
    if (null == this.likeCount) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.likeCount);
    }
    if (null == this.shareCount) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.shareCount);
    }
    if (null == this.device_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, device_type);
    }
    if (null == this.grade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.grade);
    }
    if (null == this.star) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, star);
    }
    if (null == this.tags_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tags_id);
    }
    if (null == this.duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.duration);
    }
    if (null == this.title_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.title_duration);
    }
    if (null == this.end_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_duration);
    }
    if (null == this.is_parent_lock) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_parent_lock);
    }
    if (null == this.is_ads) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_ads);
    }
    if (null == this.is_preview) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_preview);
    }
    if (null == this.seasons_asset_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, seasons_asset_id);
    }
    if (null == this.seasons_total_num) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.seasons_total_num);
    }
    if (null == this.mark_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, mark_id);
    }
    if (null == this.template_model) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, template_model);
    }
    if (null == this.resource_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.resource_id);
    }
    if (null == this.onshelf) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, onshelf);
    }
    if (null == this.audit_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audit_status);
    }
    if (null == this.audit_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audit_description);
    }
    if (null == this.ads_list) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ads_list);
    }
    if (null == this.audio_track) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, audio_track);
    }
    if (null == this.is_trash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_trash);
    }
    if (null == this.is_download) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_download);
    }
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.update_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_time.getTime());
    __dataOut.writeInt(this.update_time.getNanos());
    }
    if (null == this.start_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.start_datetime.getTime());
    __dataOut.writeInt(this.start_datetime.getNanos());
    }
    if (null == this.end_datetime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_datetime.getTime());
    __dataOut.writeInt(this.end_datetime.getNanos());
    }
    if (null == this.play_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.play_count);
    }
    if (null == this.movie_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.movie_id);
    }
    if (null == this.is_fee) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, is_fee);
    }
    if (null == this.product_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, product_id);
    }
    if (null == this.intact) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.intact);
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
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(asset_id==null?"null":asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(originalid==null?"null":"" + originalid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(parent_asset_id==null?"null":parent_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(provider_code==null?"null":provider_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(list_sort==null?"null":"" + list_sort, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(episode_updated==null?"null":"" + episode_updated, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(episode_total==null?"null":"" + episode_total, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(director_id==null?"null":director_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(actor_id==null?"null":actor_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(writer_id==null?"null":writer_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(category_id==null?"null":category_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(online_year_tag_id==null?"null":online_year_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(area_tag_id==null?"null":area_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(language_tag_id==null?"null":language_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(hit_count==null?"null":"" + hit_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(likeCount==null?"null":"" + likeCount, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(shareCount==null?"null":"" + shareCount, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(device_type==null?"null":device_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(grade==null?"null":"" + grade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(star==null?"null":star, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tags_id==null?"null":tags_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(duration==null?"null":"" + duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(title_duration==null?"null":"" + title_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_duration==null?"null":"" + end_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_parent_lock==null?"null":is_parent_lock, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_ads==null?"null":is_ads, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_preview==null?"null":is_preview, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(seasons_asset_id==null?"null":seasons_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(seasons_total_num==null?"null":"" + seasons_total_num, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(mark_id==null?"null":mark_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(template_model==null?"null":template_model, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(resource_id==null?"null":"" + resource_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(onshelf==null?"null":onshelf, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audit_status==null?"null":audit_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audit_description==null?"null":audit_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ads_list==null?"null":ads_list, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audio_track==null?"null":audio_track, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_trash==null?"null":is_trash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_download==null?"null":is_download, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"null":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_time==null?"null":"" + update_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_datetime==null?"null":"" + start_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_datetime==null?"null":"" + end_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(play_count==null?"null":"" + play_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(movie_id==null?"null":"" + movie_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_fee==null?"null":is_fee, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(product_id==null?"null":product_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(intact==null?"null":"" + intact, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(asset_id==null?"null":asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(originalid==null?"null":"" + originalid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(parent_asset_id==null?"null":parent_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(provider_code==null?"null":provider_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(list_sort==null?"null":"" + list_sort, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(episode_updated==null?"null":"" + episode_updated, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(episode_total==null?"null":"" + episode_total, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(director_id==null?"null":director_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(actor_id==null?"null":actor_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(writer_id==null?"null":writer_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(category_id==null?"null":category_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(online_year_tag_id==null?"null":online_year_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(area_tag_id==null?"null":area_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(language_tag_id==null?"null":language_tag_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(hit_count==null?"null":"" + hit_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(likeCount==null?"null":"" + likeCount, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(shareCount==null?"null":"" + shareCount, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(device_type==null?"null":device_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(grade==null?"null":"" + grade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(star==null?"null":star, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tags_id==null?"null":tags_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(duration==null?"null":"" + duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(title_duration==null?"null":"" + title_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_duration==null?"null":"" + end_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_parent_lock==null?"null":is_parent_lock, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_ads==null?"null":is_ads, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_preview==null?"null":is_preview, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(seasons_asset_id==null?"null":seasons_asset_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(seasons_total_num==null?"null":"" + seasons_total_num, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(mark_id==null?"null":mark_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(template_model==null?"null":template_model, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(resource_id==null?"null":"" + resource_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(onshelf==null?"null":onshelf, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audit_status==null?"null":audit_status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audit_description==null?"null":audit_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ads_list==null?"null":ads_list, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(audio_track==null?"null":audio_track, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_trash==null?"null":is_trash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_download==null?"null":is_download, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"null":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_time==null?"null":"" + update_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_datetime==null?"null":"" + start_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_datetime==null?"null":"" + end_datetime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(play_count==null?"null":"" + play_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(movie_id==null?"null":"" + movie_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_fee==null?"null":is_fee, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(product_id==null?"null":product_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(intact==null?"null":"" + intact, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.asset_id = null; } else {
      this.asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.originalid = null; } else {
      this.originalid = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.provider_code = null; } else {
      this.provider_code = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.type = null; } else {
      this.type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.list_sort = null; } else {
      this.list_sort = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.episode_updated = null; } else {
      this.episode_updated = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.episode_total = null; } else {
      this.episode_total = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.director_id = null; } else {
      this.director_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.actor_id = null; } else {
      this.actor_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.writer_id = null; } else {
      this.writer_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.category_id = null; } else {
      this.category_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.online_year_tag_id = null; } else {
      this.online_year_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.area_tag_id = null; } else {
      this.area_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.language_tag_id = null; } else {
      this.language_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.hit_count = null; } else {
      this.hit_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.likeCount = null; } else {
      this.likeCount = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.shareCount = null; } else {
      this.shareCount = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.device_type = null; } else {
      this.device_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.grade = null; } else {
      this.grade = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.star = null; } else {
      this.star = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tags_id = null; } else {
      this.tags_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.duration = null; } else {
      this.duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.title_duration = null; } else {
      this.title_duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_duration = null; } else {
      this.end_duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_parent_lock = null; } else {
      this.is_parent_lock = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_ads = null; } else {
      this.is_ads = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_preview = null; } else {
      this.is_preview = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.seasons_asset_id = null; } else {
      this.seasons_asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.seasons_total_num = null; } else {
      this.seasons_total_num = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.mark_id = null; } else {
      this.mark_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.template_model = null; } else {
      this.template_model = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.resource_id = null; } else {
      this.resource_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.onshelf = null; } else {
      this.onshelf = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audit_status = null; } else {
      this.audit_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audit_description = null; } else {
      this.audit_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ads_list = null; } else {
      this.ads_list = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audio_track = null; } else {
      this.audio_track = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_trash = null; } else {
      this.is_trash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_download = null; } else {
      this.is_download = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_time = null; } else {
      this.update_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_datetime = null; } else {
      this.start_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_datetime = null; } else {
      this.end_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.play_count = null; } else {
      this.play_count = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.movie_id = null; } else {
      this.movie_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_fee = null; } else {
      this.is_fee = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.product_id = null; } else {
      this.product_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.intact = null; } else {
      this.intact = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.asset_id = null; } else {
      this.asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.originalid = null; } else {
      this.originalid = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.provider_code = null; } else {
      this.provider_code = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.type = null; } else {
      this.type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.list_sort = null; } else {
      this.list_sort = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.episode_updated = null; } else {
      this.episode_updated = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.episode_total = null; } else {
      this.episode_total = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.director_id = null; } else {
      this.director_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.actor_id = null; } else {
      this.actor_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.writer_id = null; } else {
      this.writer_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.category_id = null; } else {
      this.category_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.online_year_tag_id = null; } else {
      this.online_year_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.area_tag_id = null; } else {
      this.area_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.language_tag_id = null; } else {
      this.language_tag_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.hit_count = null; } else {
      this.hit_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.likeCount = null; } else {
      this.likeCount = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.shareCount = null; } else {
      this.shareCount = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.device_type = null; } else {
      this.device_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.grade = null; } else {
      this.grade = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.star = null; } else {
      this.star = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tags_id = null; } else {
      this.tags_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.duration = null; } else {
      this.duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.title_duration = null; } else {
      this.title_duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_duration = null; } else {
      this.end_duration = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_parent_lock = null; } else {
      this.is_parent_lock = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_ads = null; } else {
      this.is_ads = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_preview = null; } else {
      this.is_preview = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.seasons_asset_id = null; } else {
      this.seasons_asset_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.seasons_total_num = null; } else {
      this.seasons_total_num = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.mark_id = null; } else {
      this.mark_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.template_model = null; } else {
      this.template_model = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.resource_id = null; } else {
      this.resource_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.onshelf = null; } else {
      this.onshelf = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audit_status = null; } else {
      this.audit_status = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audit_description = null; } else {
      this.audit_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ads_list = null; } else {
      this.ads_list = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.audio_track = null; } else {
      this.audio_track = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_trash = null; } else {
      this.is_trash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_download = null; } else {
      this.is_download = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_time = null; } else {
      this.update_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_datetime = null; } else {
      this.start_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_datetime = null; } else {
      this.end_datetime = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.play_count = null; } else {
      this.play_count = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.movie_id = null; } else {
      this.movie_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.is_fee = null; } else {
      this.is_fee = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.product_id = null; } else {
      this.product_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.intact = null; } else {
      this.intact = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    bo_video_content o = (bo_video_content) super.clone();
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
    o.update_time = (o.update_time != null) ? (java.sql.Timestamp) o.update_time.clone() : null;
    o.start_datetime = (o.start_datetime != null) ? (java.sql.Timestamp) o.start_datetime.clone() : null;
    o.end_datetime = (o.end_datetime != null) ? (java.sql.Timestamp) o.end_datetime.clone() : null;
    return o;
  }

  public void clone0(bo_video_content o) throws CloneNotSupportedException {
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
    o.update_time = (o.update_time != null) ? (java.sql.Timestamp) o.update_time.clone() : null;
    o.start_datetime = (o.start_datetime != null) ? (java.sql.Timestamp) o.start_datetime.clone() : null;
    o.end_datetime = (o.end_datetime != null) ? (java.sql.Timestamp) o.end_datetime.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("asset_id", this.asset_id);
    __sqoop$field_map.put("originalid", this.originalid);
    __sqoop$field_map.put("parent_asset_id", this.parent_asset_id);
    __sqoop$field_map.put("provider_code", this.provider_code);
    __sqoop$field_map.put("type", this.type);
    __sqoop$field_map.put("list_sort", this.list_sort);
    __sqoop$field_map.put("episode_updated", this.episode_updated);
    __sqoop$field_map.put("episode_total", this.episode_total);
    __sqoop$field_map.put("director_id", this.director_id);
    __sqoop$field_map.put("actor_id", this.actor_id);
    __sqoop$field_map.put("writer_id", this.writer_id);
    __sqoop$field_map.put("category_id", this.category_id);
    __sqoop$field_map.put("online_year_tag_id", this.online_year_tag_id);
    __sqoop$field_map.put("area_tag_id", this.area_tag_id);
    __sqoop$field_map.put("language_tag_id", this.language_tag_id);
    __sqoop$field_map.put("hit_count", this.hit_count);
    __sqoop$field_map.put("likeCount", this.likeCount);
    __sqoop$field_map.put("shareCount", this.shareCount);
    __sqoop$field_map.put("device_type", this.device_type);
    __sqoop$field_map.put("grade", this.grade);
    __sqoop$field_map.put("star", this.star);
    __sqoop$field_map.put("tags_id", this.tags_id);
    __sqoop$field_map.put("duration", this.duration);
    __sqoop$field_map.put("title_duration", this.title_duration);
    __sqoop$field_map.put("end_duration", this.end_duration);
    __sqoop$field_map.put("is_parent_lock", this.is_parent_lock);
    __sqoop$field_map.put("is_ads", this.is_ads);
    __sqoop$field_map.put("is_preview", this.is_preview);
    __sqoop$field_map.put("seasons_asset_id", this.seasons_asset_id);
    __sqoop$field_map.put("seasons_total_num", this.seasons_total_num);
    __sqoop$field_map.put("mark_id", this.mark_id);
    __sqoop$field_map.put("template_model", this.template_model);
    __sqoop$field_map.put("resource_id", this.resource_id);
    __sqoop$field_map.put("onshelf", this.onshelf);
    __sqoop$field_map.put("audit_status", this.audit_status);
    __sqoop$field_map.put("audit_description", this.audit_description);
    __sqoop$field_map.put("ads_list", this.ads_list);
    __sqoop$field_map.put("audio_track", this.audio_track);
    __sqoop$field_map.put("is_trash", this.is_trash);
    __sqoop$field_map.put("is_download", this.is_download);
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("update_time", this.update_time);
    __sqoop$field_map.put("start_datetime", this.start_datetime);
    __sqoop$field_map.put("end_datetime", this.end_datetime);
    __sqoop$field_map.put("play_count", this.play_count);
    __sqoop$field_map.put("movie_id", this.movie_id);
    __sqoop$field_map.put("is_fee", this.is_fee);
    __sqoop$field_map.put("product_id", this.product_id);
    __sqoop$field_map.put("intact", this.intact);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("asset_id", this.asset_id);
    __sqoop$field_map.put("originalid", this.originalid);
    __sqoop$field_map.put("parent_asset_id", this.parent_asset_id);
    __sqoop$field_map.put("provider_code", this.provider_code);
    __sqoop$field_map.put("type", this.type);
    __sqoop$field_map.put("list_sort", this.list_sort);
    __sqoop$field_map.put("episode_updated", this.episode_updated);
    __sqoop$field_map.put("episode_total", this.episode_total);
    __sqoop$field_map.put("director_id", this.director_id);
    __sqoop$field_map.put("actor_id", this.actor_id);
    __sqoop$field_map.put("writer_id", this.writer_id);
    __sqoop$field_map.put("category_id", this.category_id);
    __sqoop$field_map.put("online_year_tag_id", this.online_year_tag_id);
    __sqoop$field_map.put("area_tag_id", this.area_tag_id);
    __sqoop$field_map.put("language_tag_id", this.language_tag_id);
    __sqoop$field_map.put("hit_count", this.hit_count);
    __sqoop$field_map.put("likeCount", this.likeCount);
    __sqoop$field_map.put("shareCount", this.shareCount);
    __sqoop$field_map.put("device_type", this.device_type);
    __sqoop$field_map.put("grade", this.grade);
    __sqoop$field_map.put("star", this.star);
    __sqoop$field_map.put("tags_id", this.tags_id);
    __sqoop$field_map.put("duration", this.duration);
    __sqoop$field_map.put("title_duration", this.title_duration);
    __sqoop$field_map.put("end_duration", this.end_duration);
    __sqoop$field_map.put("is_parent_lock", this.is_parent_lock);
    __sqoop$field_map.put("is_ads", this.is_ads);
    __sqoop$field_map.put("is_preview", this.is_preview);
    __sqoop$field_map.put("seasons_asset_id", this.seasons_asset_id);
    __sqoop$field_map.put("seasons_total_num", this.seasons_total_num);
    __sqoop$field_map.put("mark_id", this.mark_id);
    __sqoop$field_map.put("template_model", this.template_model);
    __sqoop$field_map.put("resource_id", this.resource_id);
    __sqoop$field_map.put("onshelf", this.onshelf);
    __sqoop$field_map.put("audit_status", this.audit_status);
    __sqoop$field_map.put("audit_description", this.audit_description);
    __sqoop$field_map.put("ads_list", this.ads_list);
    __sqoop$field_map.put("audio_track", this.audio_track);
    __sqoop$field_map.put("is_trash", this.is_trash);
    __sqoop$field_map.put("is_download", this.is_download);
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("update_time", this.update_time);
    __sqoop$field_map.put("start_datetime", this.start_datetime);
    __sqoop$field_map.put("end_datetime", this.end_datetime);
    __sqoop$field_map.put("play_count", this.play_count);
    __sqoop$field_map.put("movie_id", this.movie_id);
    __sqoop$field_map.put("is_fee", this.is_fee);
    __sqoop$field_map.put("product_id", this.product_id);
    __sqoop$field_map.put("intact", this.intact);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
