package toni.darkwindowbar;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface DwmApi extends StdCallLibrary {
    public static final DwmApi INSTANCE = (DwmApi)Native.load("dwmapi", DwmApi.class, W32APIOptions.DEFAULT_OPTIONS);

    public static final WinDef.DWORD DWMWA_USE_IMMERSIVE_DARK_MODE = new WinDef.DWORD(20L);

    WinNT.HRESULT DwmSetWindowAttribute(WinDef.HWND paramHWND, WinDef.DWORD paramDWORD1, WinDef.LPVOID paramLPVOID, WinDef.DWORD paramDWORD2);
}
