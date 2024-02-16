import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightOrange = Color(0xFFFFD699)
val DarkOrange = Color(0xFFFFA343)
val AccentOrange = Color(0xFFFF5C00)

val LightGray = Color(0xFFE0E0E0)
val DarkGray = Color(0xFF616161)

val DarkBackground = Color(0xFF121212)
val LightBackground = Color(0xFFFFFFFF)

val DarkOnBackground = Color(0xFFFFFFFF)
val LightOnBackground = Color(0xFF000000)

val LightOrangeColorScheme = lightColorScheme(
    primary = LightOrange,
    secondary = DarkOrange,
    tertiary = AccentOrange,
    background = LightBackground,
    surface = LightBackground,
    onPrimary = DarkOnBackground,
    onSecondary = DarkOnBackground,
    onTertiary = DarkOnBackground,
    onBackground = DarkOnBackground,
    onSurface = DarkOnBackground
)

val DarkOrangeColorScheme = darkColorScheme(
    primary = LightOrange,
    secondary = DarkOrange,
    tertiary = AccentOrange,
    background = DarkBackground,
    surface = DarkBackground,
    onPrimary = LightOnBackground,
    onSecondary = LightOnBackground,
    onTertiary = LightOnBackground,
    onBackground = LightOnBackground,
    onSurface = LightOnBackground
)
