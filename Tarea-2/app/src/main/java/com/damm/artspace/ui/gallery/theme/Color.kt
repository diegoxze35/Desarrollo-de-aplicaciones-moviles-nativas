package com.damm.artspace.ui.gallery.theme

import androidx.compose.ui.graphics.Color

// Tema Claro - Basado en PrimaryLight = #CCABFA (Un lavanda claro)
val PrimaryLight = Color(0xFFCCABFA) // Color base proporcionado
val OnPrimaryLight = Color(0xFF38006B) // Texto oscuro para contraste sobre lavanda claro
val PrimaryContainerLight = Color(0xFFEADDFF) // Un tono más claro de lavanda para contenedores
val OnPrimaryContainerLight = Color(0xFF21005D) // Texto oscuro para contraste sobre el contenedor claro

val SecondaryLight = Color(0xFFC0C2DB) // Un gris azulado suave como secundario
val OnSecondaryLight = Color(0xFF2E3048)
val SecondaryContainerLight = Color(0xFFE2E0F9)
val OnSecondaryContainerLight = Color(0xFF1B1C31)

val TertiaryLight = Color(0xFFB7C3A0) // Un verde suave como terciario
val OnTertiaryLight = Color(0xFF283416)
val TertiaryContainerLight = Color(0xFFDAEBC0)
val OnTertiaryContainerLight = Color(0xFF151F06)

val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

val BackgroundLight = Color(0xFFFEFBFF) // Fondo casi blanco
val OnBackgroundLight = Color(0xFF1B1B1F) // Texto oscuro sobre fondo claro

val SurfaceLight = Color(0xFFFEFBFF) // Superficie igual al fondo
val OnSurfaceLight = Color(0xFF1B1B1F) // Texto oscuro sobre superficie clara
val SurfaceVariantLight = Color(0xFFE7E0EB) // Variante de superficie ligeramente más oscura/grisácea
val OnSurfaceVariantLight = Color(0xFF49454E) // Texto para la variante de superficie

val OutlineLight = Color(0xFF7A757F)
val OutlineVariantLight = Color(0xFFCBC4CF) // Un borde más sutil

val ScrimLight = Color(0xFF000000)
val SurfaceTintLight = PrimaryLight // Por defecto, el tinte de superficie es el primario

val InverseSurfaceLight = Color(0xFF303033)
val InverseOnSurfaceLight = Color(0xFFF2EFF4)
val InversePrimaryLight = Color(0xFF5C2E9B) // El PrimaryDark para el tema inverso

// Tema Oscuro - Basado en PrimaryDark = #5C2E9B (Un morado intenso)
val PrimaryDark = Color(0xFF5C2E9B) // Color base proporcionado
val OnPrimaryDark = Color(0xFFFFFFFF) // Texto claro para contraste sobre morado oscuro
val PrimaryContainerDark = Color(0xFF430084) // Un tono de morado ligeramente diferente para contenedores
val OnPrimaryContainerDark = Color(0xFFEADDFF) // Texto claro para contraste sobre el contenedor oscuro

val SecondaryDark = Color(0xFFA5A5BF) // Un gris azulado más claro para tema oscuro
val OnSecondaryDark = Color(0xFF1A1A31)
val SecondaryContainerDark = Color(0xFF40425A)
val OnSecondaryContainerDark = Color(0xFFE2E0F9)

val TertiaryDark = Color(0xFF9FB781) // Un verde más claro para tema oscuro
val OnTertiaryDark = Color(0xFF141F00)
val TertiaryContainerDark = Color(0xFF4D5B3C)
val OnTertiaryContainerDark = Color(0xFFDAEBC0)

val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

val BackgroundDark = Color(0xFF1B1B1F) // Fondo oscuro
val OnBackgroundDark = Color(0xFFE5E1E6) // Texto claro sobre fondo oscuro

val SurfaceDark = Color(0xFF1B1B1F) // Superficie igual al fondo
val OnSurfaceDark = Color(0xFFE5E1E6) // Texto claro sobre superficie oscura
val SurfaceVariantDark = Color(0xFF49454E) // Variante de superficie ligeramente más clara/grisácea
val OnSurfaceVariantDark = Color(0xFFCAC4CF) // Texto para la variante de superficie

val OutlineDark = Color(0xFF948F99)
val OutlineVariantDark = Color(0xFF49454E) // Un borde más sutil

val ScrimDark = Color(0xFF000000)
val SurfaceTintDark = PrimaryDark // Por defecto, el tinte de superficie es el primario

val InverseSurfaceDark = Color(0xFFE5E1E6)
val InverseOnSurfaceDark = Color(0xFF303033)
val InversePrimaryDark = Color(0xFFCCABFA) // El PrimaryLight para el tema inverso