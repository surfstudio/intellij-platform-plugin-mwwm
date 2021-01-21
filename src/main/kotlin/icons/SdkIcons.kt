package icons

import com.intellij.openapi.util.IconLoader
import java.io.File

//Пути до иконок
object SdkIcons {
    @JvmField
    val mwwm_icon_mini_size = IconLoader.getIcon(
            File.separator + "icons" + File.separator + "mwwm_mini_size.svg", javaClass)

    @JvmField
    val mwwm_icon_standart_size = IconLoader.getIcon(
            File.separator + "icons" + File.separator + "pluginIcon.svg", javaClass)
}