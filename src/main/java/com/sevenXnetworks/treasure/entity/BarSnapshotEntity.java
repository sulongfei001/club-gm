package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/2 13:45
 * @Version 1.0
 */
@Entity
@Table(name = "bar_snapshot", schema = "club", catalog = "")
public class BarSnapshotEntity {
    private long id;
    private Long barId;
    private String url;

    public BarSnapshotEntity() {
    }

    public BarSnapshotEntity(Long barId, String url) {
        this.barId = barId;
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
    @Column(name = "bar_id")
    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
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
        BarSnapshotEntity that = (BarSnapshotEntity) o;
        return id == that.id &&
                Objects.equals(barId, that.barId) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barId, url);
    }
}
