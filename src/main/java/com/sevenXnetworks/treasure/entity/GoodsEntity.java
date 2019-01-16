package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:54
 * @Version 1.0
 */
@Entity
@Table(name = "goods", schema = "club", catalog = "")
public class GoodsEntity {
    private long id;
    private Long allianceId;
    private Long goodsId;
    private String goodsName;
    private Byte goodsType;
    private BigDecimal goodsPrdValueMon;
    private Integer orderNum;
    private Byte state;

    public GoodsEntity() {
    }

    public GoodsEntity(Long allianceId, Long goodsId, String goodsName, Byte goodsType, BigDecimal goodsPrdValueMon, Byte state) {
        this.allianceId = allianceId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsPrdValueMon = goodsPrdValueMon;
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
    @Column(name = "goods_id")
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_name")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "goods_type")
    public Byte getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Byte goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "goods_prd_value_mon")
    public BigDecimal getGoodsPrdValueMon() {
        return goodsPrdValueMon;
    }

    public void setGoodsPrdValueMon(BigDecimal goodsPrdValueMon) {
        this.goodsPrdValueMon = goodsPrdValueMon;
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
