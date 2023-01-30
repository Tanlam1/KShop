package com.kshop.main.common;

public interface Constant {
    
    
    interface SELLER_TYPE {
        String CA_NHAN = "CA_NHAN";
        String DOANH_NGHIEP = "DOANH_NGHIEP";
    }
    
    interface SESSION_LOGIN_TYPE {
        String ADMIN = "admin_kodoku_KShop";
        String CLIENT = "userLogin";
    }
    
    interface SESSION_DATA {
        String REPORT_USER_ID = "reports_store_id";
    }
    
    interface IMG_DEFAULT {
        String STORE = "https://img.icons8.com/cotton/100/000000/shop--v7.png";
    }
    
    interface DIR {
        String WEB_ROOT = "http://localhost:8080";
        
    }
    
    interface URI {
        String ADMIN = "/admin-panel";
    }
    
    interface ROLES {
        Long ADMIN = 1L;
        Long THU_KHO = 2L;
        Long KE_TOAN = 3L;        
        Long SHOPER = 4L;
        Long TU_VAN = 5L;
        Long CHAM_SOC = 6L;        
        Long ADMIN_MONEY = 7L;
        Long TRO_LY = 8L;
        Long CHOT_DON = 9L;
        Long UPLOAD_PRODUCT = 10L;
    }
    
    interface RESPONE {
        String TOKEN_DIE = "Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại";
        String TOKEN_NOT_ACCEPT = "Bạn không có quyền truy cập đến chức năng này."; 
        String TOKEN_FAIL_ROLE = "Bạn không có quyền này. Vui lòng liên hệ quản lý của bạn"; 
        String NOT_FOUND = "Không tìm thấy gì cả. Vui lòng thử lại"; 
        String ALREADY_UPDATED = "Thông tin này đã được cập nhật từ trước và không thể thay đổi.";
        String NOT_FOUND_USER = "Không tìm thấy user. Vui lòng thử lại"; 
        String NOT_FOUND_ORDER = "Không tìm thấy order. Vui lòng thử lại"; 
        String HAS_ERROR = "Đã có lỗi xảy ra. Vui lòng thử lại";
    }
    
    interface SHOP_PAY {
        Double MIN = 500000D; // min pay của shop là: 500k
    }
    
    interface ORDER_STATUS {
        String NEW = "New";
        String SHOP_CFR = "Shop Confirmed";
        String SHOP_NOT_CFR = "Canceled By Store";
        String SHIPPED = "Shipped";
        String COMPLETE = "Complete";
        String CLOSE = "Close";
        String CANCEL = "Cancel";
    }
}
