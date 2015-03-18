package guda.task.web.action.seller.form;

import javax.validation.constraints.NotNull;

/**
 * Created by foodoon on 2014/12/21.
 */
public class ActForm {

    private String id;

    private String sellerId;

    private String sellerName;

    private Long amountPay;


    private String itemId;

    private String itemName;

    private String itemUrl;

    private String itemPic;

    private String descript;

    private String comment;

    private Integer status;
    @NotNull(message = "不能为空")
    private Long amountFee;
    @NotNull(message = "不能为空")
    private String searchUrl;
    @NotNull(message = "不能为空")
    private String searchKey;
    @NotNull(message = "不能为空")
    private String searchResultPage;
    @NotNull(message = "不能为空")
    private String searchResultRow;
    @NotNull(message = "不能为空")
    private String searchResultCol;

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearchResultPage() {
        return searchResultPage;
    }

    public void setSearchResultPage(String searchResultPage) {
        this.searchResultPage = searchResultPage;
    }

    public String getSearchResultRow() {
        return searchResultRow;
    }

    public void setSearchResultRow(String searchResultRow) {
        this.searchResultRow = searchResultRow;
    }

    public String getSearchResultCol() {
        return searchResultCol;
    }

    public void setSearchResultCol(String searchResultCol) {
        this.searchResultCol = searchResultCol;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getAmountPay() {
        return amountPay;
    }

    public void setAmountPay(Long amountPay) {
        this.amountPay = amountPay;
    }

    public Long getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(Long amountFee) {
        this.amountFee = amountFee;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
