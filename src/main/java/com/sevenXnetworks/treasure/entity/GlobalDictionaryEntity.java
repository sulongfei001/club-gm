package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 17:20
 * @Version 1.0
 */
@Entity
@Table(name = "global_dictionary", schema = "club", catalog = "")
public class GlobalDictionaryEntity {
    private long id;
    private String key;
    private String value;
    private String remark;
    private Long type;

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
    @Column(name = "\"key\"")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "type")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlobalDictionaryEntity that = (GlobalDictionaryEntity) o;
        return id == that.id &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, value, remark, type);
    }
}
