package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 14:57
 * @Version 1.0
 */
@Entity
@Table(name = "bar", schema = "club", catalog = "")
public class BarEntity {
    private long id;
    private Long allianceId;
    private Long barId;
    private String barName;
    private Integer orderNum;
    private Byte state;

    public BarEntity() {
    }

    public BarEntity(Long allianceId, Long barId, String barName, Byte state) {
        this.allianceId = allianceId;
        this.barId = barId;
        this.barName = barName;
        this.state = state;
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
    @Column(name = "alliance_id")
    public Long getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Long allianceId) {
        this.allianceId = allianceId;
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
    @Column(name = "bar_name")
    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    @Basic
    @Column(name = "order_num")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
