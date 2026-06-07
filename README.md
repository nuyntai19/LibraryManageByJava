# 🚀 LibraCore: Hệ thống Quản lý Thư viện
> Giải pháp quản lý thư viện toàn diện, tự động hoá nghiệp vụ mượn trả và thống kê.

## 📖 Tổng quan dự án (Overview)

Quản lý thư viện truyền thống thường đối mặt với các thách thức về việc kiểm soát thất thoát sách, theo dõi quá trình mượn/trả phức tạp và khó khăn trong việc quản trị dữ liệu độc giả cấp quy mô lớn. Đặc biệt, việc thiếu các công cụ báo cáo trực quan khiến ban quản lý không đánh giá được tình hình hoạt động của thư viện.

**LibraCore** được phát triển nhằm giải quyết triệt để bài toán trên. Đây là một ứng dụng Desktop mạnh mẽ được xây dựng bằng Java Swing, cung cấp quy trình quản lý khép kín. Hệ thống giúp đơn giản hoá từ khâu nhập kho, cấp phát thẻ thành viên, xử lý mượn trả sách, đến việc thi hành kỷ luật (phạt trễ hạn/hư hỏng). Trải nghiệm người dùng được đặt lên hàng đầu với giao diện trực quan, kết hợp cùng các công cụ thống kê biểu đồ thời gian thực.

## 💻 Tech Stack

Dự án được xây dựng dựa trên các công nghệ và thư viện tiêu chuẩn của hệ sinh thái Java:

*   **Frontend (UI/UX):** 
    *   Java Swing / AWT (Kiến trúc giao diện chính).
    *   `JCalendar` (Xử lý Date picker linh hoạt).
    *   `Raven Chart` (Vẽ biểu đồ thống kê trực quan).
    *   Bộ component tuỳ chỉnh: `RoundedButton`, `RoundedTextField`, `ImagePanel` mang lại UI hiện đại.
*   **Backend & Logic:** 
    *   Java SE (Core Java) - JDK 8+.
    *   Lập trình hướng đối tượng (OOP) cực kỳ chặt chẽ.
*   **Database:**
    *   **MySQL** (Tích hợp qua `mysql-connector-j-9.2.0.jar`).
    *   Hỗ trợ tuỳ chọn kết nối **SQL Server** thông qua cấu hình linh hoạt.
*   **Architecture & DevOps:** 
    *   Apache Ant (Công cụ Build).
    *   NetBeans IDE / Eclipse.
    *   Git & GitHub.

## ✨ Tính năng nổi bật (Key Features)

*   👥 **Quản lý Độc giả & Thẻ Thành viên:** Theo dõi chi tiết hồ sơ độc giả, tiến hành cấp phát, gia hạn hoặc khóa thẻ thành viên khi có vi phạm.
*   📚 **Quản lý Đầu sách & Nhập kho:** Quản lý kho sách số lượng lớn, phân loại theo thể loại. Tích hợp quản lý nguồn nhập, lập và theo dõi các phiếu nhập sách.
*   🔄 **Quy trình Mượn/Trả khép kín:** Khởi tạo phiếu mượn logic, tự động kiểm tra trạng thái khả dụng của sách và thẻ thành viên. 
*   ⚠️ **Kiểm soát Vi phạm & Xử phạt:** Tự động phát hiện thủ tục trễ hạn, cung cấp mô-đun lập phiếu phạt chi tiết (Làm hỏng/Mất sách, Trễ hạn).
*   📊 **Báo cáo & Thống kê Trực quan:** Cung cấp dashboard biểu đồ phân tích đa chiều (Lượng sách mượn theo năm, thống kê độc giả, hình thức mượn) giúp đưa ra quyết định quản trị hiệu quả.
*   🔒 **Phân quyền & Bảo mật (RBAC):** Hệ thống phân quyền tài khoản sâu, giới hạn chính xác nhân viên nào được thực hiện thao tác (Thủ thư, Quản lý, Nhập kho).

## ⚙️ Kiến trúc & Logic nghiệp vụ (Architecture & Business Logic)

Dự án tuân thủ nghiêm ngặt mô hình **3-Layer Architecture (Kiến trúc 3 lớp)** kết hợp DTO, đảm bảo tính clean code, dễ mở rộng và bảo trì:
*   **GUI (Lớp Giao diện - `src/ui`):** Chỉ chịu trách nhiệm hiển thị khối giao diện Swing và trigger các event.
*   **BUS (Lớp Nghiệp vụ - `src/BUS`):** Tập trung toàn bộ business logic. Ví dụ: Tính toán số tiền phạt vượt khung, xác thực các điều kiện (Thẻ thành viên còn hạn không? Sách có đang tồn kho không?) trước khi call database.
*   **DAO (Lớp Data Access - `src/DAO`):** Đóng gói các câu lệnh truy vấn Data, xử lý ResultSet thông qua `JDBCUtil`.
*   **DTO (Data Transfer Object - `src/DTO`):** Chuẩn hoá luồng dữ liệu trung chuyển giữa các lớp dưới dạng object (e.g. `DocGiaDTO`, `PhieuMuonDTO`).

**Logic Data Constraints:** 
Cơ sở dữ liệu được thiết kế với các ràng buộc khóa ngoại, trigger / cascade chặt chẽ (được mô tả trong `src/QuanLiThuVien.sql`). Hệ thống UI được thiết kế có tính kế thừa cao, tận dụng Use Case tìm kiếm đa trường linh hoạt từ tầng Helper xuống DAO.

## 🛠️ Hướng dẫn cài đặt & Khởi chạy (Getting Started)

### Yêu cầu hệ thống:
- Java Development Kit (JDK 8 trở lên).
- MySQL Server (Có thể dùng XAMPP, WAMP hoặc MySQL Workbench).
- IDE (Khuyến nghị NetBeans 8+ hoặc IntelliJ IDEA/Eclipse).

### Các bước cài đặt:

**Bước 1: Clone kho lưu trữ**
```bash
git clone https://github.com/nuyntai19/LibraryManageByJava.git
cd LibraryManageByJava-main
```

**Bước 2: Cài đặt Database**
1. Mở hệ quản trị MySQL (VD: phpMyAdmin, MySQL Workbench).
2. Tạo một schema mới có tên `quanlithuvien` (hoặc tên tương tự).
3. Chạy (Import) đoạn script từ file `src/QuanLiThuVien.sql` hoặc `src/sql_library.sql` để tạo tables và data mẫu.

**Bước 3: Cấu hình kết nối**
Điều hướng tới file cấu hình kết nối CSDL: `src/DAO/mySQLConnect.java`. Thay đổi các thông số sau cho khớp với MySQL ở môi trường local của bạn:
```java
String url = "jdbc:mysql://localhost:3306/quanlithuvien";
String user = "root";          // User MySQL của bạn
String password = "your_password"; // Mật khẩu DB
```

**Bước 4: Build và Chạy dự án**
1. Mở thư mục dự án thông qua IDE (File > Open Project).
2. IDE sẽ tự động nhận diện dependencies trong thư mục `dist/lib` và file `build.xml`.
3. Locate file `src/ui/Login.java` hoặc `src/ui/Main.java`. Right-click và chọn **Run File** để khởi động ứng dụng.
