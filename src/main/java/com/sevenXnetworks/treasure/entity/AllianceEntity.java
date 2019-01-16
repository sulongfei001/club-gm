package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:54
 * @Version 1.0
 */
@Entity
@Table(name = "alliance", schema = "club", catalog = "")
public class AllianceEntity {
    private long id;
    private Long allianceId;
    private String allianceName;
    private Byte state;

    public AllianceEntity() {
    }

    public AllianceEntity(Long allianceId, String allianceName, Byte state) {
        this.allianceId = allianceId;
        this.allianceName = allianceName;
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
    @Column(name = "alliance_name")
    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
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
