package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-6 上午10:51
 * @Version 1.0
 */
@Entity
@Table(name = "\"order\"", schema = "club", catalog = "")
public class OrderEntity {
    private long id;
    private String orderId;
    private Long activityId;
    private String openId;
    private String memberId;
    private String userName;
    private String cellPhone;
    private Timestamp createDate;
    private Timestamp tradeDate;
    private Integer tradeCount;
    private BigDecimal tradeMoney;
    private String voucherType;
    private Byte isWin;
    private String winCode;

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
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "member_id")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "cell_phone")
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Basic
    @Column(name = "trade_date")
    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Basic
    @Column(name = "trade_count")
    public Integer getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(Integer tradeCount) {
        this.tradeCount = tradeCount;
    }

    @Basic
    @Column(name = "trade_money")
    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    @Basic
    @Column(name = "voucher_type")
    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    @Basic
    @Column(name = "is_win")
    public Byte getIsWin() {
        return isWin;
    }

    public void setIsWin(Byte isWin) {
        this.isWin = isWin;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "win_code")
    public String getWinCode() {
        return winCode;
    }

    public void setWinCode(String winCode) {
        this.winCode = winCode;
    }
}
