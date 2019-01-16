package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/1 11:27
 * @Version 1.0
 */
@Entity
@Table(name = "activity_snapshot", schema = "club", catalog = "")
public class ActivitySnapshotEntity {
    private long id;
    private Long activityId;
    private Byte type;
    private String url;

    public ActivitySnapshotEntity() {
    }

    public ActivitySnapshotEntity(Long activityId, Byte type, String url) {
        this.activityId = activityId;
        this.type = type;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity_id")
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitySnapshotEntity that = (ActivitySnapshotEntity) o;
        return id == that.id &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityId, type, url);
    }
}
