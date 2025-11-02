#include <windows.h>
#include <commctrl.h>

//Mé nó bắt mình nhập mấy dòng này, éo hiểu gì hết:)
#pragma comment(lib, "comctl32.lib")
#pragma comment(linker,"\"/manifestdependency:type='win32' \
name='Microsoft.Windows.Common-Controls' version='6.0.0.0' \
processorArchitecture='*' publicKeyToken='6595b64144ccf1df' language='*'\"")

/*Hàm main (Đáng nhẽ là WinMain mà vì unicode nên dùng wWinMain)
    hInstance: Handle đến instance hiện tại của chương trình, basically để xử lí hộp thoại thôi
    hPrevInstance: Luôn NULL, giữ lại cho tương thích cũ, nghe nói lỗi thời rồi phải, mà thôi thêm vào cho nó đủ
    lpCmdLine: Chuỗi lệnh khi chạy chương trình (Unicode)
        Cái này hay này:))
        Khai báo Runtime.getRuntime().exec("Đường dẫn "+lpCmdLine) để đưa dữ liệu vào cpp cho nó xử lí:)
    nCmdShow: Xác định cách hiển thị cửa sổ (ẩn, thu nhỏ, phóng to, …)
 */
int WINAPI wWinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, PWSTR lpCmdLine, int nCmdShow)
{
    //Tạo một cấu trúc chứa mọi thông tin cấu hình của hộp thoại TaskDialog
    //TASKDIALOGCONFIG là một struct do Windows định nghĩa.
    //{ sizeof(config) } giúp Windows biết kích thước struct (để tương thích giữa các bản SDK khác nhau).
    TASKDIALOGCONFIG config = { sizeof(config) };

    //Không có cửa sổ cha, nên hộp thoại hiển thị độc lập.
    config.hwndParent = NULL;

    //Đây là cờ điều khiển hành vi hộp thoại. (Trong trường hợp này thì có thể đóng nhờ nút x hoặc phím esc)
    config.dwFlags = TDF_ALLOW_DIALOG_CANCELLATION;

    // Không dùng nút mặc định (Mấy nút No,Yes,Cancel,Apply... á)
    config.dwCommonButtons = 0;

    //Title thôi:)
    config.pszWindowTitle = L"Hộp thoại tùy chỉnh";

    //Content chính
    config.pszMainInstruction = L"Vui lòng chọn đối tượng của bạn";

    //Content bổ sung thôi:)
    config.pszContent = L"Việc này giúp chúng tôi có thể hỗ trợ cho bạn";

    // Danh sách nút tuỳ chỉnh
    TASKDIALOG_BUTTON buttons[] = {
        { 100, L"Khách hàng mới" },
        { 101, L"Khách hàng cũ" },
        { 102, L"Tài khoản nhân viên" }
    };

    //Trỏ tới mảng các nút mà bạn vừa khai báo. (pointerButton)
    config.pButtons = buttons;

    //Cho Windows biết có bao nhiêu nút trong mảng.
    config.cButtons = ARRAYSIZE(buttons);

    //Biến để nhận ID của nút được bấm sau khi hộp thoại đóng.
    int nButtonPressed = 0;

    //Hàm chính hiển thị hộp thoại. (SUCCEEDED(hr) trả về 1 nếu oke)
    HRESULT hr = TaskDialogIndirect(&config, &nButtonPressed, NULL, NULL);

    //Tự hiểu đi:))
    if (SUCCEEDED(hr)) {
        switch (nButtonPressed) {
        case 100:
            MessageBoxW(NULL, L"Bạn đã chọn Khách hàng mới.", L"Khách hàng mới", MB_OK | MB_ICONINFORMATION);
            break;
        case 101:
            MessageBoxW(NULL, L"Bạn đã chọn Khách hàng cũ.", L"Khách hàng cũ", MB_OK | MB_ICONINFORMATION);
            break;
        case 102:
            MessageBoxW(NULL, L"Bạn đã chọn Nhân viên khách hàng.", L"Nhân viên khách hàng", MB_OK | MB_ICONINFORMATION);
            break;
        default:
            MessageBoxW(NULL, L"Không có nút nào được chọn.", L"Thông báo", MB_OK | MB_ICONEXCLAMATION);
            break;
        }
    }
    else {
        MessageBoxW(NULL, L"Không thể hiển thị hộp thoại.", L"Lỗi", MB_OK | MB_ICONERROR);
    }
    return 0;
}
