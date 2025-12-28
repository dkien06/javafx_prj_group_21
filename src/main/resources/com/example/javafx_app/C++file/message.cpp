#include <windows.h>
#include <string>

int wmain(int argc, wchar_t* argv[]) {
    std::wstring message = L"Thông báo mặc định";
    if (argc > 1) {
        message = argv[1];
    }

    std::wstring psCommand =
        L"-Command \"[void][Windows.UI.Notifications.ToastNotificationManager, Windows.UI.Notifications, ContentType = WindowsRuntime]; "
        L"$xml = [Windows.UI.Notifications.ToastNotificationManager]::GetTemplateContent([Windows.UI.Notifications.ToastTemplateType]::ToastImageAndText02); "
        L"$texts = $xml.GetElementsByTagName('text'); "
        L"$texts[0].AppendChild($xml.CreateTextNode(\'" + message + "\')); "
        L"$toast = [Windows.UI.Notifications.ToastNotification]::new($xml); "
        L"[Windows.UI.Notifications.ToastNotificationManager]::CreateToastNotifier('JavaApp').Show($toast)\"";

    ShellExecuteW(NULL, L"open", L"powershell.exe", psCommand.c_str(), NULL, SW_HIDE);
    return 0;
}