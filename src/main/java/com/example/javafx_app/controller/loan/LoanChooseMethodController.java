package com.example.javafx_app.controller.loan;

public class LoanChooseMethodController {
    /*
    Scene này cho phép người dùng lựa chọn phương thức vay vốn.
    Hiện tại hệ thống hỗ trợ 2 phương thức: Vay kỳ hạn và Vay trả góp.

    1. VAY KỲ HẠN
    - Người dùng vay một khoản tiền trong một khoảng thời gian xác định.
    - Đến thời điểm đáo hạn:
        + Người dùng có thể trả toàn bộ cả gốc và lãi.
        + Hoặc yêu cầu gia hạn khoản vay (nếu được ngân hàng chấp thuận).
    - Trong suốt thời gian vay, người dùng chỉ cần theo dõi khoản vay,
      không phải trả tiền hàng tháng.
    - Lãi suất của vay kỳ hạn thường thấp hơn vay trả góp.
    - Phù hợp với người dùng có kế hoạch tài chính rõ ràng
      và có khả năng thanh toán toàn bộ khoản vay khi đến hạn.

    2. VAY TRẢ GÓP
    - Người dùng trả nợ theo từng tháng trong suốt thời gian vay.
    - Mỗi tháng, hệ thống sẽ tự động trích tiền từ tài khoản:
        + Một phần tiền gốc
        + Và tiền lãi đã được tính toán trước
    - Tổng số tiền phải trả được chia nhỏ giúp giảm áp lực tài chính hàng tháng.
    - Lãi suất vay trả góp thường cao hơn vay kỳ hạn.
    - Phù hợp với người dùng có thu nhập ổn định hàng tháng.

    LƯU Ý VỀ LÃI SUẤT
    - Lãi suất áp dụng phụ thuộc vào:
        + Mục đích vay (Mua sắm, Sản xuất kinh doanh, Mua ô tô, Bất động sản, Đầu tư...)
        + Danh mục vay đã được người dùng lựa chọn ở scene trước.
    - Đối với khoản vay tự chọn:
        + Lãi suất chưa được xác định tại bước này.
        + Ngân hàng sẽ xét duyệt và áp dụng mức lãi suất phù hợp
          sau khi đánh giá hồ sơ của người dùng.

    Scene này chỉ hiển thị phương thức vay,
    các thông tin chi tiết về lãi suất và lịch trả nợ
    sẽ được hiển thị ở các bước tiếp theo.
    */
}
