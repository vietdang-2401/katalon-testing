Feature: Quản lí từ điển năng lực
  Là 1 admin user, tôi muốn quản lí việc CRUD từ điển năng lực

  Background: 
    Given User đăng nhập thành công với role admin
    And User đang ở trang "Từ điển năng lực"

   Scenario: Admin lọc năng lực theo tên
     Given Bộ lọc đang ở chế độ mặc định
     When Admin lọc theo tên "Giao tiếp"
     Then Danh sách chỉ hiển thị các năng lực có tên chứa "Giao tiếp"
     And Pagination reset về trang đầu tiên
  
   Scenario: Admin lọc năng lực theo Core
     Given Bộ lọc đang ở chế độ mặc định
     When Admin lọc theo "Chỉ Core"
     Then Danh sách chỉ hiển thị các năng lực Core
     And Pagination reset về trang đầu tiên
  
   Scenario: Admin lọc năng lực theo loại ASK
     Given Bộ lọc đang ở chế độ mặc định
     When Admin lọc theo loại năng lực "Kỹ năng (S)"
     Then Danh sách chỉ hiển thị các năng lực loại "S"
     And Pagination reset về trang đầu tiên
  
   Scenario: Admin kết hợp nhiều bộ lọc
     Given Bộ lọc đang ở chế độ mặc định
     When Admin lọc theo tên "Giao tiếp"
     And Admin lọc theo "Chỉ Core"
     And Admin lọc theo loại năng lực "Kỹ năng (S)"
     Then Danh sách chỉ hiển thị các năng lực thỏa mãn tất cả điều kiện
  
   Scenario: Admin xóa bộ lọc
     Given Admin đã áp dụng các bộ lọc
     When Admin click nút xóa bộ lọc
     Then Tất cả bộ lọc được reset về mặc định
     And Danh sách hiển thị lại tất cả năng lực
     And Pagination reset về trang đầu tiên
  
   Scenario: Admin xem kết quả trống khi không có năng lực phù hợp
     Given Admin đã áp dụng bộ lọc ngẫu nhiên
     When Không có dữ liệu phù hợp
     Then Hiển thị thông báo không có kết quả
     And Không hiển thị bảng danh sách

   Scenario: Admin điều hướng pagination
     Given Bảng có nhiều hơn một trang dữ liệu
     When Admin chuyển sang trang tiếp theo
     Then Hiển thị dữ liệu trang tiếp theo
     And Nút quay lại trang trước khả dụng
     When Admin quay lại trang trước
     Then Hiển thị dữ liệu trang trước đó

   Scenario: Admin thêm mới năng lực thành công
     Given Admin đang ở trang thêm mới năng lực
     When Admin nhập đầy đủ thông tin năng lực hợp lệ
     And Admin click nút lưu/cập nhật
     Then Hiển thị thông báo thêm mới thành công

   Scenario: Admin cập nhật thông tin năng lực thành công
     Given Admin đang ở trang chỉnh sửa năng lực
     When Admin thay đổi thông tin năng lực
     And Admin click nút lưu/cập nhật
     Then Hiển thị thông báo cập nhật thành công

  Scenario: Admin xóa năng lực thành công
    Given Admin chọn năng lực cần xóa
    When Admin click nút xóa
    And Admin xác nhận xóa
    Then Năng lực bị xóa khỏi danh sách
    And Hiển thị thông báo xóa thành công
