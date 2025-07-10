package toni.darkwindowbar;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.system.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mojang.blaze3d.platform.Window;

#if FORGE
import net.minecraftforge.fml.common.Mod;
#endif

#if NEO
import net.neoforged.fml.common.Mod;
#endif

#if FORGELIKE
@Mod("darkwindowbar")
#endif
public class DarkWindowBar
{
    public static final String MODNAME = "Dark Window Bar";
    public static final String ID = "darkwindowbar";
    private static final Logger LOGGER = LoggerFactory.getLogger(MODNAME);

    public DarkWindowBar() { }

    public static void setDarkWindowBar(Window window) {
        if (Platform.get() != Platform.WINDOWS) {
            LOGGER.warn("Dark Window Bar only works on Windows!");
            return;
        }

        WinNT.OSVERSIONINFO osversioninfo = new WinNT.OSVERSIONINFO();
        Kernel32.INSTANCE.GetVersionEx(osversioninfo);

        if (osversioninfo.dwMajorVersion.longValue() < 10L || osversioninfo.dwBuildNumber.longValue() < 17763L) {
            LOGGER.warn("DarkTitleBar requires Windows 10 version 1809 or newer!");
            return;
        }

        long glfwWindow = window.getWindow();
        long hwndLong = GLFWNativeWin32.glfwGetWin32Window(glfwWindow);

        WinDef.HWND hwnd = new WinDef.HWND(Pointer.createConstant(hwndLong));
        Memory mem = new Memory(Native.POINTER_SIZE);
        mem.setInt(0L, 1);
        DwmApi.INSTANCE.DwmSetWindowAttribute(hwnd, DwmApi.DWMWA_USE_IMMERSIVE_DARK_MODE, new WinDef.LPVOID(mem), new WinDef.DWORD(4L));
        mem.close();

        int oldWidth = window.getScreenWidth();
        window.setWindowed(oldWidth + 2, window.getScreenHeight());
        window.setWindowed(oldWidth, window.getScreenHeight());
    }
}
