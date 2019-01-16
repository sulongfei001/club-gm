package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 19:12
 * @Version 1.0
 */
@Entity
@Table(name = "activity", schema = "club", catalog = "")
public class ActivityEntity {
    private Long id;
    private Long barId;
    private Long goodsId;
    private String goodsName;
    private Integer goodsType;
    private BigDecimal goodsPrdValueMon;
    private String name;
    private String content;
    private Integer count;
    private Byte onSale;
    private Integer orderNum;
    private Timestamp startTime;
    private Timestamp endTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "goods_prdValueMon")
    public BigDecimal getGoodsPrdValueMon() {
        return goodsPrdValueMon;
    }

    public void setGoodsPrdValueMon(BigDecimal goodsPrdValueMon) {
        this.goodsPrdValueMon = goodsPrdValueMon;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "on_sale")
    public Byte getOnSale() {
        return onSale;
    }

    public void setOnSale(Byte onSale) {
        this.onSale = onSale;
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
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

}
